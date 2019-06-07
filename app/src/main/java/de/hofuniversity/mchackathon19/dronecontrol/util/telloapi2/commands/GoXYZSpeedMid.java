package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class GoXYZSpeedMid extends Command
{
    private double x;
    private double y;
    private double z;
    private double speed;
    private double mid;

    private double oldX;
    private double oldY;
    private double oldZ;
    private double oldMid;

    public GoXYZSpeedMid(double x, double y, double z, double speed, double mid)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.mid = mid;
        this.speed = speed;
        value = "go";
    }

    public void setOldValues(double oldX, double oldY, double oldZ, double oldMid)
    {
        this.oldX = oldX;
        this.oldY = oldY;
        this.oldZ = oldZ;
        this.oldMid = oldMid;
    }

    public String getValue()
    {
        return value + " " + x + " " + y + " " + z + " " + speed + " " + mid;
    }

    public Command reverse()
    {
        return new GoXYZSpeedMid(oldX, oldY, oldZ, speed, oldMid);
    }
}
