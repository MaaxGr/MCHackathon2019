package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class ForwardX extends Command
{
    private double x;

    public ForwardX(double x)
    {
        this.x = x;
        value = "forward";
    }

    public String getValue()
    {
        return value + " " + x;
    }

    public Command reverse()
    {
        return new BackwardX(x);
    }
}
