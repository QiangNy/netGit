package cyla.juan.icab.presenter;

import java.util.List;

import cyla.juan.icab.model.GirlModelImpl;
import cyla.juan.icab.model.IGirlModel;
import cyla.juan.icab.view.IGirlView;

/**
 * Created by chen on 16-8-22.
 */
public class GirlPresenter extends BasePresenter<IGirlView> {

    private IGirlModel model = new GirlModelImpl();



    public void fetch() {
        model.loadGirl(new IGirlModel.GirlOnLoadListenr() {
            @Override
            public void onComplet(List<String> girls) {
                getView().showToast(girls);
            }
        });
    }
}
