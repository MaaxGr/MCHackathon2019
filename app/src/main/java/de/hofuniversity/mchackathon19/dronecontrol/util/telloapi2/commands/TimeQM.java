package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class TimeQM extends ReadCommand
{
    public TimeQM()
    {
        value = "time?";
    }

    public Command reverse()
    {
        return this;
    }
}
