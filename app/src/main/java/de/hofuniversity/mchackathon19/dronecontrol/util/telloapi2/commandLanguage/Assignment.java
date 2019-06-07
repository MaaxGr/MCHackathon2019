package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;


import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.OwnCommand;

public class Assignment extends Statement implements Visitable
{
    private String name;
    private Expression expression;

    public Assignment(String name, Expression expression)
    {
        this.name = name;
        this.expression = expression;
    }

    public String getName()
    {
        return name;
    }

    public Expression getExpression()
    {
        return expression;
    }

    public void accept(OwnCommand oC)
    {
        oC.visit(this);
    }

}
