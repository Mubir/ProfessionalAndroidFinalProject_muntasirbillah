package edu.galileo.android.myphotogallery.login;

/**
 * Created by mubir on 8/19/17.
 */
public interface LoginRepository {
    void signUp(String email, String password);
    void signIn(String email, String password);
    void checkSession();
}
