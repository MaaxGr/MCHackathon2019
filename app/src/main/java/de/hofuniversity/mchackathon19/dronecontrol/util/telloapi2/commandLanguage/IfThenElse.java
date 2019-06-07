package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;

import java.util.List;
import commands.OwnCommand;

public class IfThenElse extends Statement implements Visitable
{
    private Condition condition;
    private List<Statement> thenStatements;
    private List<Statement> elseStatements;

    public IfThenElse(Condition condition, List<Statement> thenStatements, List<Statement> elseStatements)
    {
        this.condition = condition;
        this.thenStatements = thenStatements;
        this.elseStatements = elseStatements;
    }

    public Condition getCondition()
    {
        return condition;
    }

    public List<Statement> getThenStatements()
    {
        return thenStatements;
    }

    public List<Statement> getElseStatements()
    {
        return elseStatements;
    }

    public void addElse(List<Statement> elseStatements)
    {
        this.elseStatements = elseStatements;
    }

    public void accept(OwnCommand oC)
    {
        oC.visit(this);
    }
}