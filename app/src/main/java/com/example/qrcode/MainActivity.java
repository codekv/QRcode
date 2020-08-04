package com.example.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {
    String TAG="GenerateQrCode";
    EditText edttext;
    ImageView qrimg;
    Button start;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    String inputvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qrimg=findViewById(R.id.qrcode);
        edttext=findViewById(R.id.edittext);
        start=findViewById(R.id.createbtn);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputvalue=edttext.getText().toString().trim();
                if (inputvalue.length()>0){
                    WindowManager manager =(WindowManager)getSystemService(WINDOW_SERVICE);
                    Display display =manager.getDefaultDisplay();
                    Point point =new Point();
                    display.getSize(point);
                    int width=point.x;
                    int heigth=point.y;
                    int smallerdimension =width<heigth ? width:heigth;
                    smallerdimension=smallerdimension*3/4;
                    qrgEncoder=new QRGEncoder(inputvalue,null, QRGContents.Type.TEXT,smallerdimension);
                    try {
                        bitmap=qrgEncoder.encodeAsBitmap();
                        qrimg.setImageBitmap(bitmap);
                    }
                    catch (WriterException e){
                        Log.v(TAG,e.toString());
                    }
                }else {
                    edttext.setError("Required");
                }
            }
        });
    }
}