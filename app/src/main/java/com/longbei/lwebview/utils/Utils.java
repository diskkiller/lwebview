package com.longbei.lwebview.utils;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.view.MotionEventCompat;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	public static Toast mToast;

	private static final int QR_WIDTH = 300;
	private static final int QR_HEIGHT = 300;
	//要转换的地址或字符串,可以是中文


	// 两次点击按钮之间的点击间隔不能少于1500毫秒
	private static final int MIN_CLICK_DELAY_TIME = 1500;
	private static long lastClickTime;

	public static boolean isFastClick(int time) {
		boolean flag = true;
		long curClickTime = System.currentTimeMillis();
		if ((curClickTime - lastClickTime) >= time) {
			flag = false;
		}
		lastClickTime = curClickTime;
		return flag;
	}


	/**
			* 打开软键盘
	*
			* @param
	* @param mContext
	*/
	public static void openKeybord(Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
				InputMethodManager.HIDE_IMPLICIT_ONLY);
	}

	/**
	 * 关闭软键盘
	 *
	 * @param mEditText
	 * @param mContext
	 */
	public static void closeKeybord(EditText mEditText, Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
	}
	public static void closeKeybord(Context mContext) {
		InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}


	public static void showToast(Context mContext, String msg) {
		if (mToast == null) {
			mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
		}
		mToast.setText(msg);
		mToast.show();
	}
	public static void showToastCenter(Context mContext, String msg) {
		if (mToast == null) {
			mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
		}

		LinearLayout linearLayout = (LinearLayout) mToast.getView();
		TextView messageTextView = (TextView) linearLayout.getChildAt(0);
		messageTextView.setTextSize(25);

		mToast.setGravity(Gravity.CENTER, 0, 0);
		mToast.setText(msg);
		mToast.show();

	}
	public static void showImageToastCenter(Context mContext, String msg, int resId) {
		if (mToast == null) {
			mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
		}
		mToast.setGravity(Gravity.CENTER, 0, 0);
		LinearLayout toastView = (LinearLayout) mToast.getView();
		ImageView imageCodeProject = new ImageView(mContext);
		imageCodeProject.setImageResource(resId);
		toastView.addView(imageCodeProject, 0);
		mToast.setText(msg);
		mToast.show();


	}
	public static void openActivity(Context ctx, Class<?> cls, Bundle bundle) {

		Intent intent = new Intent(ctx, cls);
		if(bundle != null) intent.putExtras(bundle);
		ctx.startActivity(intent);
	}



	/**
	 * 获取版本号
	 * @param context
	 * @return
	 */
	public static String getVersionCode(Context context){

		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}


	/**
	 * 获取圆角bitmap,RCB means Rounded Corner Bitmap
	 * @param bitmap
	 * @param roundPX
	 * @return
	 */
	public static Bitmap getRCB(Bitmap bitmap, float roundPX) {
		if (bitmap == null || roundPX <= 0) {
			return bitmap;
		}
		Bitmap dstbmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(dstbmp);
		Paint paint = new Paint();
		RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
		Path mPath = new Path();
		float[] mCorner = new float[] { roundPX, roundPX, roundPX, roundPX,roundPX, roundPX, roundPX, roundPX};
		mPath.addRoundRect(rectF, mCorner, Path.Direction.CW);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawPath(mPath, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, 0, 0, paint);
		return dstbmp;
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}


	/**
	 * dip 转换成 px
	 * @param dip
	 * @param context
	 * @return
	 */
	public static float dip2Dimension(float dip, Context context) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, displayMetrics);
	}
	/**
	 * @param dip
	 * @param context
	 * @param complexUnit {@link TypedValue#COMPLEX_UNIT_DIP} {@link TypedValue#COMPLEX_UNIT_SP}}
	 * @return
	 */
	public static float toDimension(float dip, Context context, int complexUnit) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return TypedValue.applyDimension(complexUnit, dip, displayMetrics);
	}

	/** 获取状态栏高度
	 * @param v
	 * @return
	 */
	public static int getStatusBarHeight(View v) {
		if (v == null) {
			return 0;
		}
		Rect frame = new Rect();
		v.getWindowVisibleDisplayFrame(frame);
		return frame.top;
	}

	public static String getActionName(MotionEvent event) {
		String action = "unknow";
		switch (MotionEventCompat.getActionMasked(event)) {
		case MotionEvent.ACTION_DOWN:
			action = "ACTION_DOWN";
			break;
		case MotionEvent.ACTION_MOVE:
			action = "ACTION_MOVE";
			break;
		case MotionEvent.ACTION_UP:
			action = "ACTION_UP";
			break;
		case MotionEvent.ACTION_CANCEL:
			action = "ACTION_CANCEL";
			break;
		case MotionEvent.ACTION_OUTSIDE:
			action = "ACTION_SCROLL";
			break;
		default:
			break;
		}
		return action;
	}





	public static String getTableName(String user_id, String other_id) {
		return "U"+user_id+"_U"+other_id;
	}


	public static String getRealFilePath(final Context context, final Uri uri ) {
		if ( null == uri ) return null;
		final String scheme = uri.getScheme();
		String data = null;
		if ( scheme == null )
			data = uri.getPath();
		else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
			data = uri.getPath();
		} else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
			Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore
					.Images.ImageColumns.DATA }, null, null, null );
			if ( null != cursor ) {
				if ( cursor.moveToFirst() ) {
					int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
					if ( index > -1 ) {
						data = cursor.getString( index );
					}
				}
				cursor.close();
			}
		}
		return data;
	}





	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale);
	}

	/**
	 * 获取控件在屏幕中可见的比例
	 */
	public static int getVisiblePercent(View pView) {
		if (pView != null && pView.isShown()) {
			DisplayMetrics displayMetrics = pView.getContext().getResources().getDisplayMetrics();
			int displayWidth = displayMetrics.widthPixels;
			Rect rect = new Rect();
			pView.getGlobalVisibleRect(rect);
			if ((rect.top > 0) && (rect.left < displayWidth)) {
				double areaVisible = rect.width() * rect.height();
				double areaTotal = pView.getWidth() * pView.getHeight();
				return (int) ((areaVisible / areaTotal) * 100);
			} else {
				return -1;
			}
		}
		return -1;
	}

	/**
	 * 是否连接wifi
	 */
	public static boolean isWifiConnected(Context context) {
		if (context.checkCallingOrSelfPermission(Manifest.permission.ACCESS_WIFI_STATE)
				!= PackageManager.PERMISSION_GRANTED) {
			return false;
		}
		ConnectivityManager connectivityManager = (ConnectivityManager)
				context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectivityManager.getActiveNetworkInfo();
		if (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI) {
			return true;
		}
		return false;
	}

	/**
	 * 是否能自动播放
	 */
	public static boolean canAutoPlay(Context context, AutoPlaySetting setting) {
		boolean result = true;
		switch (setting) {
			case AUTO_PLAY_3G_4G_WIFI:
				result = true;
				break;
			case AUTO_PLAY_ONLY_WIFI:
				if (isWifiConnected(context)) {
					result = true;
				} else {
					result = false;
				}
				break;
			case AUTO_PLAY_NEVER:
				result = false;
				break;
		}
		return result;
	}

	public enum AutoPlaySetting {
		// wifi状态下才可以自动播放
		AUTO_PLAY_ONLY_WIFI,
		// 3G、4G、wifi都可以自动播放
		AUTO_PLAY_3G_4G_WIFI,
		// 不允许自动播放
		AUTO_PLAY_NEVER
	}

	//
	public static String getAppVersion(Context context) {
		String version = "1.0.0"; //默认1.0.0版本
		PackageManager manager = context.getPackageManager();
		try {
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			version = info.versionName;
		} catch (Exception e) {
		}

		return version;
	}

	/**
	 * 将数组中的所有素材IE拼接起来，空则拼接“”
	 */
	//    public static String getAdIE(ArrayList<VideoValue> values) {
	//        StringBuilder result = new StringBuilder();
	//        if (values != null && values.size() > 0) {
	//            for (VideoValue value : values) {
	//                result.append(value.adid.equals("") ? "" : value.adid).append(",");
	//            }
	//            return result.substring(0, result.length() - 1);
	//        }
	//        return "";
	//    }
	public static DisplayMetrics getDisplayMetrics(Context context) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		if (windowManager == null) {
			return displayMetrics;
		}
		windowManager.getDefaultDisplay().getMetrics(displayMetrics);
		return displayMetrics;
	}

	public static int getScreenWidth(Context context) {
		DisplayMetrics metrics = getDisplayMetrics(context);
		return metrics.widthPixels;
	}

	public static BitmapDrawable decodeImage(String base64drawable) {
		byte[] rawImageData = Base64.decode(base64drawable, 0);
		return new BitmapDrawable(null, new ByteArrayInputStream(rawImageData));
	}

	public static boolean isPad(Context context) {

		//如果能打电话那可能是平板或手机，再根据配置判断
		if (canTelephone(context)) {
			//能打电话可能是手机也可能是平板
			return (context.getResources().getConfiguration().
					screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
					>= Configuration.SCREENLAYOUT_SIZE_LARGE;
		} else {
			return true; //不能打电话一定是平板
		}
	}

	private static boolean canTelephone(Context context) {
		TelephonyManager telephony = (TelephonyManager)
				context.getSystemService(Context.TELEPHONY_SERVICE);
		return (telephony.getPhoneType() == TelephonyManager.PHONE_TYPE_NONE) ? false : true;
	}

	public static boolean containString(String source, String destation) {

		if (source.equals("") || destation.equals("")) {
			return false;
		}

		if (source.contains(destation)) {
			return true;
		}
		return false;
	}

	public static boolean matchRegular(String str) {
		String regex = "^[\\u4e00-\\u9fa5]*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	}
