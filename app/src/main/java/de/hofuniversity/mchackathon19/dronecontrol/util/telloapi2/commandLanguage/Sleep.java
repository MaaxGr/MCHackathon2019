package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;


import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.OwnCommand;

public class Sleep extends Command
{
    private Arithmetic a;

    public Sleep(String command)
    {
        this.a = Arithmetic.parse(command.substring(6));
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