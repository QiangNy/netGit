package cyla.juan.icab.model;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by chen on 16-8-22.
 */
public class WelcomeModelImpl implements WelcomeModel {
    private WelOnCheckListener mWelOnCheckListener;
    private MyHandler mHandler;

    @Override
    public void checkpers(WelOnCheckListener listener) {
        this.mWelOnCheckListener = listener;



        if (mWelOnCheckListener != null)
            mWelOnCheckListener.completCheck(true);


/*        // 创建一个包含Looper的线程，这里如果没有HandlerThread的调用，会直接将后边的MyThread放到UI线程队列
        HandlerThread myHandlerThread = new HandlerThread("QiangNy");
        // 启动新线程
        myHandlerThread.start();
        // 将handler绑定到新线程
        mHandler = new MyHandler(myHandlerThread.getLooper());
        // 在新线程中执行任务
        mHandler.post(new MyThread());*/
    }


    private class MyHandler extends Handler {
        /**
         * 使用默认的构造函数，会将handler绑定当前UI线程的looper。
         * 如果想使用多线程这里是不能使用默认的构造方法的。
         */
        public MyHandler() {
            super();
        }

        public MyHandler(Looper looper) {
            super(looper);
        }


    }

    private class MyThread implements Runnable {
        @Override
        public void run() {


                if (mWelOnCheckListener != null)
                mWelOnCheckListener.completCheck(true);


          /*  Message message = Message.obtain(mHandler);
            message.sendToTarget();*/
        }

    }
}
