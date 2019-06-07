package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.model.command;

public interface TelloCommand {

  /**
   * Compose the command with all the parameters neccessary.
   *
   * @return Composed command.
   */
  String composeCommand();
}