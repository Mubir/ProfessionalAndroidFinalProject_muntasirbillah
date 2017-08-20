package edu.galileo.android.myphotogallery.login;

/**
 * Created by mubir on 8/19/17.
 */
public interface LoginInteractor {
    void checkSession();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);
}
