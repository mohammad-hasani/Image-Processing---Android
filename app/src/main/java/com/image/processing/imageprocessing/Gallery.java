package com.image.processing.imageprocessing;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import java.io.FileNotFoundException;

/**
 * Created by root on 19/04/2018.
 */

public class Gallery extends Activity {

    private ImageView imageView;
    private Mat mat;
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    mat = new Mat();
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

    public void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_13, this, mLoaderCallback);
        } else {
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);

        imageView = (ImageView) findViewById(R.id.image_view);

        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/jpg | image/jpeg | image/png");
        startActivityForResult(intent, 0);

    }

    private void showAlert(final Uri targetUri, final String postfix) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setCancelable(true);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alert, null);
        builder1.setView(dialogView);
        builder1.setPositiveButton(
                "Assign",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Mat kernel = new Mat(3, 3, CvType.CV_32F);
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                int x = Integer.valueOf(((EditText) dialogView.findViewById(R.id.txt00 + i + j)).getText().toString());
                                kernel.put(i, j, x);
                            }
                        }
                        convert(targetUri, kernel, postfix);

                    }
                });

        builder1.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void convert(Uri targetUri, Mat kernel, String postfix) {
        if (postfix.equals("mp4")) {

        } else {
            Bitmap bmp = Tools.convertMatToBitmap(this, targetUri, kernel);
            imageView.setImageBitmap(bmp);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 0) {
                Uri targetUri = data.getData();
//                String realPath = Tools.getRealPathFromUri(this, targetUri);
//                String[] split = realPath.split(".");
//                Log.d("AAA", split[0]);
//                String postfix = split[split.length - 1];
                String postfix = "jpg";
                showAlert(targetUri, postfix);
            }
        }
    }
}