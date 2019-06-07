package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.droneControl;

import java.io.*;
import java.util.*;

import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage.CommandParser;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.Command;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.GoXYZSpeed;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.GoXYZSpeedMid;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.MissionPadDirectionX;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.OwnCommand;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.SpeedQM;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.SpeedX;

public class DroneControl
{
    private DroneCommunication droneComm;

    private boolean recording;
    private List<Command> recordedCommands;

    private Map<String, OwnCommand> ownCommands;

    private int oldMdirectionValue;

    public DroneControl()
    {
        droneComm = new DroneCommunication("192.168.10.1", 8889, 8890);

        parseOwnCommands();

        oldMdirectionValue = 2;
    }

    public void setRecording(boolean recording)
    {
        this.recording = recording;
    }

    public void resetRecordedCommands()
    {
        recordedCommands = new ArrayList<>();
    }

    private void parseOwnCommands()
    {
        ownCommands = new HashMap<String, OwnCommand>();

        File ownCommandFolder = new File(System.getProperty("user.dir") + "/OwnCommands");

        if(ownCommandFolder.listFiles() == null) {
            return;
        }

        for(File f : ownCommandFolder.listFiles())
        {
            if(f.getName().equals(".DS_Store") || f.getName().equals("ReadMe"))
            {
                continue;
            }
            CommandParser parser = new CommandParser();
            try
            {
                OwnCommand command = parser.parse(f, this);
                command.setDC(this);
                ownCommands.put(command.getName(), command);
            } catch(Exception e)
            {
                System.out.println(f.getName() + " failed to be parsed");
            }
        }
    }

    public String sendCommand(Command command)
    {
        if(recording)
        {
            recordedCommands.add(command);
        }

        rememberOldValuesForSpecialCommands(command);
        if(command instanceof MissionPadDirectionX)
        {
            // only way to remeber old value
            // mdirection x -> value of x = substring(12, length)
            oldMdirectionValue = Integer.parseInt(command.getValue().substring(12, command.getValue().length()));
        }

        return droneComm.sendCommand(command.getValue());
    }

    public void executeCommand(String input)
    {
        String commandName = input.split(" ")[0];

        if(ownCommands.containsKey(commandName))
        {
            ownCommands.get(commandName).execute(input);
        }
        else
        {
            Command command = Command.getCommand(input);
            sendCommand(command);
        }
    }

    public String getOutput()
    {
        return droneComm.getOutput();
    }

    private void rememberOldValuesForSpecialCommands(Command command)
    {
        if(command instanceof GoXYZSpeed)
        {
            HashMap<String, Double> oldValues = getOutputMap();
            ((GoXYZSpeed) command).setOldValues(oldValues.get("x").intValue(), oldValues.get("y").intValue(),
                    oldValues.get("z").intValue());
        }
        else if(command instanceof GoXYZSpeedMid)
        {
            HashMap<String, Double> oldValues = getOutputMap();
            ((GoXYZSpeedMid) command).setOldValues(oldValues.get("x").intValue(), oldValues.get("y").intValue(),
                    oldValues.get("z").intValue(), oldValues.get("mid").intValue());
        }
        else if(command instanceof SpeedX)
        {
            int speed = Integer.parseInt(sendCommand(new SpeedQM()));
            ((SpeedX) command).setOldValue(speed);
        }
        else if(command instanceof MissionPadDirectionX)
        {
            ((MissionPadDirectionX) command).setOldValue(oldMdirectionValue);
        }
    }

    public HashMap<String, Double> getOutputMap()
    {
        HashMap<String, Double> returnMap = new HashMap<>();

        String[] output = droneComm.getOutput().split(";");
        for(int i = 0; i < output.length; i++)
        {
            String key = output[i].split(":")[0];
            double value = Double.parseDouble(output[i].split(":")[1]);
            returnMap.put(key, value);
        }

        return returnMap;
    }

    public void reverse()
    {
        if(recordedCommands == null)
        {
            return;
        }

        for(int i = recordedCommands.size() - 1; i >= 0; i--)
        {
            sendCommand(recordedCommands.get(i).reverse());
        }
    }

    public void refly()
    {
        if(recordedCommands == null)
        {
            return;
        }

        for(Command c : recordedCommands)
        {
            sendCommand(c);
        }
    }
}