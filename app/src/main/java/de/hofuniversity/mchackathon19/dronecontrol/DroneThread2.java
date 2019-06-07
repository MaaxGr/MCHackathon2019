package de.hofuniversity.mchackathon19.dronecontrol;

import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.Takeoff;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.droneControl.DroneControl;

public class DroneThread2 extends Thread {

    @Override
    public void run() {

        DroneControl dC = new DroneControl();

        dC.executeCommand("takeoff");


        super.run();
    }
}
