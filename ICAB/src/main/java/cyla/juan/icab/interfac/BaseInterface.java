package cyla.juan.icab.interfac;

import android.os.Bundle;

/**
 * Created by chen on 16-4-21.
 */public class  BaseInterface {

    public interface mBaseInterface {
        public void Create(Bundle savedInstanceState);

        public void Start();

        public void Resume();

        public void Pause();

        public void Stop();

        public void Destroy();
    }

    public mBaseInterface mInterface;

    public void setBaseInterfaceListen (mBaseInterface mInterface) {
        this.mInterface = mInterface;
    }

}
