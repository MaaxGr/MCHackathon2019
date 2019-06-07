package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class BackwardX extends Command
{
    private double x;

    public BackwardX(double x)
    {
        this.x = x;
        value = "back";
    }

    public String getValue()
    {
        return value + " " + x;
    }

    public Command reverse()
    {
        return new ForwardX(x);
    }
}
