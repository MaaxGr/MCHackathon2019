package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class HeightQM extends ReadCommand
{
    public HeightQM()
    {
        value = "height?";
    }

    public Command reverse()
    {
        return this;
    }
}
