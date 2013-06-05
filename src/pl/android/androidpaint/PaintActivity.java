
package pl.android.androidpaint;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import pl.android.androidpaint.ui.Message;
import pl.android.androidpaint.util.ApplicationInfo;

public class PaintActivity extends Activity {

    private PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_paint);

        paintView = (PaintView) findViewById(R.id.PaintView);
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

    }

    public void menuEraser(View view) {

    }

    public void menuFill(View view) {

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
}
