package com.bob.android.opencvdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity {

    private Button mBtnChangeColor;
    private ImageView mImgMain;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initOpenCv();
        mBtnChangeColor = (Button)findViewById(R.id.btn_change_color);
        mImgMain = (ImageView)findViewById(R.id.img_main);
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.img);
        mImgMain.setImageBitmap(bitmap);

        mBtnChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.img);
                Mat src = new Mat();
                Mat dst = new Mat();
                Utils.bitmapToMat(bitmap,src);
                Imgproc.cvtColor(src,dst,Imgproc.COLOR_BGRA2GRAY);
                Utils.matToBitmap(dst,bitmap);
                mImgMain.setImageBitmap(bitmap);
                src.release();
                dst.release();
            }
        });
    }

    private void initOpenCv() {
        boolean success = OpenCVLoader.initDebug();
        if(success){
            Log.e("TAG","openCV Library loadeed....");
        }else{
            Log.e("TAG","openCV Library unloadeed....");
        }
    }
}
