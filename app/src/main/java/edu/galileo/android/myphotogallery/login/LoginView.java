package edu.galileo.android.myphotogallery.login;

/**
 * Created by mubir on 8/19/17.
 */
public interface LoginView {
    void showProgress();
    void hideProgress();
    void enabledInputs();
    void disableInputs();

    void setCurrentUser(String email);
    void loginError(String error);
    void navigateToMainScreen();

    void newUserSuccess();
    void newUserError(String error);
}
