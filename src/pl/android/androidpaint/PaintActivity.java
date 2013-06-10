
package pl.android.androidpaint;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import pl.android.androidpaint.ui.Message;
import pl.android.androidpaint.util.ApplicationInfo;

public class PaintActivity extends Activity implements OnSeekBarChangeListener {

    public static final int PICK_DRAWING_REQUEST = 1;

    private PaintView paintView;

    private LinearLayout parametersLayout;
    private LinearLayout figureLayout;
    private LinearLayout sizeLayout;
    private LinearLayout colorLayout;

    private SeekBar sizeSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_paint);

        paintView = (PaintView) findViewById(R.id.PaintView);

        parametersLayout = (LinearLayout) findViewById(R.id.layout_parameters);
        figureLayout = (LinearLayout) findViewById(R.id.layout_figure);
        sizeLayout = (LinearLayout) findViewById(R.id.layout_size);
        colorLayout = (LinearLayout) findViewById(R.id.layout_color);

        sizeSeekBar = (SeekBar) findViewById(R.id.seekBar_size);
        sizeSeekBar.setOnSeekBarChangeListener(this);

        parametersLayout.setVisibility(View.GONE);
        sizeSeekBar.setProgress(10 - 1);
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
                CharSequence title = ApplicationInfo.getName(this) + " "
                        + ApplicationInfo.getVersion(this);
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
        if (parametersLayout.getVisibility() != View.VISIBLE) {
            parametersLayout.setVisibility(View.VISIBLE);
        } else {
            parametersLayout.setVisibility(View.GONE);
        }

        if (figure) {
            figureLayout.setVisibility(View.VISIBLE);
        } else {
            figureLayout.setVisibility(View.GONE);
        }

        if (size) {
            sizeLayout.setVisibility(View.VISIBLE);
        } else {
            sizeLayout.setVisibility(View.GONE);
        }

        if (color) {
            colorLayout.setVisibility(View.VISIBLE);
        } else {
            colorLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getId() == sizeSeekBar.getId()) {
            int size = progress + 1;
            paintView.setSize(size);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
