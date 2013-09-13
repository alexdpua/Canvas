package ua.Emchigeshev.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View implements View.OnTouchListener {
	
	private Paint mPainter = new Paint(Paint.ANTI_ALIAS_FLAG);
	private float x, y;
	private static float SPEED = 4.5f;
	private boolean isLeft, isUp;
	private boolean isRunning = false;
	private float lineX, lineY, lineWidth;
	private Paint mLinePaint = new Paint();
	private float prevX = 0;
	private boolean isAttached = false;
	public int points = 0;
	private Listener mListener;
	private float radius = 15;

	public interface Listener {
		public void onPointsChange(int count);
	}

	public void setListener(Listener l) {
		mListener = l;
	}

	public MyView(Context context, AttributeSet ta) {
		super(context, ta);
		x = 0.0f;
		y = 0.0f;
		isLeft = false;
		isUp = true;
		mPainter.setColor(0xffffffff);
		lineX = 0;
		lineY = 7;
		lineWidth = 100;
		mLinePaint.setStrokeWidth(5);
		mLinePaint.setColor(0xff0055ff);
		setOnTouchListener(this);
	}

	public MyView(Context context, AttributeSet ta, int defStyle) {
		this(context, ta);
	}

	private void update() {
		float w = getMeasuredWidth();
		float h = getMeasuredHeight();
		if (isLeft) {
			x -= SPEED;
			if (x <= radius) {
				x = radius;
				isLeft = false;
			}

		} else {
			x += SPEED;
			if (x >= w - radius) {
				x = w - radius;
				isLeft = true;
			}
		}

		if (isUp) {
			y -= SPEED;
			if (y <= radius) {
				y = radius;
				isUp = false;
			}
		} else {
			y += SPEED;
			if (y >= (h - lineY) - radius) {
				if (x >= lineX && x <= lineX + lineWidth) {
					y = h - radius;
					isUp = true;
					++points;
					mListener.onPointsChange(points);
				} else {
					pause();
					isAttached = true;
					x = lineX + lineWidth / 2;
					y = (h - lineY) - radius;
				}
			}
		}

	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(0xffff9900);
		float lineYPos = getMeasuredHeight() - lineY;
		canvas.drawLine(lineX, lineYPos, lineX + lineWidth, lineYPos,
				mLinePaint);
		canvas.drawCircle(x, y, radius, mPainter);
		if (isRunning) {
			update();
			postInvalidate();
		}
	}

	public void pause() {
		isRunning = false;
	}
	SinglePlayerActivity sp = new SinglePlayerActivity();

	public void resume() {
		isAttached = false;
		isRunning = true;
		points = 0;
		invalidate();
	}

	public boolean isPaused() {
		return !isRunning;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		if (action == MotionEvent.ACTION_MOVE) {
			float x = event.getX();
			float speed = x - prevX;
			if (speed > 3 || speed < -3) {
				float w = getMeasuredWidth();
				lineX += speed; // speed * 0.5f - сила реакции, чувствительность
				if (lineX < 0) {
					lineX = 0;
				} else if (lineX + lineWidth > w) {
					lineX = w - lineWidth;
				}

			}
			prevX = x;
			if (isAttached) {
				this.x = lineX + lineWidth / 2;
			}
			invalidate();
		} else if (action == MotionEvent.ACTION_DOWN) {
			prevX = event.getX();
		}
		return true;
	}

}
