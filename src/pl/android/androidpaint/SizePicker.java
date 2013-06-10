
package pl.android.androidpaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class SizePicker extends SeekBar {

    public SizePicker(Context context, AttributeSet attrs) {
        super(context, attrs);

        PaintDrawable p = new PaintDrawable();
        p.setShape(new Shape() {

            @Override
            public void draw(Canvas canvas, Paint paint) {
                Path path = new Path();
                path.moveTo(0, getHeight());
                path.lineTo(getWidth(), getHeight());
                path.lineTo(getWidth(), 0);
                canvas.drawPath(path, paint);
            }
        });

        setProgressDrawable(p);
    }

    public int getSize() {
        return getProgress();
    }
}
