package de.hofuniversity.mchackathon19.dronecontrol;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.Queue;

import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.Emergency;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.Land;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.Takeoff;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.droneControl.DroneControl;

public class DroneThread2 extends Thread {

    private WeakReference<MainActivity> mainActivityWeakReference;

    private Queue<String> commandQueue;
    private DroneControl droneControl;
    private boolean stop;

    public DroneThread2(MainActivity mainActivity) {
        this.mainActivityWeakReference = new WeakReference<>(mainActivity);
    }

    @Override
    public void run() {
        commandQueue = new LinkedList<>();
        stop = false;

        Log.d("App", "start thread");


        droneControl = new DroneControl();
        droneControl.sendCommand(new Takeoff());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        droneControl.sendCommand(new Land());


        Log.d("App", "connected");

        while (!stop) {

            Log.d("App", "Searching for commands");


            if (!commandQueue.isEmpty()) {

                String command = commandQueue.poll();

                Log.d("App", "Found command: " + command);

                switch (command) {
                    case "start":
                        droneControl.sendCommand(new Takeoff());
                        System.out.println("start");
                        break;
                    case "land":
                        droneControl.sendCommand(new Land());
                        System.out.println("land");
                        break;
                    case "stop":
                        droneControl.sendCommand(new Emergency());
                        stop = true;
                        break;
                    default:
                        Log.d("App", "Malformed command");
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
        System.out.println("command: " + command);

        commandQueue.add(command);
    }
}
