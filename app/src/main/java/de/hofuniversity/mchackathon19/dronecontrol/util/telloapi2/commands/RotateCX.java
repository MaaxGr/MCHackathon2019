package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class RotateCX extends Command
{
    private double x;

    public RotateCX(double x)
    {
        this.x = x;
        value = "cw";
    }

    public String getValue()
    {
        return value + " " + x;
    }

    public Command reverse()
    {
        return new RotateCCX(x);
    }
}
