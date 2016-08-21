package cyla.juan.icab.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import cyla.juan.icab.R;

/**
 * Created by chen on 16-4-21.
 */
public class Login extends BaseActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initviews(savedInstanceState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initviews(Bundle savedInstanceState) {

    }
}
