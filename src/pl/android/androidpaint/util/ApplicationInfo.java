
package pl.android.androidpaint.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import pl.android.androidpaint.R;

public class ApplicationInfo {
    public static String getName(Context context) {
        return context.getResources().getText(R.string.app_name).toString();
    }

    public static String getVersion(Context context) {
        String version = "";

        try {
            PackageInfo pinfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            version = pinfo.versionName;
        } catch (NameNotFoundException e) {
        }

        return version;
    }
}
