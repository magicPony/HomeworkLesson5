package com.example.taras.homeworklesson5;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**public class MyClass {
     public static final int SOME_CONSTANT = 42;
     public int publicField;
     private static MyClass sSingleton;
     int mPackagePrivate;
     private int mPrivate;
     protected int mProtected;
     }

     Constants
     Fields
     Constructors
     Override methods and callbacks (public or private)
     Public methods
     Private methods
     Inner classes or interfaces
     */

    private EditText etSubject, etRecipient, etMessage;
    private Button btnCall, btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSubject = (EditText) findViewById(R.id.text_subject_field);
        etRecipient = (EditText) findViewById(R.id.text_recipient_field);
        etMessage = (EditText) findViewById(R.id.text_message_field);
        btnCall = (Button) findViewById(R.id.button_call);
        btnSend = (Button) findViewById(R.id.button_send);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:099-232-131-7"));

                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, R.string.permission_error_message, Toast.LENGTH_LONG).show();
                    return;
                }

                startActivity(callIntent);
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mRecipient, mSubject, mMessage;
                mRecipient = etRecipient.getText().toString();
                mSubject = etSubject.getText().toString();
                mMessage = etMessage.getText().toString();
                /*Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_EMAIL, mRecipient);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, mSubject);
                sendIntent.putExtra(Intent.EXTRA_TEXT, mMessage);
                startActivity(Intent.createChooser(sendIntent, "Send Email"));*/
                /**Intent intent = new Intent(Intent.ACTION_SENDTO);
                 intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                 intent.putExtra(Intent.EXTRA_EMAIL, addresses);
                 intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                 if (intent.resolveActivity(getPackageManager()) != null) {
                 startActivity(intent);
                 }*/
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {mRecipient});
                intent.putExtra(Intent.EXTRA_SUBJECT, mSubject);
                intent.putExtra(Intent.EXTRA_TEXT, mMessage);
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });
    }
}
