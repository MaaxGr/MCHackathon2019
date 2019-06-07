package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class Stop extends Command
{
    public Stop()
    {
        value = "stop";
    }

    public Command reverse()
    {
        return this;
    }
}
