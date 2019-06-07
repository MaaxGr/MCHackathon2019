package de.hofuniversity.mchackathon19.dronecontrol;

import android.util.Log;

import java.util.LinkedList;
import java.util.Queue;

import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.Land;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.Takeoff;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.droneControl.DroneControl;

public class DroneThread2 extends Thread {

    private Queue<String> commandQueue;
    private DroneControl droneControl;
    private boolean stop;

    @Override
    public void run() {
        commandQueue = new LinkedList<>();
        stop = false;

        Log.d("App", "start thread");


        droneControl = new DroneControl();
        Log.d("App", "connected");

        droneControl.sendCommand(new Takeoff());
        Log.d("App", "take off");

        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        droneControl.sendCommand(new Land());
        Log.d("App", "land");

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
