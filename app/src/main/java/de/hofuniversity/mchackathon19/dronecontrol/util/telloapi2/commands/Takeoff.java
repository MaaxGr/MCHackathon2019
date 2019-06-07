package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class Takeoff extends Command
{
    public Takeoff()
    {
        value = "takeoff";
    }

    public Command reverse()
    {
        return new Land();
    }
}
