package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

public class FlipX extends Command
{
    private char x;

    public FlipX(char x)
    {
        this.x = x;
        value = "flip";
    }

    public String getValue()
    {
        return value + " " + x;
    }

    public Command reverse()
    {
        switch(x)
        {
        case 'f':
            return new FlipX('b');
        case 'b':
            return new FlipX('f');
        case 'l':
            return new FlipX('r');
        case 'r':
            return new FlipX('l');
        default:
            return null;
        }
    }
}
