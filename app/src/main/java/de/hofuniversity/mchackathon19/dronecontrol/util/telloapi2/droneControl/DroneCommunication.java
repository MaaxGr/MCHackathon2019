package de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.droneControl;
import java.io.*;
import java.net.*;

public class DroneCommunication
{
    private InetAddress comAddress;
    private int comPort;
    private DatagramSocket comSocket;
    private DatagramSocket stateSocket;

    public DroneCommunication(String host, int comPort, int statePort)
    {
        try
        {
            comAddress = InetAddress.getByName(host);
        } catch(UnknownHostException e)
        {
            e.printStackTrace();
        }

        this.comPort = comPort;

        try
        {
            comSocket = new DatagramSocket(comPort);
            stateSocket = new DatagramSocket(statePort);
        } catch(SocketException e)
        {
            e.printStackTrace();
        }

        comSocket.connect(comAddress, comPort);

        setupCommand();
    }

    private void setupCommand()
    {
        byte[] buf = "command".getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, comAddress, comPort);

        try
        {
            comSocket.send(packet);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            comSocket.receive(receivePacket);
        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public String sendCommand(String command)
    {
        byte[] buffer = command.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, comAddress, comPort);

        try
        {
            comSocket.send(packet);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            comSocket.receive(receivePacket);

            return new String(receivePacket.getData(), 0, receivePacket.getLength());
        } catch(IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public String getOutput()
    {
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try
        {
            stateSocket.receive(receivePacket);
        } catch(IOException e)
        {
            e.printStackTrace();
        }
        return new String(receivePacket.getData(), 0, receivePacket.getLength());
    }
}