package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class Emergency extends Command
{
    public Emergency()
    {
        value = "emergency";
    }

    public Command reverse()
    {
        return this;
    }
}
