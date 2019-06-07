package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class UpX extends Command
{
    private double x;

    public UpX(double x)
    {
        this.x = x;
        value = "up";
    }

    public String getValue()
    {
        return value + " " + x;
    }

    public Command reverse()
    {
        return new DownX(x);
    }
}
