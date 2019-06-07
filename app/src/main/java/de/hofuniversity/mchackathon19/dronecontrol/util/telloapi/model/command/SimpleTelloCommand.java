package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.model.command;

public class SimpleTelloCommand extends AbstractTelloCommand {

  public SimpleTelloCommand(String command) {
    super(command);
  }

  @Override
  public String composeCommand() {
    return null;
  }
}
