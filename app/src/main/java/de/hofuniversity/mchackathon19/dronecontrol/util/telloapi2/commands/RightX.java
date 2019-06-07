package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class RightX extends Command
{
    private double x;

    public RightX(double x)
    {
        this.x = x;
        value = "right";
    }

    public String getValue()
    {
        return value + " " + x;
    }

    public Command reverse()
    {
        return new LeftX(x);
    }
}
