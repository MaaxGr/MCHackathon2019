package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;

public enum ArithmeticOperator
{
    Addition, Subtraction, Multiplication, Division, Modulo;

    static ArithmeticOperator getOperator(char operator)
    {
        switch(operator)
        {
        case '+':
            return Addition;
        case '-':
            return Subtraction;
        case '*':
            return Multiplication;
        case '/':
            return Division;
        case '%':
            return Modulo;
        default:
            return null;
        }
    }
}
