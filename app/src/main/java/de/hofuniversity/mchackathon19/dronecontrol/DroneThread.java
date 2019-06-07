package de.hofuniversity.mchackathon19.dronecontrol;

import android.util.Log;

import java.util.LinkedList;
import java.util.Queue;

import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.world.TelloWorld;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi.world.TelloWorldImpl;

public class DroneThread extends Thread {

    private Queue<String> commandQueue;
    private TelloWorld telloWorld;
    private boolean stop;

    @Override
    public void run() {
        commandQueue = new LinkedList<>();
        telloWorld = new TelloWorldImpl();
        stop = false;

        Log.d("App", "start thread");

        telloWorld.connect();
        System.out.println("connected");

        telloWorld.enterCommandMode();
        System.out.println("entered command mode");


        telloWorld.takeOff();

        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        telloWorld.land();


        /*
        while (!stop) {

            Log.d("App", "Searching for commands");


            if (!commandQueue.isEmpty()) {

                String command = commandQueue.poll();

                Log.d("App", "Found command: " + command);

                switch (command) {
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

                        default:
                            Log.d("App", "Malformed command");
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        */

    }

    public void executeCommand(String command) {
        System.out.println("command: " + command);

        commandQueue.add(command);
    }


}
