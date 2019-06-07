package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class SpeedQM extends ReadCommand
{
    public SpeedQM()
    {
        value = "speed?";
    }

    public Command reverse()
    {
        return this;
    }
}
