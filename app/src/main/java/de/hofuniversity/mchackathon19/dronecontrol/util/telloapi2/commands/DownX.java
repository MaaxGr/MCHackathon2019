package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class DownX extends Command
{
    private double x;

    public DownX(double x)
    {
        this.x = x;
        value = "down";
    }

    public String getValue()
    {
        return value + " " + x;
    }

    public Command reverse()
    {
        return new UpX(x);
    }
}
