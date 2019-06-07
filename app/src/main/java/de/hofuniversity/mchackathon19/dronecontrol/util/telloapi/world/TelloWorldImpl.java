package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.world;


import java.util.logging.Logger;

import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.communication.TelloCommunication;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.communication.TelloCommunicationImpl;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.model.TelloConnection;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.model.TelloDrone;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.model.TelloDroneImpl;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.model.TelloFlip;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.model.command.BasicTelloCommand;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.model.command.TelloCommand;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.model.command.TelloCommandValues;

public class TelloWorldImpl implements TelloWorld {

  private static final Logger logger = Logger.getLogger(TelloWorldImpl.class.getName());


  private TelloDrone telloDrone;

  private TelloCommunication telloCommunication;

  public TelloWorldImpl() {
    telloDrone = new TelloDroneImpl();
    telloCommunication = new TelloCommunicationImpl();
  }

  @Override
  public void connect() {
    boolean connectionSuccessful = telloCommunication.connect();
    if (connectionSuccessful) {
      telloDrone.setTelloConnection(TelloConnection.CONNECTED);
      logger.info("Connected!");
    }
  }

  @Override
  public void disconnect() {

  }

  @Override
  public void enterCommandMode() {
    TelloCommand command = new BasicTelloCommand(TelloCommandValues.COMMAND_MODE);
    boolean executionSuccessful = telloCommunication.executeCommand(command);
    if (executionSuccessful) {
      logger.info("Entering command mode successful");
    }
  }


  @Override
  public void takeOff() {
    TelloCommand command = new BasicTelloCommand(TelloCommandValues.TAKE_OFF);
    boolean executionSuccessful = telloCommunication.executeCommand(command);
    if (executionSuccessful) {
      logger.info("Taking off command was executed successfully");
    }
  }

  @Override
  public void land() {
    TelloCommand command = new BasicTelloCommand(TelloCommandValues.LAND);
    boolean executionSuccessful = telloCommunication.executeCommand(command);
    if (executionSuccessful) {
      logger.info("Landing command was executed successfully");
    }
  }

  @Override
  public void doFlip(TelloFlip telloFlip) {

  }

  @Override
  public void setSpeed(Integer speed) {

  }

  @Override
  public void forward(Integer distance) {

  }

  @Override
  public void backward(Integer distance) {

  }

  @Override
  public void right(Integer distance) {

  }

  @Override
  public void left(Integer distance) {

  }

  @Override
  public void rotatateRight(Integer angle) {

  }

  @Override
  public void rotateLeft(Integer angle) {

  }

  public TelloDrone getTelloDrone() {
    return telloDrone;
  }
}
