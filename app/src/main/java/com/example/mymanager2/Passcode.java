package com.example.mymanager2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.hanks.passcodeview.PasscodeView;

public class Passcode extends AppCompatActivity {

    PasscodeView passcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);

        //assign variables
        passcodeView = findViewById(R.id.passcode_view);
        passcodeView.setPasscodeLength(4)//Set password length
                .setLocalPasscode("1234")//set password
                .setListener(new PasscodeView.PasscodeViewListener() {
                    @Override
                    public void onFail() {
                        //if password will wrong
                        Toast.makeText(getApplicationContext(),
                                "Passcode is wrong",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(String number) {
                        //if passcode will correct
                        Intent intent= new Intent(Passcode.this,Confidential.class);
                        startActivity(intent);

                    }
                });

    }
}