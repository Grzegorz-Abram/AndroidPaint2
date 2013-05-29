
package pl.android.androidpaint;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.Menu;
import android.view.MenuItem;

public class PaintActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_paint);
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
                about();
                break;
            case R.id.menu_exit:
                exit();
                break;
        }

        return true;
    }

    private void about() {
        showMessage(getResources().getText(R.string.about_message));
    }

    private void exit() {
        finish();
        System.exit(0);
    }

    private void showMessage(CharSequence message) {
        String versionName = "";

        try {
            PackageInfo pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionName = pinfo.versionName;
        } catch (NameNotFoundException e) {
        }

        AlertDialog ad = new AlertDialog.Builder(this).create();
        ad.setCancelable(false);
        ad.setMessage(message);
        ad.setIcon(R.drawable.ic_launcher);
        ad.setTitle(getResources().getText(R.string.app_name) + " " + versionName);
        ad.setButton(getResources().getText(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ad.show();
    }
}
