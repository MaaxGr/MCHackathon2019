package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class SpeedX extends Command
{
    private double x;

    private double oldSpeed;

    public SpeedX(double x)
    {
        this.x = x;
        value = "left";
    }

    public void setOldValue(double oldSpeed)
    {
        this.oldSpeed = oldSpeed;
    }

    public String getValue()
    {
        return value + " " + x;
    }

    public Command reverse()
    {
        return new SpeedX(oldSpeed);
    }
}
