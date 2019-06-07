package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage.Arithmetic;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage.Assignment;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage.BinaryArithmetic;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage.BinaryCondition;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage.Break;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage.Comparison;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage.Continue;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage.DroneCommand;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage.IfThenElse;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage.Number;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage.Out;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage.Return;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage.Sleep;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage.Statement;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage.UnaryArithmetic;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage.UnaryCondition;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage.Variable;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commandLanguage.While;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.droneControl.DroneControl;

public class OwnCommand extends Command {
    private String name;
    private List<Statement> statements;
    private Map<String, Double> parameters;
    private Map<Integer, String> parameterPlaces;
    private Map<String, Double> variables;
    private List<String> readVariables;
    private List<String> readCommands;

    private DroneControl dC;

    private boolean breakF;
    private boolean continueF;
    private boolean returnF;

    public String getName() {
        return name;
    }

    public void setDC(DroneControl dC) {
        this.dC = dC;
    }

    public OwnCommand(String name, List<Statement> statements, Map<String, Double> parameters,
                      Map<Integer, String> parameterPlaces, Map<String, Double> variables) {
        this.name = name;
        this.statements = statements;
        this.parameters = parameters;
        this.parameterPlaces = parameterPlaces;
        this.variables = variables;

        String[] readableVariables = {"mid", "x", "y", "z", "pitch", "roll", "yaw", "vgx", "vgy", "vgz", "templ",
                "temph", "tof", "h", "bat", "baro", "time", "agx", "agy", "agz"};
        readVariables = Arrays.asList(readableVariables);

        String[] readableCommands = {"battery", "height", "speed", "time"};
        readCommands = Arrays.asList(readableCommands);
    }

    public Command reverse() {
        return null;
    }

    public void execute(String command) {
        String[] commandArray = command.split(" ");
        for (int i = 1; i < commandArray.length; i++) {
            parameters.put(parameterPlaces.get(i), Double.parseDouble(commandArray[i]));
        }

        for (Statement s : statements) {
            s.accept(this);
            if (returnF) {
                return;
            }
        }
    }

    public void visit(Assignment a) {
        if (variables.containsKey(a.getName())) {
            variables.put(a.getName(), a.getExpression().accept(this));
        } else if (parameters.containsKey(a.getName())) {
            parameters.put(a.getName(), a.getExpression().accept(this));
        }
    }

    public void visit(Break b) {
        breakF = true;
    }

    public void visit(Continue c) {
        continueF = true;
    }

    public void visit(DroneCommand d) {
        String commandLine = d.getCommand();
        if (commandLine.contains(" ")) {
            // Flip with char as parameter and the whole command with parameter in the
            // commandLine
            dC.sendCommand(Command.getCommand(commandLine));
        } else {
            Command command = Command.getCommand(commandLine);
            for (Arithmetic a : d.getParameters()) {
                commandLine += " " + a.accept(this);
            }
            dC.sendCommand(command);
        }
    }

    public void visit(IfThenElse i) {
        if (i.getCondition().accept(this)) {
            for (Statement s : i.getThenStatements()) {
                s.accept(this);
                if (returnF || breakF || continueF) {
                    return;
                }
            }
        } else if (i.getElseStatements() != null) {
            for (Statement s : i.getElseStatements()) {
                s.accept(this);
                if (returnF || breakF || continueF) {
                    return;
                }
            }
        }
    }

    public void visit(Out o) {
        System.out.println(o.getA().accept(this));
    }

    public void visit(Return r) {
        returnF = true;
    }

    public void visit(Sleep s) {
        try {
            Thread.sleep((long) s.getA().accept(this));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void visit(While w) {
        while (w.getCondition().accept(this)) {
            for (Statement s : w.getStatements()) {
                s.accept(this);
                if (returnF || breakF) {
                    returnF = false;
                    breakF = false;
                    return;
                } else if (continueF) {
                    continueF = false;
                    break;
                }
            }
        }
    }

    public double visit(BinaryArithmetic b) {
        switch (b.getOperator()) {
            case Addition:
                return b.getLeft().accept(this) + b.getRight().accept(this);
            case Subtraction:
                return b.getLeft().accept(this) - b.getRight().accept(this);
            case Multiplication:
                return b.getLeft().accept(this) * b.getRight().accept(this);
            case Division:
                return b.getLeft().accept(this) / b.getRight().accept(this);
            case Modulo:
                return b.getLeft().accept(this) % b.getRight().accept(this);
            default:
                return 0;
        }
    }

    public double visit(Number n) {
        return n.getNumber();
    }

    public double visit(UnaryArithmetic u) {
        return -u.accept(this);
    }

    public double visit(Variable v) {
        String name = v.getName();
        if (variables.containsKey(name)) {
            return variables.get(name);
        } else if (parameters.containsKey(name)) {
            return parameters.get(name);
        } else if (readVariables.contains(name)) {
            return dC.getOutputMap().get(name);
        } else if (readCommands.contains(name)) {
            return Double.parseDouble(dC.sendCommand(Command.getCommand(name + "?")));
        } else {
            return Double.NaN;
        }
    }

    public boolean visit(BinaryCondition b) {
        switch (b.getOperator()) {
            case And:
                return b.getLeft().accept(this) && b.getRight().accept(this);
            case Or:
                return b.getLeft().accept(this) || b.getRight().accept(this);
            case XOr:
                return b.getLeft().accept(this) ^ b.getRight().accept(this);
            default:
                return false;
        }
    }

    public boolean visit(Comparison c) {
        switch (c.getOperator()) {
            case Equals:
                return c.getLeft().accept(this) == c.getRight().accept(this);
            case Greater:
                return c.getLeft().accept(this) > c.getRight().accept(this);
            case GreaterEquals:
                return c.getLeft().accept(this) >= c.getRight().accept(this);
            case Less:
                return c.getLeft().accept(this) < c.getRight().accept(this);
            case LessEquals:
                return c.getLeft().accept(this) <= c.getRight().accept(this);
            case NotEqual:
                return c.getLeft().accept(this) != c.getRight().accept(this);
            default:
                return false;
        }
    }

    public boolean visit(UnaryCondition u) {
        return !u.getOperand().accept(this);
    }
}