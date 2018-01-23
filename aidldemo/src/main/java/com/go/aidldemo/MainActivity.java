package com.go.aidldemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.go.android.course.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {




    private IMyAidlInterface iMyAidlInterface;

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn_aidl);
        Intent intent = new Intent("com.go.android.course.MY_REMOTE_SERVICE");
        intent.setPackage("com.go.android.course");

        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
                Toast.makeText(MainActivity.this,"连接成功", Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent(Intent.ACTION_MAIN);
                intent1.addCategory(Intent.CATEGORY_LAUNCHER);
                ComponentName cn = new ComponentName("com.go.android.course", "com.go.android.course.communication.CommunicationMainActivity");
                intent1.setComponent(cn);
                startActivity(intent1);

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

                iMyAidlInterface = null;
            }
        }, BIND_AUTO_CREATE);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Toast.makeText(MainActivity.this, iMyAidlInterface.getGame(), Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }






}
