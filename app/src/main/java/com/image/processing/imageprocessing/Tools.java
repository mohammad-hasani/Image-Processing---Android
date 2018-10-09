package com.image.processing.imageprocessing;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by root on 19/04/2018.
 */

public class Tools {
    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static Bitmap convertMatToBitmap(Context context, Uri targetUri, Mat kernel) {
        Mat mat = Highgui.imread(Tools.getRealPathFromUri(context, targetUri), 0);
        Mat out = new Mat(mat.rows(), mat.cols(), mat.type());
        Bitmap bmp;
        Mat out2 = new Mat(mat.rows(), mat.cols(), mat.type());
        Imgproc.filter2D(mat, out, -1, kernel);
        bmp = Bitmap.createBitmap(out.cols(), out.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(out, bmp);
        return bmp;
    }

    public static Field[] listRaw() {
        Field[] fields = R.raw.class.getFields();
        return fields;
    }

    public static Mat convertDrwableToMat(Context context, int pic) {
        Mat img = null;
        Mat gryimg = new Mat();
        try {
            img = Utils.loadResource(context, pic, Highgui.CV_LOAD_IMAGE_COLOR);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Imgproc.cvtColor(img, gryimg, Imgproc.COLOR_RGB2BGRA);
        return gryimg;
    }

}
