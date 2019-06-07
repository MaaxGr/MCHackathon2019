package de.hofuniversity.mchackathon19.dronecontrol;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.BatteryQM;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.Emergency;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.Land;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.OwnCommand;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.commands.Takeoff;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.droneControl.DroneCommunication;
import de.hofuniversity.mchackathon19.dronecontrol.util.telloapi2.droneControl.DroneControl;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drone_controller);

        View landBtn = findViewById(R.id.btn_land);
        View emergencyBtn = findViewById(R.id.btn_emergency);
        ImageButton takeOffBtn = findViewById(R.id.btn_takeoff);
        TextView txtBat = findViewById(R.id.tv_battery);


        emergencyBtn.setOnClickListener(btn -> new Thread() {
            @Override
            public void run() {
                DroneControl DrCo = new DroneControl();
                DrCo.sendCommand(new BatteryQM());
                super.run();
            }
        }.start());

        takeOffBtn.setOnClickListener(btn -> new Thread() {
            @Override
            public void run() {
                DroneControl DrCo = new DroneControl();
                DrCo.sendCommand(new Takeoff());
                super.run();
            }
        }.start());

        landBtn.setOnClickListener(btn -> new Thread() {
            @Override
            public void run() {
                DroneControl DrCo = new DroneControl();
                DrCo.sendCommand(new Land());
                super.run();
            }
        }.start());

        emergencyBtn.setOnClickListener(btn -> new Thread() {
            @Override
            public void run() {
                DroneControl DrCo = new DroneControl();
                DrCo.sendCommand(new Emergency());
                super.run();
            }
        }.start());
    }

}
