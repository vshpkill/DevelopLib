package develop.lib.imageloader;

import android.graphics.Bitmap;

/**
 * Created by KKK on 2017/9/7.
 */

public interface ImageCache {
    Bitmap get(String url);
    void put(String url,Bitmap bitmap);
}
