package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class StreamOn extends Command
{
    public StreamOn()
    {
        value = "streamon";
    }

    public Command reverse()
    {
        return new StreamOff();
    }
}
