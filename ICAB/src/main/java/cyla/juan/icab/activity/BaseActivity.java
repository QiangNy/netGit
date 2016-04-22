package cyla.juan.icab.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cyla.juan.icab.interfac.BaseInterface;

public abstract class BaseActivity  extends AppCompatActivity implements  BaseInterface.mBaseInterface{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Create(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public  void onStart() {
        Start();
        super.onStart();
    }

    @Override
    public void onResume() {
        Resume();
        super.onResume();
    }

    @Override
    public void onPause() {
        Pause();
        super.onPause();
    }

    @Override
    public void onStop() {
        Stop();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Destroy();
        super.onDestroy();
    }

}
