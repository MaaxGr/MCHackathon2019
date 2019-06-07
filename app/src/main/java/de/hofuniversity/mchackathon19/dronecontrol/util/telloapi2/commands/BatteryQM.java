package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class BatteryQM extends ReadCommand
{
    public BatteryQM()
    {
        value = "battery?";
    }

    public Command reverse()
    {
        return this;
    }
}
