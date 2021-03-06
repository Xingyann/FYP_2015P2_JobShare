package com.example.jobshare.menu;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;

public class HomeImageView extends ImageView {

	public interface ResponseObserver {
		public void onError();

		public void onSuccess();
	}

	private ResponseObserver mObserver;

	public void setResponseObserver(ResponseObserver observer) {
		mObserver = observer;
	}

	/**
	 * The URL of the network image to load
	 */
	private String mUrl;

	/**
	 * Resource ID of the image to be used as a placeholder until the network
	 * image is loaded.
	 */
	private int mDefaultImageId;

	/**
	 * Resource ID of the image to be used if the network response fails.
	 */
	private int mErrorImageId;

	/**
	 * Local copy of the ImageLoader.
	 */
	private ImageLoader mImageLoader;

	/**
	 * Current ImageContainer. (either in-flight or finished)
	 */
	private ImageContainer mImageContainer;

	public HomeImageView(Context context) {
		this(context, null);
	}

	public HomeImageView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HomeImageView(Context context, AttributeSet attrs,
						 int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setImageUrl(String url, ImageLoader imageLoader) {
		mUrl = url;
		mImageLoader = imageLoader;
		// The URL has potentially changed. See if we need to load it.
		loadImageIfNecessary(false);
	}

	public void setDefaultImageResId(int defaultImage) {
		mDefaultImageId = defaultImage;
	}

	public void setErrorImageResId(int errorImage) {
		mErrorImageId = errorImage;
	}

	private void loadImageIfNecessary(final boolean isInLayoutPass) {
		final int width = getWidth();
		int height = getHeight();

		boolean isFullyWrapContent = getLayoutParams() != null
				&& getLayoutParams().height == LayoutParams.WRAP_CONTENT
				&& getLayoutParams().width == LayoutParams.WRAP_CONTENT;
		if (width == 0 && height == 0 && !isFullyWrapContent) {
			return;
		}

		if (TextUtils.isEmpty(mUrl)) {
			if (mImageContainer != null) {
				mImageContainer.cancelRequest();
				mImageContainer = null;
			}
			setDefaultImageOrNull();
			return;
		}

		// if there was an old request in this view, check if it needs to be
		// canceled.
		if (mImageContainer != null && mImageContainer.getRequestUrl() != null) {
			if (mImageContainer.getRequestUrl().equals(mUrl)) {
				// if the request is from the same URL, return.
				return;
			} else {
				// if there is a pre-existing request, cancel it if it's
				// fetching a different URL.
				mImageContainer.cancelRequest();
				setDefaultImageOrNull();
			}
		}

		// The pre-existing content of this view didn't match the current URL.
		// Load the new image
		// from the network.
		ImageContainer newContainer = mImageLoader.get(mUrl,
				new ImageListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						if (mErrorImageId != 0) {
							setImageResource(mErrorImageId);
						}

						if (mObserver != null) {
							mObserver.onError();
						}
					}

					@Override
					public void onResponse(final ImageContainer response,
							boolean isImmediate) {
						if (isImmediate && isInLayoutPass) {
							post(new Runnable() {
								@Override
								public void run() {
									onResponse(response, false);
								}
							});
							return;
						}

						int bWidth = 0, bHeight = 0;
						if (response.getBitmap() != null) {

							setImageBitmap(response.getBitmap());
							bWidth = response.getBitmap().getWidth();
							bHeight = response.getBitmap().getHeight();
							adjustImageAspect(bWidth, bHeight);

						} else if (mDefaultImageId != 0) {
							setImageResource(mDefaultImageId);
						}

						if (mObserver != null) {
							mObserver.onSuccess();

						}
					}
				});

		// update the ImageContainer to be the new bitmap container.
		mImageContainer = newContainer;

	}

	private void setDefaultImageOrNull() {
		if (mDefaultImageId != 0) {
			setImageResource(mDefaultImageId);
		} else {
			setImageBitmap(null);
		}
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		loadImageIfNecessary(true);
	}

	@Override
	protected void onDetachedFromWindow() {
		if (mImageContainer != null) {
			// If the view was bound to an image request, cancel it and clear
			// out the image from the view.
			mImageContainer.cancelRequest();
			setImageBitmap(null);
			// also clear out the container so we can reload the image if
			// necessary.
			mImageContainer = null;
		}
		super.onDetachedFromWindow();
	}

	@Override
	protected void drawableStateChanged() {
		super.drawableStateChanged();
		invalidate();
	}

	/*
	 * Adjusting imageview height
	 * */
	private void adjustImageAspect(int bWidth, int bHeight) {
		LayoutParams params = (LayoutParams) getLayoutParams();

		if (bWidth == 0 || bHeight == 0)
			return;

		int swidth = getWidth();
		int new_height = 0;
		new_height = swidth * bHeight / bWidth;
		params.width = swidth;
		params.height = new_height;
		setLayoutParams(params);
	}
}