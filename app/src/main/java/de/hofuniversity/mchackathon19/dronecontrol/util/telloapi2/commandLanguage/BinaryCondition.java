package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;


import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.OwnCommand;

public class BinaryCondition extends Condition
{
    private Condition left;
    private Condition right;
    private BooleanOperator operator;

    public BinaryCondition(Condition left, Condition right, BooleanOperator operator)
    {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public Condition getLeft()
    {
        return left;
    }

    public Condition getRight()
    {
        return right;
    }

    public BooleanOperator getOperator()
    {
        return operator;
    }

    public boolean accept(OwnCommand oC)
    {
        return oC.visit(this);
    }
}
