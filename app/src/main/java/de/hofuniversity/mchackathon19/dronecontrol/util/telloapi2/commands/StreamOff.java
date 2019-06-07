package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class StreamOff extends Command
{
    public StreamOff()
    {
        value = "streamoff";
    }

    public Command reverse()
    {
        return new StreamOn();
    }
}
