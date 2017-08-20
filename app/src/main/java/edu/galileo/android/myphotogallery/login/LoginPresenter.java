package edu.galileo.android.myphotogallery.login;

/**
 * Created by mubir on 8/19/17.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForSession();
    void validateLogin(String email, String password);
    void handleSignUp(String email,String password);

    void onEventMainThread(LoginEvent event);
}
