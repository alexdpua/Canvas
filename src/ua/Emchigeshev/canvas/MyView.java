package ua.Emchigeshev.canvas;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
	private Paint mPainter = new Paint(Paint.ANTI_ALIAS_FLAG);
	private float x, y;
	private static float SPEED = 6.5f;
	private boolean isLeft, isUp;
	private float textWidth;
	private static final String sText = "Hello There";

	public MyView(Context context) {
		super(context);
		x = 0.0f;
		y = 0.0f;
		isLeft = false;
		isUp = true;
		mPainter.setColor(0xffffffff);
		mPainter.setTextSize(30);
		textWidth = mPainter.measureText(sText);
	}
	public MyView(Context context, AttributeSet ta) {
		this(context);
	}
	public MyView(Context context, AttributeSet ta, int defStyle) {
		this(context, ta);
	}

	private void update() {
		float w = getMeasuredWidth();
		float h = getMeasuredHeight();
		if (isLeft) {
			if (x >= SPEED) {
				x -= SPEED;
			} else {
				isLeft = false;
			}
		} else {
			if (x <= w - SPEED - textWidth) {
				x += SPEED;
			} else {
				isLeft = true;
			}
		}
		
		if (isUp) {
			if (y >= SPEED) {
				y -= SPEED;
			} else {
				isUp = false;
			}
		} else {
			if (y <= h - SPEED - mPainter.getTextSize()) {
				y += SPEED;
			} else {
				isUp = true;
			}
		}
		

	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(0xffff9900);
		canvas.drawText(sText, x, y + 25, mPainter);
		update();
		postInvalidate();
	}

}
