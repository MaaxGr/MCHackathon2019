package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.droneControl;

import java.io.*;

import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.Land;

public class ConsolControl
{
    public static void main(String[] args) throws InterruptedException, IOException
    {
        DroneControl dC = new DroneControl();

        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            while(true)
            {
                System.out.println("Command... (For help: helpDrone, helpOwn)");
                String input = inputReader.readLine();
                switch(input)
                {
                case "exit":
                    return;
                case "output":
                    System.out.println(dC.getOutput());
                    break;
                case "recordstart":
                    System.out.println("Start recording...");
                    dC.setRecording(true);
                    dC.resetRecordedCommands();
                    break;
                case "recordcontinue":
                    System.out.println("Continue recording...");
                    dC.setRecording(true);
                case "recordstop":
                    System.out.println("Stop recording...");
                    dC.setRecording(false);
                    break;
                case "reverse":
                    dC.reverse();
                    break;
                case "refly":
                    dC.refly();
                    break;
                case "helpDrone":
                    System.out.println("See manual!");
                    break;
                case "helpOwn":
                    System.out.println(
                            "testflight, handland, output, recordstart, recordcontinue, recordstop, reverse, refly, exit");
                    break;

                default:
                    dC.executeCommand(input);
                }
            }
        } catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Error in programm! Autoland!");
            dC.sendCommand(new Land());
        }
    }

}