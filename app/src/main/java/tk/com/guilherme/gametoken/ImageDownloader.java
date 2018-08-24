package tk.com.guilherme.gametoken;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
    private Bitmap bitmap;
    private ImageView imageView;


    public ImageDownloader(ImageView imageView) {
        this.imageView = imageView;
    }

    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        try {
            InputStream inputStream = new java.net.URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            bitmap = null;
            Log.e("ImageDownloader", "Exception: " + e.getMessage());
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap finalBitmap) {
        if (finalBitmap != null) {
            imageView.setImageBitmap(finalBitmap);
        }
    }
}
