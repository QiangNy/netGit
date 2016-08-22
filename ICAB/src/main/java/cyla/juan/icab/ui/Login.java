package cyla.juan.icab.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.List;

import cyla.juan.icab.R;
import cyla.juan.icab.presenter.GirlPresenter;
import cyla.juan.icab.view.IGirlView;

/**
 * Created by chen on 16-4-21.
 */
public class Login extends MVPBaseActivity<IGirlView,GirlPresenter> implements IGirlView{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initviews(savedInstanceState);

        mPresenter.fetch();
    }


    @Override
    protected GirlPresenter createPresenter() {
        return new GirlPresenter();
    }

    private void initviews(Bundle savedInstanceState) {

    }


    @Override
    public void showToast(List<String> girls) {
        Toast.makeText(this,girls.toString(),Toast.LENGTH_LONG).show();
    }


}
