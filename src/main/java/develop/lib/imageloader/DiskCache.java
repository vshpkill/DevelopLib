package develop.lib.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by KKK on 2017/9/7.
 */

public class DiskCache implements ImageCache {
    private static String diskPath = "sdcard/cache";

    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(diskPath + url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(diskPath + url);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
