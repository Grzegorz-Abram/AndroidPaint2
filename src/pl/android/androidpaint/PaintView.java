
package pl.android.androidpaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PaintView extends View {

    private Paint paint;
    private Path path;
    private boolean drawing = false;

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setFocusable(true);

        paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(2);
        paint.setStyle(Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            if (drawing) {
                canvas.drawPath(path, paint);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path = new Path();

                path.moveTo(event.getX(), event.getY());
                path.lineTo(event.getX() + 1, event.getY() + 1);

                break;
            case MotionEvent.ACTION_MOVE:
                for (int j = 0; j < event.getHistorySize(); j++) {
                    path.lineTo(event.getHistoricalX(j), event.getHistoricalY(j));
                }
                path.lineTo(event.getX(), event.getY());

                drawing = true;

                break;
            case MotionEvent.ACTION_UP:
                drawing = true;

                break;
        }

        invalidate();

        return true;
    }
}
