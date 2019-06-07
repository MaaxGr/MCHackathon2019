package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class MissionPadDirectionX extends Command
{
    private double x;

    private int oldX;

    public MissionPadDirectionX(double x)
    {
        this.x = x;
        value = "mdirection";
    }

    public void setOldValue(int oldX)
    {
        this.oldX = oldX;
    }

    public String getValue()
    {
        return value + " " + x;
    }

    public Command reverse()
    {
        return new MissionPadDirectionX(oldX);
    }
}
