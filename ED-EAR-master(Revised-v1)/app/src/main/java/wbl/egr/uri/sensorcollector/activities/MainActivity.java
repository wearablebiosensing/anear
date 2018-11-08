package wbl.egr.uri.sensorcollector.activities;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * MainActivity
 *
 * Gathers information from the application and the android device in order to return
 * the directory in which every anEAR file will be saved.
 *
 * This class in particular creates the root anEAR directory and the patient directory, then returns
 * the path so that the .csv and .wav files can be stored there.
 */
public class MainActivity extends Application {
    public static File getRootFile(Context context) {
        File root;
        root = new File("/storage/sdcard1");
        if (!root.exists() || !root.canWrite()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                root = new File(Environment.getExternalStorageDirectory(), Environment.DIRECTORY_DOCUMENTS);
            } else {
                root = new File(Environment.getExternalStorageDirectory(), "Documents");
            }
        }
        File directory;
        String id = SettingsActivity.getString(context, SettingsActivity.KEY_IDENTIFIER, null);
        if (id == null || id.equals("")) {
            directory = new File(root, ".anear");
        } else {
            directory = new File(root, ".anear/" + id);
        }
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                Log.d("MAIN", "Made parent directories");
            }
        }
        return directory;
    }
}
