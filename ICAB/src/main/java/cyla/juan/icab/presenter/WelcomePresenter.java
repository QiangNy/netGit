package cyla.juan.icab.presenter;

import cyla.juan.icab.model.WelcomeModel;
import cyla.juan.icab.model.WelcomeModelImpl;
import cyla.juan.icab.view.WelcomView;

/**
 * Created by chen on 16-8-22.
 */
public class WelcomePresenter extends BasePresenter<WelcomView> {

    private WelcomeModel model = new WelcomeModelImpl();

    public void checkPermison() {
        model.checkpers(new WelcomeModel.WelOnCheckListener() {
            @Override
            public void completCheck(boolean check) {
                getView().completCheckPermision(check);
            }
        });

    }
}
