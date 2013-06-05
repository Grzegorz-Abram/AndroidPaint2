
package pl.android.androidpaint.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;

import pl.android.androidpaint.R;

public class Message {

    public static void showMessageOK(Context context, CharSequence message, CharSequence title, Drawable icon) {
        AlertDialog ad = new AlertDialog.Builder(context).create();

        ad.setCancelable(false);

        ad.setMessage(message);
        ad.setTitle(title);
        ad.setIcon(icon);

        ad.setButton(context.getResources().getText(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        ad.show();
    }
}
