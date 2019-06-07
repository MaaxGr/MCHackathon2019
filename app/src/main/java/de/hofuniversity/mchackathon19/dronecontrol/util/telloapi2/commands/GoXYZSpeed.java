package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class GoXYZSpeed extends Command
{
    private double x;
    private double y;
    private double z;
    private double speed;

    private double oldX;
    private double oldY;
    private double oldZ;

    public GoXYZSpeed(double x, double y, double z, double speed)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.speed = speed;
        value = "go";
    }

    public void setOldValues(double oldX, double oldY, double oldZ)
    {
        this.oldX = oldX;
        this.oldY = oldY;
        this.oldZ = oldZ;
    }

    public String getValue()
    {
        return value + " " + x + " " + y + " " + z + " " + speed;
    }

    public Command reverse()
    {
        return new GoXYZSpeed(oldX, oldY, oldZ, speed);
    }
}
