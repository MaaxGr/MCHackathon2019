package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;

public abstract class Arithmetic extends Expression
{
    public static Arithmetic parse(String s)
    {
        ExpressionToParse<Arithmetic, ArithmeticOperator> eTP = convertToETP(s);

        for(int i = eTP.size() - 2; i > 0; i--)
        {
            ArithmeticOperator op = eTP.getOperatorAt(i);
            if(op == ArithmeticOperator.Multiplication || op == ArithmeticOperator.Division
                    || op == ArithmeticOperator.Modulo)
            {
                eTP.concat(i);
            }
        }

        while(eTP.size() > 1)
        {
            eTP.concat(eTP.size() - 2);
        }

        return eTP.getOperandAt(0);
    }

    private static ExpressionToParse<Arithmetic, ArithmeticOperator> convertToETP(String s)
    {
        ExpressionToParse<Arithmetic, ArithmeticOperator> returnList = new ExpressionToParse<>();

        int i = 0;
        while(i < s.length())
        {
            char c = s.charAt(i);
            if(c == '(')
            {
                i = handleBracketExpression(returnList, s, i);
            }
            else if(c == '-' && i == 0)
            {
                i = handleUnary(returnList, s, i);
            }
            else if(c == '+' || c == '-' || c == '*' || c == '/' || c == '%')
            {
                returnList.addOperator(ArithmeticOperator.getOperator(c));
                i++;
            }
            else if(Character.isDigit(c))
            {
                i = handleNumber(returnList, s, i);
            }
            else if(Character.isAlphabetic(c))
            {
                i = handleVariable(returnList, s, i);
            }
            else
            {
                i++;
            }
        }

        return returnList;
    }

    private static int handleBracketExpression(ExpressionToParse<Arithmetic, ArithmeticOperator> eAP, String s, int i)
    {
        int startIndex = i + 1;
        i = getEndOfBracketExpression(s, startIndex);
        if(startIndex >= 2)
        {
            eAP.add(ArithmeticOperator.getOperator(s.charAt(startIndex - 3)), convertToETP(s.substring(startIndex, i)));
        }
        else
        {
            eAP.add(convertToETP(s.substring(startIndex, i)));
        }

        return i;
    }

    private static int handleUnary(ExpressionToParse<Arithmetic, ArithmeticOperator> eAP, String s, int i)
    {
        if(s.charAt(i + 2) == '(')
        {
            int startIndex = i + 2;
            i = getEndOfBracketExpression(s, startIndex);
            eAP.addOperand(new UnaryArithmetic(parse(s.substring(startIndex, i))));
        }
        else
        {
            int startIndex = i + 2;
            i = getEndhOfNumberOrVariable(s, startIndex);
            eAP.addOperand(new UnaryArithmetic(new Number(Double.parseDouble(s.substring(startIndex, i)))));
        }
        return i;
    }

    private static int handleNumber(ExpressionToParse<Arithmetic, ArithmeticOperator> eAP, String s, int i)
    {
        int end = getEndhOfNumberOrVariable(s, i);
        eAP.addOperand(new Number(Double.parseDouble(s.substring(i, end + 1))));

        return end + 1;
    }

    private static int handleVariable(ExpressionToParse<Arithmetic, ArithmeticOperator> eAP, String s, int i)
    {
        int end = getEndhOfNumberOrVariable(s, i);
        eAP.addOperand(new Variable(s.substring(i, end + 1)));

        return end + 1;
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

    private static int getEndhOfNumberOrVariable(String s, int i)
    {
        while(i <= s.length() - 1 && s.charAt(i) != ' ')
        {
            i++;
        }
        return i - 1;
    }
}
