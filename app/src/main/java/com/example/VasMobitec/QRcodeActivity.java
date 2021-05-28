package com.example.VasMobitec;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.zxing.WriterException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QRcodeActivity extends AppCompatActivity {

    private ImageView qrCodeIV;
    private EditText dataEdt;
    private Button generateQrBtn;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        qrCodeIV = findViewById(R.id.idIVQrcode);
        dataEdt = findViewById(R.id.idEdt);
        generateQrBtn = findViewById(R.id.idBtnGenerateQR);

        generateQrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(dataEdt.getText().toString())) {

                    // if the edittext inputs are empty then execute
                    // this method showing a toast message.
                    Toast.makeText(QRcodeActivity.this, "Enter some text to generate QR Code", Toast.LENGTH_SHORT).show();
                } else {
                    // below line is for getting
                    // the windowmanager service.
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

                    // initializing a variable for default display.
                    Display display = manager.getDefaultDisplay();

                    // creating a variable for point which
                    // is to be displayed in QR Code.
                    Point point = new Point();
                    display.getSize(point);

                    // getting width and
                    // height of a point
                    int width = point.x;
                    int height = point.y;

                    // generating dimension from width and height.
                    int dimen = width < height ? width : height;
                    dimen = dimen * 3 / 4;

                    // setting this dimensions inside our qr code
                    // encoder to generate our qr code.
                    qrgEncoder = new QRGEncoder(dataEdt.getText().toString(), null, QRGContents.Type.TEXT, dimen);
                    // getting our qrcode in the form of bitmap.
                    try {
                        bitmap = qrgEncoder.encodeAsBitmap();
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                    // the bitmap is set inside our image
                    // view using .setimagebitmap method.
                    qrCodeIV.setImageBitmap(bitmap);
                }
            }
        });

        Glide.with(this)
                .load(qrCodeIV)
                .into(qrCodeIV);
        Button btn_save = findViewById(R.id.save_btn);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileOutputStream fileOutputStream=null;
                File file=getdisc();
                if (!file.exists() && !file.mkdirs())
                {
                    Toast.makeText(getApplicationContext(),"sorry can not make dir",Toast.LENGTH_LONG).show();
                    return;
                }
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyymmsshhmmss");
                String date=simpleDateFormat.format(new Date());
                String name="img"+date+".jpeg";
                String file_name=file.getAbsolutePath()+"/"+name; File new_file=new File(file_name);
                try {
                    fileOutputStream =new FileOutputStream(new_file);
                    Bitmap bitmap=viewToBitmap(qrCodeIV,qrCodeIV.getWidth(),qrCodeIV.getHeight());
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
                    Toast.makeText(getApplicationContext(),"sucses", Toast.LENGTH_LONG).show();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
                catch
                (FileNotFoundException e) {

                } catch (IOException e) {

                } refreshGallary(file);
            } private void refreshGallary(File file)
            { Intent i=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                i.setData(Uri.fromFile(file)); sendBroadcast(i);
            }
            private File getdisc(){
                File file= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                return new File(file,"My Image");
            } });
    }
    private static Bitmap viewToBitmap(View view, int widh, int hight)
    {
        Bitmap bitmap=Bitmap.createBitmap(widh,hight, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap); view.draw(canvas);
        return bitmap;
    }
    }
