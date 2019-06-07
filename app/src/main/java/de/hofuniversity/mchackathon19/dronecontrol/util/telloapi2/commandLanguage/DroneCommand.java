package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;

import java.util.*;

import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.OwnCommand;

public class DroneCommand extends Command
{
    private String command;
    private List<Arithmetic> parameters;

    public DroneCommand(String commandLine)
    {
        command = commandLine.split(" ")[0];
        if(!command.equals("flip"))
        {
            parameters = new ArrayList<>();
            String paramString = commandLine.substring(command.length());
            for(String parameter : paramString.split(","))
            {
                if(parameter.length() > 0)
                {
                    parameters.add(Arithmetic.parse(parameter));
                }
            }
        }
        else
        {
            command = commandLine;
        }
    }

    public String getCommand()
    {
        return command;
    }

    public List<Arithmetic> getParameters()
    {
        return parameters;
    }

    public void accept(OwnCommand oC)
    {
        oC.visit(this);
    }
}