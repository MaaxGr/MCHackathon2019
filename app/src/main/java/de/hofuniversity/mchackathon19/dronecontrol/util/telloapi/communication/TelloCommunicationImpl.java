package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.communication;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.exception.TelloCommandException;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.exception.TelloConnectionException;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.model.TelloDroneImpl;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.model.command.TelloCommand;

/**
 * Sending and receiving data from DJI Tello drone.
 */
public class TelloCommunicationImpl implements TelloCommunication {

    private static final Logger logger = Logger.getLogger(TelloCommunicationImpl.class.getName());

    /**
     * Datagram connection to the Tello drone.
     */
    private DatagramSocket ds;

    /**
     * Drone's IP address.
     */
    private InetAddress ipAddress;

    /**
     * Drones UDP PORT.
     */
    private Integer udpPort;

    public TelloCommunicationImpl() throws TelloConnectionException {
        try {
            this.ipAddress = InetAddress.getByName(TelloDroneImpl.IP_ADDRESS);
            this.udpPort = TelloDroneImpl.UDP_PORT;
        } catch (UnknownHostException e) {
            throw new TelloConnectionException("Unknown host");
        }
    }

    @Override
    public boolean connect() {
        try {
            ds = new DatagramSocket(udpPort);
            ds.connect(ipAddress, udpPort);
        } catch (SocketException e) {
            logger.info("Connection to the drone could not be established.");
            throw new TelloConnectionException("Could not connect");
        }
        return true;
    }

    @Override
    public boolean executeCommand(final TelloCommand telloCommand) {
        if (telloCommand == null) {
            logger.info("TelloCommand was null");
            return false;
        }
        if (!ds.isConnected()) {
            logger.info("Tello connection lost");
            return false;
        }

        final String command = telloCommand.composeCommand();
        logger.info("Executing tello command: " + command);

        try {
            sendData(command);
            String response = receiveData();
            logger.info("Tello response: " + response);
        } catch (IOException e) {
            logger.info("Exception occurred during sending and receiving command");
            logger.info(e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public Map<String, String> getTelloOnBoardData(List<String> valuesToBeObtained) {
        Map<String, String> dataMap = new HashMap<>();

        return dataMap;
    }

    private String executeReadCommand(TelloCommand telloCommand) throws TelloCommandException {
        if (telloCommand == null) {
            logger.info("TelloCommand was null");
            throw new TelloCommandException("Command was empty");
        }
        if (!ds.isConnected()) {
            logger.info("Tello connection lost");
            throw new TelloConnectionException("No connection");
        }

        final String command = telloCommand.composeCommand();
        logger.info("Executing tello command: " + command);

        try {
            sendData(command);
            String response = receiveData();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void executeCommands(List<TelloCommand> telloCommandList) {

    }

    public void disconnect() {

    }


    private void sendData(String data) throws IOException {
        byte[] sendData = data.getBytes();
        final DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress,
                udpPort);
        ds.send(sendPacket);
    }

    private String receiveData() throws IOException {
        byte[] receiveData = new byte[1024];
        final DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        ds.receive(receivePacket);
        return trimExecutionResponse(receiveData, receivePacket);
    }

    private String trimExecutionResponse(byte[] response, DatagramPacket receivePacket) {
        response = Arrays.copyOf(response, receivePacket.getLength());
        return new String(response, StandardCharsets.UTF_8);
    }


}
