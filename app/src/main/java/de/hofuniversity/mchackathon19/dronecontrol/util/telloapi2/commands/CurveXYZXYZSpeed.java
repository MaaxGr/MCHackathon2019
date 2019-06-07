package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class CurveXYZXYZSpeed extends Command
{
    private double x1;
    private double y1;
    private double z1;
    private double x2;
    private double y2;
    private double z2;
    private double speed;

    public CurveXYZXYZSpeed(double x1, double y1, double z1, double x2, double y2, double z2, double speed)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
        this.speed = speed;
        value = "curve";
    }

    public String getValue()
    {
        return value + " " + x1 + " " + y1 + " " + z1 + " " + x2 + " " + y2 + " " + z2 + " " + speed;
    }

    public Command reverse()
    {
        // TODO: Check if correct
        return new CurveXYZXYZSpeed(x2, y2, z2, x1, y1, z1, speed);
    }
}
