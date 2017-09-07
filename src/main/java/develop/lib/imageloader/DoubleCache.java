package develop.lib.imageloader;

import android.graphics.Bitmap;

/**
 * Created by KKK on 2017/9/7.
 */

public class DoubleCache implements ImageCache {

    private final MemoryCache memoryCache;
    private final DiskCache diskCache;

    public DoubleCache() {
        memoryCache = new MemoryCache();
        diskCache = new DiskCache();
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = memoryCache.get(url);
        if (bitmap == null) {
            bitmap = diskCache.get(url);
        }
        return bitmap;
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        memoryCache.put(url, bitmap);
        diskCache.put(url, bitmap);
    }
}
