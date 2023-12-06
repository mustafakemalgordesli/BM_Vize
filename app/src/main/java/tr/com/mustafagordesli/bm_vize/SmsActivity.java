package tr.com.mustafagordesli.bm_vize;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.Manifest;

public class SmsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            Log.d("---------------","Okuma izni var");
        } else {
            Log.d("------------", "Okuma izni yok");
        }
        viewSettings();
    }

    private void viewSettings() {
        Button btnSend = findViewById(R.id.btnSend);

        EditText phoneInput = (EditText)findViewById(R.id.inputPhone);
        EditText messageInput = (EditText)findViewById(R.id.inputMessage);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(ContextCompat.checkSelfPermission(SmsActivity.this, android.Manifest.permission.SEND_SMS)
                            == PackageManager.PERMISSION_GRANTED){
                        String number=phoneInput.getText().toString();
                        String msg=messageInput.getText().toString();
                        if(!number.isEmpty() && !msg.isEmpty()){
                            SmsManager sms=SmsManager.getDefault();
                            sms.sendTextMessage(number, null, msg, null,null);
                        }
                    }else {
                        ActivityCompat.requestPermissions(SmsActivity.this,new String[]{Manifest.permission.SEND_SMS}, 100);
                    }

                }  catch (Exception e){
                    Toast.makeText(SmsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /*private void requestPermission() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {

        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, 98);
        }
    }*/

    // @Override
    // public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
    //     super.onRequestPermissionsResult(requestCode,permissions,grantResults);

    //     if(requestCode == 100 && grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

    //     }else{

    //     }
    // }

}