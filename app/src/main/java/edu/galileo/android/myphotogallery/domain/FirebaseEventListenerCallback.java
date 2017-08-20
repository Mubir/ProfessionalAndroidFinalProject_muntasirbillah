package edu.galileo.android.myphotogallery.domain;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by mubir on 8/19/17.
 */
public interface FirebaseEventListenerCallback {
    void onChildAdded(DataSnapshot dataSnapshot);
    void onCanceled(DatabaseError databaseError);
}
