package com.lianyu.android.criminalintent;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.Display;
import android.widget.ImageView;

public class PictureUtils {

	@SuppressWarnings("deprecation")
	public static BitmapDrawable getScaledDrawable(Activity a, String path) {
		Display display = a.getWindowManager().getDefaultDisplay();
		float desWidth = display.getWidth();
		float desHeight = display.getHeight();

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, options);

		float srcWidth = options.outWidth;
		float srcHeight = options.outHeight;

		int inSampleSize = 1;
		if (srcHeight > desHeight || srcWidth > desWidth) {
			if (srcWidth > srcHeight) {
				inSampleSize = Math.round(srcHeight / desHeight);
			} else {
				inSampleSize = Math.round(srcWidth / desWidth);
			}
		}

		options = new BitmapFactory.Options();
		options.inSampleSize = inSampleSize;

		Bitmap bitmap = BitmapFactory.decodeFile(path, options);
		return new BitmapDrawable(a.getResources(), bitmap);
	}

	public static void cleanImageView(ImageView imageView) {
		if (!(imageView.getDrawable() instanceof BitmapDrawable)) {
			return;
		}

		BitmapDrawable b = (BitmapDrawable) imageView.getDrawable();
		b.getBitmap().recycle();
		imageView.setImageDrawable(null);
	}

}
