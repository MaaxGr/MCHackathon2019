package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;

import java.util.*;

public class ExpressionToParse<Operand, Operator>
{
    private List<Operand> operands;
    private List<Operator> operators;

    public ExpressionToParse()
    {
        operands = new ArrayList<>();
        operators = new ArrayList<>();
    }

    public void addOperand(Operand o)
    {
        operands.add(o);
    }

    public void addOperator(Operator o)
    {
        operators.add(o);
    }

    public Operand getOperandAt(int i)
    {
        return operands.get(i);
    }

    public Operator getOperatorAt(int i)
    {
        return operators.get(i);
    }

    void add(ExpressionToParse<Operand, Operator> e)
    {
        e.operands.forEach(eO -> operands.add(eO));
        e.operators.forEach(eO -> operators.add(eO));
    }

    void add(Operator o, ExpressionToParse<Operand, Operator> e)
    {
        operators.add(o);
        e.operands.forEach(eO -> operands.add(eO));
        e.operators.forEach(eO -> operators.add(eO));
    }

    int size()
    {
        return operands.size();
    }

    @SuppressWarnings("unchecked")
    void concat(int firstIndex)
    {
        Operand first = operands.remove(firstIndex);
        Operand second = operands.remove(firstIndex);
        Operator operator = operators.remove(firstIndex);

        if(first instanceof Arithmetic)
        {
            operands.add(firstIndex, (Operand) new BinaryArithmetic((Arithmetic) first, (Arithmetic) second,
                    (ArithmeticOperator) operator));
        }
        else if(first instanceof Condition)
        {
            operands.add(firstIndex,
                    (Operand) new BinaryCondition((Condition) first, (Condition) second, (BooleanOperator) operator));
        }
    }
}