package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class MissionPadOn extends Command
{
    public MissionPadOn()
    {
        value = "mon";
    }

    public Command reverse()
    {
        return new MissionPadOff();
    }
}
