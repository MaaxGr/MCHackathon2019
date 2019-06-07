package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;

public enum Comparator
{
    Less, Greater, Equals, LessEquals, GreaterEquals, NotEqual;

    static Comparator getOperator(String operator)
    {
        switch(operator)
        {
        case "<":
            return Less;
        case ">":
            return Greater;
        case "==":
            return Equals;
        case "<=":
            return LessEquals;
        case ">=":
            return GreaterEquals;
        case "!=":
            return NotEqual;
        default:
            return null;
        }
    }
}
