package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;


import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.OwnCommand;

public class BinaryArithmetic extends Arithmetic
{
    private Arithmetic left;
    private Arithmetic right;
    private ArithmeticOperator operator;

    public BinaryArithmetic(Arithmetic left, Arithmetic right, ArithmeticOperator operator)
    {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public Arithmetic getLeft()
    {
        return left;
    }

    public Arithmetic getRight()
    {
        return right;
    }

    public ArithmeticOperator getOperator()
    {
        return operator;
    }

    public double accept(OwnCommand oC)
    {
        return oC.visit(this);
    }
}
