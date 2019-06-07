package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;


import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.OwnCommand;

public class Comparison extends Condition
{
    private Arithmetic left;
    private Arithmetic right;
    private Comparator operator;

    public Comparison(Arithmetic left, Arithmetic right, Comparator operator)
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

    public Comparator getOperator()
    {
        return operator;
    }

    public boolean accept(OwnCommand oC)
    {
        return oC.visit(this);
    }

    public static Comparison parse(String s)
    {
        String[] parts = s.split(" >= | <= | > | < | == | != ");
        int lengthOfOperatorWithSpaces = s.length() - parts[0].length() - parts[1].length();
        String operator = s.substring(parts[0].length() + 2, parts[0].length() + lengthOfOperatorWithSpaces);

        return new Comparison(Arithmetic.parse(parts[0]), Arithmetic.parse(parts[1]), Comparator.getOperator(operator));
    }
}