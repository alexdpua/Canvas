package ua.Emchigeshev.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CoolView extends SurfaceView implements SurfaceHolder.Callback,
		Runnable {
	boolean mIsRun;
	Thread mRenderThread;

	public CoolView(Context context) {
		super(context);
		init();
	}

	private void init() {
		getHolder().addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mRenderThread = new Thread(this);
		mRenderThread.start();

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		synchronized (this) {
			mIsRun = false;
		}
		try {
			mRenderThread.join();
		} catch (InterruptedException e) {

		}

	}

	@Override
	public void run() {
		synchronized (this) {
			mIsRun = true;
		}
		long lastFrameTime = System.currentTimeMillis();
		final int LIKE_FPS = 60;
		final int FRAME_DELAY_MILLIS = 1000 / LIKE_FPS;
		while (true) {
			synchronized (this) {
				if (!mIsRun) {
					break;
				}
				Canvas c = null;
				SurfaceHolder holder = getHolder();
				try {
					c = holder.lockCanvas();
					render(c);
				} catch (Exception e) {
				} finally {
					if (c != null) {
						holder.unlockCanvasAndPost(c);
					}
				}
			}
		}
	}
	
	private void render(Canvas c){
		Paint p = new Paint();
		p.setColor(0xff99ffff);
		c.drawLine(0, 0, 200, 200, p);
		
	}

}
