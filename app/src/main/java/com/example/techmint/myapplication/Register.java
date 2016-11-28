package com.example.techmint.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button bRegister;
    EditText etName, etStudentId, etUsername, etPassword;
    TextView tv_uids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        StringBuilder sb = new StringBuilder();

        TelephonyManager telMan = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String imei = telMan.getDeviceId();

        WifiManager wm = (WifiManager) getSystemService(android.content.Context.WIFI_SERVICE);
        String mac_adr = wm.getConnectionInfo().getMacAddress();

        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
                String name = etName.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                int studentid = Integer.parseInt("5");
                User regiesteredData = new User(name, studentid, username, password);
    }
}