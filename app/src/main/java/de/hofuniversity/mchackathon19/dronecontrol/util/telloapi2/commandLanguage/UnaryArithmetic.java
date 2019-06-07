package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;


import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.OwnCommand;

public class UnaryArithmetic extends Arithmetic
{
    private Arithmetic operand;

    public UnaryArithmetic(Arithmetic operand)
    {
        this.operand = operand;
    }

    public Arithmetic getOperand()
    {
        return operand;
    }

    public double accept(OwnCommand oC)
    {
        return oC.visit(this);
    }
}