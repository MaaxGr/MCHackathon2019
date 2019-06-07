package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;

import java.util.List;

import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.OwnCommand;

public class While extends Statement implements Visitable
{
    private Condition condition;
    private List<Statement> statements;

    public While(Condition condition, List<Statement> statements)
    {
        this.condition = condition;
        this.statements = statements;
    }

    public Condition getCondition()
    {
        return condition;
    }

    public List<Statement> getStatements()
    {
        return statements;
    }

    public void accept(OwnCommand oC)
    {
        oC.visit(this);
    }
}
