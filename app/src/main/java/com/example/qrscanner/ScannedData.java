package com.example.qrscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.qrcode.QRCodeReader;

import java.security.PublicKey;

public class ScannedData extends AppCompatActivity {

    TextView scanData;
    Button back, copy;
    public String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanned_data);

        scanData = (TextView) findViewById(R.id.data);
        back = (Button) findViewById(R.id.backBtn);
        copy = (Button) findViewById(R.id.copyBtn);

        //get the data from intent
        Intent intent = getIntent();
        text = intent.getStringExtra("data");
        scanData.setText(text);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Copy text to clipboard
                ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", text);
                clipboard.setPrimaryClip(clip);

                // Show a toast message to inform the user
                Toast.makeText(getApplicationContext(), "Text copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });

    }

}