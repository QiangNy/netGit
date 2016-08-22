package cyla.juan.icab.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import cyla.juan.icab.R;
import cyla.juan.icab.presenter.WelcomePresenter;
import cyla.juan.icab.uitls.AppPermisionCheckUitl;
import cyla.juan.icab.view.WelcomView;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * app的欢迎界面
 */
@RuntimePermissions
public class WelcomePage extends MVPBaseActivity<WelcomView,WelcomePresenter> implements WelcomView {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show);

        ImageView show = (ImageView) findViewById(R.id.show_iv_welcome_falsh);
        //渐变展示启动屏

        show.setImageResource(R.mipmap.bg_logo);
        final AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(2000);
        show.startAnimation(aa);

        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
            //    mPresenter.checkPermison();
                aa.cancel();
                AppPermisionCheckUitl.showCameraWithCheck(WelcomePage.this);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {

            }
        });

    }

    @Override
    protected WelcomePresenter createPresenter() {
        return new WelcomePresenter();
    }

    @Override
    public void completCheckPermision(boolean Ok) {
        Intent intent = new Intent(WelcomePage.this, Login.class);

        startActivity(intent);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // NOTE: delegate the permission handling to generated method
        AppPermisionCheckUitl.onRequestPermissionsResult(this, requestCode, grantResults);
    }
    @NeedsPermission(Manifest.permission.CAMERA)
    public void showCamera() {
        Toast.makeText(this,"打开照相机",Toast.LENGTH_LONG).show();
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    public void showRationaleForCamera(PermissionRequest request) {
        showRationaleDialog(R.string.permission_camera_rationale, request);
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    public void onCameraDenied() {
        Toast.makeText(this,"拒绝打开相机权限",Toast.LENGTH_LONG).show();
    }
    @NeedsPermission({Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS})
    public void showContacts() {
        Toast.makeText(this,"拒绝打开通讯录权限",Toast.LENGTH_LONG).show();
    }
    @OnShowRationale({Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS})
    public void showRationaleForContact(PermissionRequest request) {
        showRationaleDialog(R.string.permission_contacts_rationale, request);
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    public void onCameraNeverAskAgain() {
        Toast.makeText(this,"拒绝打开相机且不再显示",Toast.LENGTH_LONG).show();
    }

    private void showRationaleDialog(@StringRes int messageResId, final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton("allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("refuse", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }
}
