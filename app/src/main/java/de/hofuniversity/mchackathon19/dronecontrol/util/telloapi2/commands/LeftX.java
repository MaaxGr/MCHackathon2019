package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class LeftX extends Command
{
    private double x;

    public LeftX(double x)
    {
        this.x = x;
        value = "left";
    }

    public String getValue()
    {
        return value + " " + x;
    }

    public Command reverse()
    {
        return new RightX(x);
    }
}
