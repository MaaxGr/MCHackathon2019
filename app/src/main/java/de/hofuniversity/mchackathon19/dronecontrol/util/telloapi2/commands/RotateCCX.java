package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class RotateCCX extends Command
{
    private double x;

    public RotateCCX(double x)
    {
        this.x = x;
        value = "ccw";
    }

    public String getValue()
    {
        return value + " " + x;
    }

    public Command reverse()
    {
        return new RotateCX(x);
    }
}
