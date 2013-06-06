
package pl.android.androidpaint;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import pl.android.androidpaint.ui.Message;
import pl.android.androidpaint.util.ApplicationInfo;

public class PaintActivity extends Activity implements OnSeekBarChangeListener {

    public static final int PICK_DRAWING_REQUEST = 1;

    private PaintView paintView;
    private LinearLayout layoutParameters;
    private LinearLayout layoutFigure;
    private LinearLayout layoutSize;
    private LinearLayout layoutColor;
    private EditText size;
    private SeekBar seekBarSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_paint);

        paintView = (PaintView) findViewById(R.id.PaintView);

        layoutParameters = (LinearLayout) findViewById(R.id.layout_parameters);
        layoutParameters.setVisibility(View.GONE);

        layoutFigure = (LinearLayout) findViewById(R.id.layout_figure);
        layoutFigure.setVisibility(View.GONE);

        layoutSize = (LinearLayout) findViewById(R.id.layout_size);
        layoutSize.setVisibility(View.GONE);

        layoutColor = (LinearLayout) findViewById(R.id.layout_color);
        layoutColor.setVisibility(View.GONE);

        seekBarSize = (SeekBar) findViewById(R.id.seekBar_size);
        seekBarSize.setOnSeekBarChangeListener(this);

        size = (EditText) findViewById(R.id.size);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_about:
                CharSequence message = getResources().getText(R.string.about_message);
                CharSequence title = ApplicationInfo.getName(this) + " " + ApplicationInfo.getVersion(this);
                Drawable icon = getResources().getDrawable(R.drawable.ic_launcher);

                Message.showMessageOK(this, message, title, icon);

                break;
            case R.id.menu_exit:
                finish();
                System.exit(0);

                break;
        }

        return true;
    }

    public void menuDrawing(View view) {
        showParameters(true, true, true);
    }

    public void menuEraser(View view) {
        showParameters(false, true, false);
    }

    public void menuFill(View view) {
        showParameters(false, false, true);
    }

    public void menuOpenSave(View view) {

    }

    public void menuUndo(View view) {
        paintView.undo();
    }

    public void menuRedo(View view) {
        paintView.redo();
    }

    public void menuClear(View view) {
        paintView.clear();
    }

    private void showParameters(boolean figure, boolean size, boolean color) {
        if (layoutParameters.getVisibility() != View.VISIBLE) {
            layoutParameters.setVisibility(View.VISIBLE);
        } else {
            layoutParameters.setVisibility(View.GONE);
        }

        if (figure) {
            layoutFigure.setVisibility(View.VISIBLE);
        } else {
            layoutFigure.setVisibility(View.GONE);
        }

        if (size) {
            layoutSize.setVisibility(View.VISIBLE);
        } else {
            layoutSize.setVisibility(View.GONE);
        }

        if (color) {
            layoutColor.setVisibility(View.VISIBLE);
        } else {
            layoutColor.setVisibility(View.GONE);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        size.setText(String.valueOf(progress));
        paintView.setSize(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
