package cyla.juan.icab.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by chen on 16-8-21.
 */
public abstract class BasePresenter<T> {

    /**
     * 当内存不足可释放内存
     * */
    protected WeakReference<T> mViewRef;

    /**
     * bind prenter with view
     * */
    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    /**
     * 解除绑定
     * */
    public void detachView() {
        if (mViewRef != null) {
           mViewRef.clear();
            mViewRef = null;
        }
    }

    protected T getView() {
        return mViewRef.get();
    }
}



