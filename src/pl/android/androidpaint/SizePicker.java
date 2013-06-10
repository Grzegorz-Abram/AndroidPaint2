
package pl.android.androidpaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SizePicker extends SeekBar implements OnSeekBarChangeListener {

    private static int size = 1;

    public SizePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnSeekBarChangeListener(this);

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

    public static int getSize() {
        return size;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        size = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
