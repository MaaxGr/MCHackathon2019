package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;

public enum BooleanOperator
{
    And, Or, XOr;

    static BooleanOperator getOperator(String operator)
    {
        switch(operator)
        {
        case "&&":
            return And;
        case "||":
            return Or;
        case "^":
            return XOr;
        default:
            return null;
        }
    }
}
