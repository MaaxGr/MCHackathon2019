package de.hofuniversity.mchackathon19.dronecontrol;

import java.util.concurrent.SynchronousQueue;

import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.world.TelloWorld;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.world.TelloWorldImpl;

public class DroneThread extends Thread {

    private SynchronousQueue<String> commandQueue = new SynchronousQueue<>();
    private TelloWorld telloWorld = new TelloWorldImpl();
    private boolean stop = false;

    @Override
    public void run() {

        telloWorld.connect();
        System.out.println("connected");

        telloWorld.enterCommandMode();
        System.out.println("entered command mode");

        while (!stop) {

            if (!commandQueue.isEmpty()) {

                switch (commandQueue.poll()) {
                    case "start":
                        telloWorld.takeOff();
                        System.out.println("start");
                        break;
                    case "land":
                        telloWorld.land();
                        System.out.println("land");
                        break;
                    case "stop":
                        telloWorld.land();
                        telloWorld.disconnect();
                        stop = true;
                        break;
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void executeCommand(String command) {
        try {
            commandQueue.put(command);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
