package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;

public abstract class Command extends Statement implements Visitable
{
    public static Command parse(String command)
    {
        switch(command.split(" ")[0])
        {
        case "out":
            return new Out(command);
        case "sleep":
            return new Sleep(command);
        case "continue":
            return new Continue();
        case "return":
            return new Return();
        case "break":
            return new Break();
        default:
            return new DroneCommand(command);
        }
    }
}