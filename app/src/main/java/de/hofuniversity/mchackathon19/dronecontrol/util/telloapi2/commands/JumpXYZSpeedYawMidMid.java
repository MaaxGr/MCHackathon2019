package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class JumpXYZSpeedYawMidMid extends Command
{
    private double x;
    private double y;
    private double z;
    private double speed;
    private double yaw;
    private double mid1;
    private double mid2;

    public JumpXYZSpeedYawMidMid(double x, double y, double z, double speed, double yaw, double mid1, double mid2)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.speed = speed;
        this.yaw = yaw;
        this.mid1 = mid1;
        this.mid2 = mid2;
        value = "jump";
    }

    public String getValue()
    {
        return value + " " + x + " " + y + " " + z + " " + speed + " " + yaw + " " + mid1 + " " + mid2;
    }

    public Command reverse()
    {
        // TODO: Check if correct
        return new JumpXYZSpeedYawMidMid(x, y, z, speed, yaw, mid2, mid1);
    }
}
