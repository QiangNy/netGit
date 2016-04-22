package cyla.juan.icab.ui;

import android.content.Context;
import android.content.Intent;

import cyla.juan.icab.activity.Login;


/**
 * Created by chen on 16-4-21.
 */
public class UIHelper {


    /**
     * 进入主界面
     *
     * @param context
     */
    public static void goMainActivity(Context context) {
        Intent intent = new Intent(context, Login.class);
        context.startActivity(intent);
    }
}
