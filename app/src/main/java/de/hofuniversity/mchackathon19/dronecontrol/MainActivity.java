package de.hofuniversity.mchackathon19.dronecontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private DroneThread droneThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.droneThread = new DroneThread();
        droneThread.start();
    }

    public void btn_takeoff(View view) {
        droneThread.executeCommand("start");
    }

    public void btn_land(View view) {
        droneThread.executeCommand("land");
    }

    public void btn_emergency(View view) {
        droneThread.executeCommand("stop");
    }

}
