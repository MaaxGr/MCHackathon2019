package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;

import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.OwnCommand;

public abstract class Condition
{
    public abstract boolean accept(OwnCommand oC);

    public static Condition parse(String s)
    {
        ExpressionToParse<Condition, BooleanOperator> eTP = convertToETP(s);

        while(eTP.size() > 1)
        {
            eTP.concat(eTP.size() - 2);
        }

        return eTP.getOperandAt(0);
    }

    private static ExpressionToParse<Condition, BooleanOperator> convertToETP(String s)
    {
        ExpressionToParse<Condition, BooleanOperator> returnList = new ExpressionToParse<>();

        int i = 0;
        while(i < s.length())
        {
            char c = s.charAt(i);
            if(c == '(')
            {
                i = handleBracketExpression(returnList, s, i);
            }
            else if(c == '!' && s.charAt(i + 1) != '=')
            {
                i = handleUnary(returnList, s, i);
            }
            else if(c == '&' || c == '|')
            {
                returnList.addOperator(BooleanOperator.getOperator(s.substring(i, i + 2)));
                i += 2;
            }
            else if(c == '^')
            {
                returnList.addOperator(BooleanOperator.getOperator("" + c));
                i++;
            }
            else if(Character.isAlphabetic(c) || Character.isDigit(c) || c == '-')
            {
                i = handleComparison(returnList, s, i);
            }
            else
            {
                i++;
            }
        }

        return returnList;
    }

    private static int handleBracketExpression(ExpressionToParse<Condition, BooleanOperator> eAP, String s, int i)
    {
        int startIndex = i + 1;
        i = getEndOfBracketExpression(s, startIndex);
        if(startIndex > 2)
        {
            if(s.charAt(startIndex - 3) == '^')
            {
                eAP.add(BooleanOperator.getOperator("" + s.charAt(startIndex - 3)),
                        convertToETP(s.substring(startIndex, i)));
            }
            else
            {
                eAP.add(BooleanOperator.getOperator(s.substring(startIndex - 4, startIndex - 2)),
                        convertToETP(s.substring(startIndex, i)));
            }
        }
        else
        {
            eAP.add(convertToETP(s.substring(startIndex, i)));
        }

        return i;
    }

    private static int handleUnary(ExpressionToParse<Condition, BooleanOperator> eAP, String s, int i)
    {
        int startIndex = i + 2;
        i = getEndOfBracketExpression(s, startIndex);
        eAP.addOperand(new UnaryCondition(parse(s.substring(startIndex, i))));

        return i;
    }

    private static int handleComparison(ExpressionToParse<Condition, BooleanOperator> eAP, String s, int i)
    {
        int startIndex = i;
        i = getEndOfComparison(s, startIndex);
        String[] expressions = s.substring(startIndex, i).split("<|>|<=|>=|==|!=");
        String operator = s.substring(startIndex + expressions[0].length(), i - expressions[1].length());
        eAP.addOperand(new Comparison(Arithmetic.parse(expressions[0]), Arithmetic.parse(expressions[1]),
                Comparator.getOperator(operator)));

        return i;
    }

    private static int getEndOfBracketExpression(String s, int i)
    {
        int bracketCount = 1;
        while(bracketCount != 0)
        {
            i++;
            if(s.charAt(i) == '(')
            {
                bracketCount++;
            }
            else if(s.charAt(i) == ')')
            {
                bracketCount--;
            }
        }

        return i;
    }

    private static int getEndOfComparison(String s, int i)
    {
        while(i != s.length() - 1 && s.charAt(i) != '&' && s.charAt(i) != '|' && s.charAt(i) != '^')
        {
            i++;
        }

        if(s.charAt(i - 2) == ')')
        {
            i -= 2;
        }

        return i;
    }
}