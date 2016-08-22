package cyla.juan.icab.uitls;

import android.support.v4.app.ActivityCompat;

import java.lang.ref.WeakReference;

import cyla.juan.icab.ui.WelcomePage;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.PermissionUtils;

/**
 * Created by chen on 16-8-22.
 */
public final class AppPermisionCheckUitl {
    private static final int REQUEST_SHOWMULTI = 0;
    private static final int REQUEST_SHOWCAMERA = 1;
    private static final int REQUEST_SHOWCONTACTS = 2;
    private static final int REQUEST_SHOWINTERNT = 3;

    private static final String[] PERMISSION_SHOWMULTI = new String[] {
            "android.permission.CAMERA",
            "android.permission.READ_CONTACTS",
            "android.permission.WRITE_CONTACTS",
            "android.permission.INTERNET"
    };

    private static final String[] PERMISSION_SHOWCAMERA = new String[] {
            "android.permission.CAMERA"
    };

    private static final String[] PERMISSION_SHOWCONTACTS = new String[] {
            "android.permission.READ_CONTACTS","android.permission.WRITE_CONTACTS"
    };

    private static final String[] PERMISSION_SHOWINTERNET = new String[] {
            "android.permission.INTERNET"
    };

    private AppPermisionCheckUitl() {
    }

    public static void showCameraWithCheck(WelcomePage target) {
        if (PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOWCAMERA)) {
            //有照相机权限
            target.showCamera();
        } else {
            //拒绝后再次显示打开相机
            if (PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_SHOWCAMERA)) {
                target.showRationaleForCamera(new ShowCameraPermissionRequest(target));
            } else {
                //请求打开相应权限消息
                ActivityCompat.requestPermissions(target, PERMISSION_SHOWCAMERA, REQUEST_SHOWCAMERA);
            }
        }
    }

    static void showContactsWithCheck(WelcomePage target) {
        if (PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOWCONTACTS)) {
            target.showContacts();
        } else {
            if (PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_SHOWCONTACTS)) {
                target.showRationaleForContact(new ShowContactsPermissionRequest(target));
            } else {
                ActivityCompat.requestPermissions(target, PERMISSION_SHOWCONTACTS, REQUEST_SHOWCONTACTS);
            }
        }
    }

    public static void onRequestPermissionsResult(WelcomePage target, int requestCode, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_SHOWCAMERA:
                //app编译版本小于23则不需要检测，android6.0+默认兼容 且app没有添加该权限，则默认该权限被关闭，无法打开
                if (PermissionUtils.getTargetSdkVersion(target) < 23 && !PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOWCAMERA)) {
                    target.onCameraDenied();
                    return;
                }
                //检测如果有相应权限，则打开
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.showCamera();
                } else {
                    if (!PermissionUtils.shouldShowRequestPermissionRationale(target, PERMISSION_SHOWCAMERA)) {
                        //没有相应权限，且下次不再弹出提示
                        target.onCameraNeverAskAgain();
                    } else {
                        //没有相应权限，下次会有访问提示
                        target.onCameraDenied();
                    }
                }
                break;
            case REQUEST_SHOWCONTACTS:
                if (PermissionUtils.getTargetSdkVersion(target) < 23 && !PermissionUtils.hasSelfPermissions(target, PERMISSION_SHOWCONTACTS)) {
                    return;
                }
                if (PermissionUtils.verifyPermissions(grantResults)) {
                    target.showContacts();
                }
                break;
            default:
                break;
        }
    }

    private static final class ShowCameraPermissionRequest implements PermissionRequest {
        private final WeakReference<WelcomePage> weakTarget;

        private ShowCameraPermissionRequest(WelcomePage target) {
            this.weakTarget = new WeakReference<>(target);
        }

        @Override
        public void proceed() {
            WelcomePage target = weakTarget.get();
            if (target == null) return;
            ActivityCompat.requestPermissions(target, PERMISSION_SHOWCAMERA, REQUEST_SHOWCAMERA);
        }

        @Override
        public void cancel() {
            WelcomePage target = weakTarget.get();
            if (target == null) return;
            target.onCameraDenied();
        }
    }

    private static final class ShowContactsPermissionRequest implements PermissionRequest {
        private final WeakReference<WelcomePage> weakTarget;

        private ShowContactsPermissionRequest(WelcomePage target) {
            this.weakTarget = new WeakReference<>(target);
        }

        @Override
        public void proceed() {
            WelcomePage target = weakTarget.get();
            if (target == null) return;
            ActivityCompat.requestPermissions(target, PERMISSION_SHOWCONTACTS, REQUEST_SHOWCONTACTS);
        }

        @Override
        public void cancel() {
        }
    }
}
