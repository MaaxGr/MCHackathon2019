package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;


import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.OwnCommand;

public class Number extends Arithmetic
{
    private double number;

    public Number(double number)
    {
        this.number = number;
    }

    public double getNumber()
    {
        return number;
    }

    public double accept(OwnCommand oC)
    {
        return oC.visit(this);
    }
}