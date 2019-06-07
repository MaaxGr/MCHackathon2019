package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.model.command;

public abstract class AbstractTelloCommand implements TelloCommand {

  protected String command;

  public AbstractTelloCommand(String command) {
    this.command = command;
  }

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

}
