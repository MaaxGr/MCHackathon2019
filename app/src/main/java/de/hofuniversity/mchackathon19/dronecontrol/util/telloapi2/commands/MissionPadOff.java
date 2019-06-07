package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class MissionPadOff extends Command
{
    public MissionPadOff()
    {
        value = "moff";
    }

    public Command reverse()
    {
        return new MissionPadOn();
    }
}
