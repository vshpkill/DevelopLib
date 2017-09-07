package develop.lib.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by KKK on 2017/9/7.
 */

public class ImageLoader {
    private ImageCache imageCache = new MemoryCache();
    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void setImageCache(ImageCache imageCache) {
        this.imageCache = imageCache;
    }

    public void dispalyImage(String url, ImageView imageView) {
        Bitmap bitmap = imageCache.get(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        submitLoadRequest(url, imageView);
    }

    private void submitLoadRequest(final String url, final ImageView imageView) {
        imageView.setTag(url);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if (bitmap == null) {
                    return;
                }
                if (TextUtils.equals(url, (CharSequence) imageView.getTag())) {
                    imageView.setImageBitmap(bitmap);
                }
                imageCache.put(url, bitmap);
            }
        });
    }

    private Bitmap downloadImage(String url) {
        Bitmap bitmap = null;
        try {
            URL urlImage = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlImage.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
