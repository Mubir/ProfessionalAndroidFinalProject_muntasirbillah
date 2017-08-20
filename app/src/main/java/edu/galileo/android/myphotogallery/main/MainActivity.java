package edu.galileo.android.myphotogallery.main;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.galileo.android.myphotogallery.MyPhotoGalleryApp;
import edu.galileo.android.myphotogallery.R;
import edu.galileo.android.myphotogallery.galleryfragment.GalleryFragment;
import edu.galileo.android.myphotogallery.login.LoginActivity;
import edu.galileo.android.myphotogallery.main.di.MainComponent;
import edu.galileo.android.myphotogallery.mainscreenfragment.MainScreenFragment;
import edu.galileo.android.myphotogallery.mainscreenfragment.entities.MyImage;
import edu.galileo.android.myphotogallery.searchphoto.SearchFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {


    private static final int CAMERA_REQUEST =1 ;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    private View viewNav;
    private TextView emailNav;


    private static Uri mediaUri;
    private ProgressDialog progressDialog;
    private static final int CAMERA_WRITE_PERMISSION = 1;
    private static final int PHOTO_REQUEST = 2;


    public static final int MAIN_SCREEN_FRAGMENT = 1;
    public static final int GALLERY_FRAGMENT = 2;
    public static final String FRAGMENT_MAIN_SCREEN = "main_screen_fragment";
    public static final String FRAGMENT_GALLERY = "gallery_fragment";



    /**** */
    private String photoPath;
    private final static int REQUEST_PICTURE = 0;
    private final static int PERMISSIONS_REQUEST_READ_MEDIA = 10;
    private final static int PERMISSIONS_REQUEST_LOCATION = 11;
    /*********** */

    @Inject
    MainPresenter mainPresenter;
    @Inject
    SharedPreferences sharedPreferences;

    private MyPhotoGalleryApp app;

    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupInjection();
        setupToolbar();
        mainPresenter.onCreate();

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        showFragment(MAIN_SCREEN_FRAGMENT);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    private void setupToolbar() {
        viewNav = navigationView.getHeaderView(0);
        emailNav = (TextView) viewNav.findViewById(R.id.emailNavTextView);
        String email = sharedPreferences.getString(app.getEmailKey(), getString(R.string.app_name));
        emailNav.setText(email);
        toolbar.setTitle(email);
        setSupportActionBar(toolbar);
    }

    private void setupInjection() {
        app = (MyPhotoGalleryApp) getApplication();
        app.getMainComponent(this).inject(this);
    }

    @OnClick(R.id.fab)
    public void handleFab(){
       // takePhoto();
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_READ_MEDIA);
        } else {
            takePhoto();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            showFragment(MAIN_SCREEN_FRAGMENT);

        } else if (id == R.id.nav_camera) {
            takePhoto();

        } else if (id == R.id.nav_gallery) {

            showFragment(GALLERY_FRAGMENT);

        }else if(id == R.id.nav_search){

            showDialogSearch();

        } else if (id == R.id.nav_logout) {
            logout();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showDialogSearch() {
        SearchFragment searchFragment = new SearchFragment();
        searchFragment.show(getSupportFragmentManager(),getString(R.string.search_message_title));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK && requestCode == REQUEST_PICTURE) {
            boolean isCamera = (data == null ||
                    data.getData() == null);

            if (isCamera) {
                addPicToGallery();
            } else {
                photoPath = getRealPathFromURI(data.getData());
            }

            mainPresenter.upPhotos( photoPath);
        }
    }



    /******* ** */

    private void takePhoto() {
        Intent chooserIntent = null;

        List<Intent> intentList = new ArrayList<>();

        Intent pickIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePhotoIntent.putExtra("return-data", true);
        File photoFile = getFile();

        if (photoFile != null) {
            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
            if (takePhotoIntent.resolveActivity(getPackageManager()) != null) {
                intentList = addIntentsToList(intentList, takePhotoIntent);
            }
        }

        if (pickIntent.resolveActivity(getPackageManager()) != null) {
            intentList = addIntentsToList(intentList, pickIntent);
        }

        if (intentList.size() > 0) {
            chooserIntent = Intent.createChooser(intentList.remove(intentList.size() - 1),
                    getString(R.string.main_message_picture_source));
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toArray(new Parcelable[]{}));
        }

        startActivityForResult(chooserIntent, REQUEST_PICTURE);
    }

    private List<Intent> addIntentsToList(List<Intent> list, Intent intent) {
        List<ResolveInfo> resInfo = getPackageManager().queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo : resInfo) {
            String packageName = resolveInfo.activityInfo.packageName;
            Intent targetedIntent = new Intent(intent);
            targetedIntent.setPackage(packageName);
            list.add(targetedIntent);
        }
        return list;
    }


    private String getRealPathFromURI(Uri contentURI) {
        String result = null;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            if (contentURI.toString().contains("mediaKey")) {
                cursor.close();

                try {
                    File file = File.createTempFile("tempImg", ".jpg", getCacheDir());
                    InputStream input = getContentResolver().openInputStream(contentURI);
                    OutputStream output = new FileOutputStream(file);

                    try {
                        byte[] buffer = new byte[4 * 1024];
                        int read;

                        while ((read = input.read(buffer)) != -1) {
                            output.write(buffer, 0, read);
                        }
                        output.flush();
                        result = file.getAbsolutePath();
                    } finally {
                        output.close();
                        input.close();
                    }

                } catch (Exception e) {
                    Log.e(MainActivity.class.getSimpleName(), "Error getting file path", e);
                }
            } else {
                cursor.moveToFirst();
                int dataColumn = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                result = cursor.getString(dataColumn);
                cursor.close();
            }

        }
        return result;
    }

    private void addPicToGallery() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(photoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }


    private File getFile() {
        File photoFile = null;
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            photoFile = File.createTempFile(imageFileName, ".jpg", storageDir);
            photoPath = photoFile.getAbsolutePath();
        } catch (IOException ex) {
            //Snackbar.make(viewPager, R.string.main_error_dispatch_camera, Snackbar.LENGTH_SHORT).show();
        }
        return photoFile;
    }
    /*********** */

    private void showCenterCrop() {
        if (mediaUri != null){
            CropImage.activity(mediaUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setCropShape(CropImageView.CropShape.RECTANGLE)
                    .setBorderLineColor(Color.RED)
                    .setGuidelinesColor(Color.GREEN)
                    .start(this);
        }else{
            Toast.makeText(MainActivity.this, R.string.main_message_uri_empty, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       /* if (grantResults .length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if (requestCode == CAMERA_WRITE_PERMISSION){
                createMediaPhoto();
            }
        }*/

        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_MEDIA:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED))
                    takePhoto();
                break;
          /*  case PERMISSIONS_REQUEST_LOCATION:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED))
                    getLastKnowLocation();
                break;*/
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    private void logout() {
        mainPresenter.logout();
        sharedPreferences.edit().clear();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void showFragment(int fragment) {

        FragmentTransaction fragmentTransaction;
        FragmentManager fragmentManager = getSupportFragmentManager();

        if(fragment == MAIN_SCREEN_FRAGMENT){

            fragmentTransaction = fragmentManager.beginTransaction();
            MainScreenFragment mainScreenFragment = new MainScreenFragment();
            fragmentTransaction.replace(R.id.contentFrameLayout,mainScreenFragment,FRAGMENT_MAIN_SCREEN);
            fragmentTransaction.commit();

        }else if(fragment == GALLERY_FRAGMENT){

            fragmentTransaction = fragmentManager.beginTransaction();
            GalleryFragment galleryFragment = new GalleryFragment();
            fragmentTransaction.replace(R.id.contentFrameLayout,galleryFragment,FRAGMENT_GALLERY);
            fragmentTransaction.commit();

        }
    }




    private void showSnackbar(int res) {
        Snackbar.make(drawer,res,Snackbar.LENGTH_SHORT).show();
    }

    private void showSnackbar(String msg) {
        Snackbar.make(drawer,msg,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.main_message_post_camera));
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void photoError(String error) {
        showSnackbar(error);
    }

    @Override
    public void photoSuccess() {
        showSnackbar(R.string.main_camera_success);
    }

    @Override
    protected void onDestroy() {
        mainPresenter.onDestroy();
        super.onDestroy();
    }

}
