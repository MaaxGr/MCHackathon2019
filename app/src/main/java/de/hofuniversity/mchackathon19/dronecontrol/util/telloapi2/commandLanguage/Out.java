package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;


import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.OwnCommand;

public class Out extends Command
{
    private Arithmetic a;

    public Out(String command)
    {
        this.a = Arithmetic.parse(command.substring(4));
    }

    public Arithmetic getA()
    {
        return a;
    }

    public void accept(OwnCommand oC)
    {
        oC.visit(this);
    }
}