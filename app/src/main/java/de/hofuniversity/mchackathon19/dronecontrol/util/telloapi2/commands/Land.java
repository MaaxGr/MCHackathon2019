package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class Land extends Command
{
    public Land()
    {
        value = "land";
    }

    public Command reverse()
    {
        return new Takeoff();
    }
}
