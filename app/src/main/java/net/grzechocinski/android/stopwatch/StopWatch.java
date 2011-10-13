package net.grzechocinski.android.stopwatch;

import android.os.Handler;
import android.os.Message;
import java.util.Date;

/**
 * @author mateusz.grzechocinski@gmail.com
 * @since 13/10/2011
 */
public class StopWatch {

	public static final int HEART_BEAT = 0;

	private long startTimestamp;

	private StopWatchListener listener;

	public StopWatch(StopWatchListener listener) {
		this.listener = listener;
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			long nowTimeStamp = new Date().getTime();
			listener.onNewValue(nowTimeStamp - startTimestamp);
			handler.sendEmptyMessageDelayed(HEART_BEAT, 1000);
		}
	};

	public void start() {
		startTimestamp = new Date().getTime();
		handler.sendEmptyMessage(HEART_BEAT);
	}

	public void stop() {
		handler.removeMessages(HEART_BEAT);
	}
}