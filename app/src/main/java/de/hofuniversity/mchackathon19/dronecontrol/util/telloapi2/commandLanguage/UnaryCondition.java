package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;


import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.OwnCommand;

public class UnaryCondition extends Condition
{
    private Condition operand;

    public UnaryCondition(Condition operand)
    {
        this.operand = operand;
    }

    public Condition getOperand()
    {
        return operand;
    }

    public boolean accept(OwnCommand oC)
    {
        return oC.visit(this);
    }
}