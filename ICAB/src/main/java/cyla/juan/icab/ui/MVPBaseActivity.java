package cyla.juan.icab.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cyla.juan.icab.presenter.BasePresenter;

public abstract class MVPBaseActivity<V,T extends BasePresenter<V>>  extends AppCompatActivity  {

    protected T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建presenter
        mPresenter = createPresenter();
        //内存泄露，关联view
        mPresenter.attachView((V)this);
    }



    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除关联
        mPresenter.detachView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * 创建 presenter
     *
     * @return
     * */
    protected abstract T createPresenter();
}
