package com.lianyu.android.photogallery;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

public class ThumbnailDownloader<Token> extends HandlerThread {

	private static final String TAG = "ThumbnailDownloader";
	private static final int MESSAGE_DOWNLOAD = 0;

	Handler mHandler;
	Map<Token, String> requestMap = Collections
			.synchronizedMap(new HashMap<Token, String>());
	Handler mResponseHandler;
	Listener<Token> mListener;

	public interface Listener<Token> {
		void onThumbnailDownloaded(Token token, Bitmap thumbnail);
	}

	public void setlistener(Listener<Token> listener) {
		mListener = listener;
	}

	public ThumbnailDownloader(Handler responseHandler) {
		super(TAG);
		// TODO Auto-generated constructor stub
		mResponseHandler = responseHandler;
	}
	
	public void clearQueue() {
		mHandler.removeMessages(MESSAGE_DOWNLOAD);
		requestMap.clear();
	}

	@Override
	protected void onLooperPrepared() {
		// TODO Auto-generated method stub
		mHandler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == MESSAGE_DOWNLOAD) {
					@SuppressWarnings("unchecked")
					Token token = (Token) msg.obj;
					Log.i(TAG,
							"Got a request for url: " + requestMap.get(token));
					handleRequest(token);
				}
			}

		};
	}

	private void handleRequest(final Token token) {
		// TODO Auto-generated method stub
		try {
			final String url = requestMap.get(token);
			if (url == null) {
				return;
			}

			byte[] bitmapBytes = new FlickrFetchr().getUrlBytes(url);
			final Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0,
					bitmapBytes.length);
			Log.i(TAG, "Bitmap created");

			mResponseHandler.post(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if (requestMap.get(token) != url)
						return;
					requestMap.remove(token);
					mListener.onThumbnailDownloaded(token, bitmap);
				}
			});
		} catch (IOException ioe) {
			Log.e(TAG, "Error downloading image", ioe);
		}
	}

	public void queueThumbnail(Token token, String url) {
		requestMap.put(token, url);

		mHandler.obtainMessage(MESSAGE_DOWNLOAD, token).sendToTarget();
	}

}
