package cyla.juan.icab.model;

import java.util.List;

/**
 * Created by chen on 16-8-22.
 */
public interface IGirlModel {

    /**
     * 加载数据
     * */
    void loadGirl(GirlOnLoadListenr listenr);

    /**
     * 监听加载数据完成
     * */
    interface GirlOnLoadListenr {
        void onComplet(List<String> girls);
    }


}
