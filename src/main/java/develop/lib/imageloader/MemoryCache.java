package develop.lib.imageloader;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by KKK on 2017/9/7.
 */

public class MemoryCache implements ImageCache {
    private LruCache<String, Bitmap> lruCache;

    public MemoryCache() {
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024;
        long lruCacheMemorySize = maxMemory / 4;
        lruCache = new LruCache<>((int) lruCacheMemorySize);
    }

    @Override
    public Bitmap get(String url) {
        return lruCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        lruCache.put(url, bitmap);
    }
}
