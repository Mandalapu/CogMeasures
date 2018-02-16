package edu.usc.projecttalent.cognitive;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import edu.usc.projecttalent.cognitive.numbers.NSInstruction;
import edu.usc.projecttalent.cognitive.reaction.RTInstruction;
import edu.usc.projecttalent.cognitive.reasoning.ARInstruction;
import edu.usc.projecttalent.cognitive.spatial.SVInstruction;
import edu.usc.projecttalent.cognitive.spatial.SVPractice;
import edu.usc.projecttalent.cognitive.thurstone.TMInstruction;
import edu.usc.projecttalent.cognitive.util.Fileutils;
import edu.usc.projecttalent.cognitive.vocab.VSInstruction;

/**
 * Activity that provides general instructions about the Cognitive Measures application.
 *
 * @author Anindya Dutta
 * @version 2.0
 */

public class CMIntro extends BaseActivity {

    /**
     * Provide basic instructions and move to vocabulary section on clicking Next.
     * @param savedInstanceState nothing is sent to the bundle currently.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmintro);
        setNext(getNextClass());

        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };

        int requestCode = 200;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }

    }

    private Class getNextClass() {
        int next = getSharedPreferences("pref", Context.MODE_PRIVATE).getInt("module", 0);
        Log.e("aarushi", next+"");
        switch (next){
            case 0: return VSInstruction.class;
            case 1: return NSInstruction.class;
            case 2: return TMInstruction.class;
            case 3: return ARInstruction.class;
            case 4: return RTInstruction.class;
            default: return SVPractice.class;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 200: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Fileutils.fileWritePermission = true;

                } else {

                    Fileutils.fileWritePermission = false;
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }
}
