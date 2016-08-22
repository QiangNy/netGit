package cyla.juan.icab.model;

/**
 * Created by chen on 16-8-22.
 */
public interface WelcomeModel {
    void checkpers(WelOnCheckListener listener);

    interface WelOnCheckListener {
        void completCheck(boolean check);
    }
}
