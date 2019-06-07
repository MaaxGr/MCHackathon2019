package de.hofuniversity.mchackathon19.dronecontrol;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DroneThread droneThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drone_controller);

        this.droneThread = new DroneThread();
        droneThread.start();

        ImageButton takeOffBtn = findViewById(R.id.btn_takeoff);
        takeOffBtn.setOnClickListener(btn -> {
            Toast.makeText(this, "test", Toast.LENGTH_LONG).show();
            System.out.println("Start test");
            droneThread.executeCommand("start");
        });

        View landBtn = findViewById(R.id.btn_land);
        landBtn.setOnClickListener(btn -> droneThread.executeCommand("land"));

        View emergencyBtn = findViewById(R.id.btn_emergency);
        emergencyBtn.setOnClickListener(btn -> droneThread.executeCommand("stop"));
    }

}
