package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public abstract class Command
{
    protected String value;

    public String getValue()
    {
        return value;
    }

    public abstract Command reverse();

    public static Command getCommand(String s)
    {
        String[] splittedString = s.split(" ");
        double[] values = new double[splittedString.length - 1];
        if(!splittedString[0].equals("flip"))
        {
            for(int i = 0; i < splittedString.length - 1; i++)
            {
                try
                {
                    values[i] = Double.parseDouble(splittedString[i + 1]);
                } catch(Exception e)
                {
                    return null;
                }
            }
        }
        try
        {
            switch(splittedString[0])
            {
            case "takeoff":
                return new Takeoff();
            case "land":
                return new Land();
            case "streamon":
                return new StreamOn();
            case "streamoff":
                return new StreamOff();
            case "emergency":
                return new Emergency();
            case "up":
                return new UpX(values[0]);
            case "down":
                return new DownX(values[0]);
            case "left":
                return new LeftX(values[0]);
            case "right":
                return new RightX(values[0]);
            case "forward":
                return new ForwardX(values[0]);
            case "back":
                return new BackwardX(values[0]);
            case "cw":
                return new RotateCX(values[0]);
            case "ccw":
                return new RotateCCX(values[0]);
            case "flip":
                return new FlipX(splittedString[1].charAt(0));
            case "go":
                if(splittedString.length == 5)
                    return new GoXYZSpeed(values[0], values[1], values[2], values[3]);
                else
                    return new GoXYZSpeedMid(values[0], values[1], values[2], values[3], values[4]);
            case "curve":
                if(splittedString.length == 8)
                    return new CurveXYZXYZSpeed(values[0], values[1], values[2], values[3], values[4], values[5],
                            values[6]);
                else
                    return new CurveXYZXYZSpeedMid(values[0], values[1], values[2], values[3], values[4], values[5],
                            values[6], values[7]);
            case "jump":
                return new JumpXYZSpeedYawMidMid(values[0], values[1], values[2], values[3], values[4], values[5],
                        values[6]);
            case "speed":
                return new SpeedX(values[0]);
            case "mon":
                return new MissionPadOn();
            case "moff":
                return new MissionPadOff();
            case "stop":
                return new Stop();
            case "mdirection":
                return new MissionPadDirectionX(values[0]);
            case "speed?":
                return new SpeedQM();
            case "battery?":
                return new BatteryQM();
            case "time?":
                return new TimeQM();
            case "height?":
                return new HeightQM();
            default:
                System.out.println("Fehler beim Parsen -> Autoland");
                return new Land();
            }
        } catch(Exception e)
        {
            System.out.println("Fehler beim Parsen -> Autoland");
            return new Land();
        }
    }
}