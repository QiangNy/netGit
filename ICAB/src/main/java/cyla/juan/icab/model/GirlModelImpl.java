package cyla.juan.icab.model;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 16-8-22.
 */
public class GirlModelImpl implements IGirlModel {

    List<String> mList;
    private MyHandler mHandler;
    private GirlOnLoadListenr mGirlOnLoadListenr;
    public GirlModelImpl() {
        this.mList = new ArrayList<String>();
    }


    @Override
    public void loadGirl(GirlOnLoadListenr listenr) {
        Log.i("通知thread_id:", ""+Thread.currentThread().getId());
        this.mGirlOnLoadListenr = listenr;

        // 创建一个包含Looper的线程，这里如果没有HandlerThread的调用，会直接将后边的MyThread放到UI线程队列
        HandlerThread myHandlerThread = new HandlerThread("QiangNy");
        // 启动新线程
        myHandlerThread.start();
        // 将handler绑定到新线程
        mHandler = new MyHandler(myHandlerThread.getLooper());
        // 在新线程中执行任务
        mHandler.post(new MyThread());

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

/*        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);

        }*/
    }

    private class MyThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mList.add("hi");
            mList.add("Baby");

            if (mGirlOnLoadListenr != null)
                mGirlOnLoadListenr.onComplet(mList);
            Log.i("通知thread_id:", ""+Thread.currentThread().getId());

          /*  Message message = Message.obtain(mHandler);
            message.sendToTarget();*/
        }

    }
}
