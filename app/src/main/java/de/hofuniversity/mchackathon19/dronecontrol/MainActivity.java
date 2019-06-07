package de.hofuniversity.mchackathon19.dronecontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private DroneThread droneThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drone_controller);

        this.droneThread = new DroneThread();
        droneThread.start();

        View takeOffBtn = findViewById(R.id.btn_takeoff);
        takeOffBtn.setOnClickListener(btn -> droneThread.executeCommand("start"));

        View landBtn = findViewById(R.id.btn_land);
        landBtn.setOnClickListener(btn -> droneThread.executeCommand("land"));

        View emergencyBtn = findViewById(R.id.btn_emergency);
        emergencyBtn.setOnClickListener(btn -> droneThread.executeCommand("stop"));

    }

}
