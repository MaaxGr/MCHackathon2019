package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage;

import java.io.*;
import java.util.*;

import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.OwnCommand;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.droneControl.DroneControl;

public class CommandParser
{
    private String name;
    private List<Statement> statements;
    private Map<String, Double> parameters;
    private Map<Integer, String> parameterPlaces;
    private Map<String, Double> variables;

    private BufferedReader reader;

    private IfThenElse lastIf;

    public OwnCommand parse(File file, DroneControl dC) throws IOException
    {
        name = file.getName();
        statements = new ArrayList<>();
        parameters = new HashMap<String, Double>();
        parameterPlaces = new HashMap<Integer, String>();
        variables = new HashMap<String, Double>();

        reader = new BufferedReader(new FileReader(file));

        readFirstTwoLines();
        readStatements();

        return new OwnCommand(name, statements, parameters, parameterPlaces, variables);
    }

    private void readFirstTwoLines() throws IOException
    {
        String firstLine = reader.readLine();
        correctFirstTwoLines(firstLine);
        String[] firstlineArray = firstLine.split(", ");
        for(int i = 0; i < firstlineArray.length; i++)
        {
            parameterPlaces.put(i, firstlineArray[i]);
            parameters.put(firstlineArray[i], 0d);
        }

        String secondLine = reader.readLine();
        correctFirstTwoLines(secondLine);
        String[] secondlineArray = secondLine.split(", ");
        for(int i = 0; i < secondlineArray.length; i++)
        {
            variables.put(secondlineArray[i], 0d);
        }
    }

    private void correctFirstTwoLines(String line)
    {
        for(int i = 0; i < line.length(); i++)
        {
            if(line.charAt(i) == ',' && line.charAt(i + 1) != ' ')
            {
                line = line.substring(0, i + 1) + ' ' + line.substring(i + 1);
            }
        }
    }

    private void readStatements() throws IOException
    {
        String line = reader.readLine();

        while(line != null)
        {
            line = correctLine(line);
            line = removeIndention(line);
            Statement statement = readStatement(line);
            if(statement != null)
            {
                statements.add(statement);
            }

            line = reader.readLine();
        }
    }

    private List<Statement> readStatementsWhileNotClosed() throws IOException
    {
        List<Statement> enclosedStatements = new ArrayList<>();
        String line = reader.readLine();
        while(!line.contains("}"))
        {
            line = correctLine(line);
            line = removeIndention(line);

            Statement statement = readStatement(line);
            if(statement != null)
            {
                enclosedStatements.add(statement);
            }

            line = reader.readLine();
        }

        return enclosedStatements;
    }

    private String correctLine(String line)
    {
        for(int i = 0; i < line.length(); i++)
        {
            char characterToTest = line.charAt(i);
            switch(characterToTest)
            {
            case '+':
            case '-':
            case '*':
            case '/':
            case '%':
            case '~':
            case '(':
            case ')':
            case '{':
            case '}':
            case ',':
                if(i > 0 && line.charAt(i - 1) != ' ')
                {
                    line = line.substring(0, i) + ' ' + line.substring(i);
                    i++;
                }
                if(i < line.length() - 1 && line.charAt(i + 1) != ' ')
                {
                    line = line.substring(0, i + 1) + ' ' + line.substring(i + 1);
                }
                break;
            case '&':
            case '|':
                if(i > 0 && line.charAt(i - 1) != ' ' && line.charAt(i - 1) != characterToTest)
                {
                    line = line.substring(0, i) + ' ' + line.substring(i);
                    i++;
                }
                if(i < line.length() - 1 && line.charAt(i + 1) != ' ' && line.charAt(i + 1) != characterToTest)
                {
                    line = line.substring(0, i + 1) + ' ' + line.substring(i + 1);
                }
                break;
            case '<':
            case '>':
                if(i > 0 && line.charAt(i - 1) != ' ')
                {
                    line = line.substring(0, i) + ' ' + line.substring(i);
                    i++;
                }
                if(i < line.length() - 1 && line.charAt(i + 1) != ' ' && line.charAt(i + 1) != '=')
                {
                    line = line.substring(0, i + 1) + ' ' + line.substring(i + 1);
                }
                break;
            case '=':
                if(i > 0)
                {
                    char charInFront = line.charAt(i - 1);
                    if(charInFront != ' ' && charInFront != '=' && charInFront != '<' && charInFront != '>')
                    {
                        line = line.substring(0, i) + ' ' + line.substring(i);
                        i++;
                    }
                }
                if(i < line.length() - 1 && line.charAt(i + 1) != ' ' && line.charAt(i + 1) != '=')
                {
                    line = line.substring(0, i + 1) + ' ' + line.substring(i + 1);
                }
            }
        }

        return line;
    }

    private String removeIndention(String line)
    {
        int indentions = 0;
        while(indentions < line.length() && line.charAt(indentions) == ' ')
        {
            indentions++;
        }

        return line.substring(indentions);
    }

    private Statement readStatement(String line) throws IOException
    {
        if(line.contains("else"))
        {
            lastIf.addElse(readStatementsWhileNotClosed());
            return null;
        }
        else if(line.length() > 2 && line.substring(0, 2).equals("if"))
        {
            return readIf(line);
        }
        else if(line.length() > 5 && line.substring(0, 5).equals("while"))
        {
            return readWhile(line);
        }
        else if(line.contains(" = "))
        {
            return readAssignment(line);
        }
        else if(line.contains("{") || line.length() == 0)
        {
            return null;
        }
        else
        {
            return Command.parse(line);
        }
    }

    private Assignment readAssignment(String line)
    {
        String[] lineArray = line.split(" =");
        return new Assignment(lineArray[0], Arithmetic.parse(lineArray[1]));
    }

    private IfThenElse readIf(String line) throws IOException
    {
        Condition condition = Condition.parse(line.substring(2));
        List<Statement> thenStatements = readStatementsWhileNotClosed();

        lastIf = new IfThenElse(condition, thenStatements, null);
        return lastIf;
    }

    private While readWhile(String line) throws IOException
    {
        Condition condition = Condition.parse(line.substring(5));
        List<Statement> whileStatements = readStatementsWhileNotClosed();

        return new While(condition, whileStatements);
    }
}