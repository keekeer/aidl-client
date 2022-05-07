package cn.ismiss.exo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Intent intentService = new Intent();
    final String BOUNDSERVICE_PACKAGE = "cn.ismiss.exo";
    final String BOUNDSERVICE_CLASS = ".PayService";
    private Iservice iservice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mTextView = findViewById(R.id.tv_test);

        intentService.setClassName(BOUNDSERVICE_PACKAGE,
                BOUNDSERVICE_PACKAGE + BOUNDSERVICE_CLASS);
        MyConn conn = new MyConn();
        bindService(intentService, conn, BIND_AUTO_CREATE);

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iservice != null) {
                    try {
                        boolean success = iservice.callPay("abc", "123", 4999);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    private class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iservice = Iservice.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}