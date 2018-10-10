package piapro.github.io.instax.ProfileComponents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import piapro.github.io.instax.R;
import piapro.github.io.instax.utilities.BottomNavigationViewHelper;
import piapro.github.io.instax.utilities.GridImageAdapter;
import piapro.github.io.instax.utilities.LoadUniversalImage;

public class ProfileActivity extends AppCompatActivity{
    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY = 4;
    private static final int NUM_GRID_COLUMNS = 3;
    private Context pContext = ProfileActivity.this;

    private ImageView profileImage;
    private ProgressBar pProgressBar;

    @Override
    //Alt+Insert open Generate Method
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: Started");

        init_profile();
//        bottomNavigationViewSetup();
//        setupProfileTopBar();
//        setActivityTool();
//        setProfileImage();
//
//        tempGridSetup();
    }

    private void init_profile(){
        Log.d(TAG, "init_profile: inflate " + getString(R.string.profile_fragment));

        ProfileFragment profileFragment = new ProfileFragment();
        FragmentTransaction transaction = ProfileActivity.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, profileFragment);
        transaction.addToBackStack(getString(R.string.profile_fragment));
        transaction.commit();

    }

//    private void bottomNavigationViewSetup() {
//        Log.d(TAG, "bottomNavigationViewSetup: Bottom Navigation View Setting up");
//        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNaviBar);
//        BottomNavigationViewHelper.bottomNavigationViewSetup(bottomNavigationViewEx);
//        BottomNavigationViewHelper.enableNavigation(pContext, bottomNavigationViewEx);//Parse the context
//        Menu menu = bottomNavigationViewEx.getMenu();
//        MenuItem menuItem = menu.getItem(ACTIVITY);
//        menuItem.setChecked(true);
//    }
//
//    private void tempGridSetup(){
//        ArrayList<String> imgURLs = new ArrayList<>();
//        imgURLs.add("https://pbs.twimg.com/profile_images/616076655547682816/6gMRtQyY.jpg");
//        imgURLs.add("https://i.redd.it/9bf67ygj710z.jpg");
//        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");
//        imgURLs.add("http://i.imgur.com/EwZRpvQ.jpg");
//        imgURLs.add("http://i.imgur.com/JTb2pXP.jpg");
//        imgURLs.add("https://i.redd.it/59kjlxxf720z.jpg");
//        imgURLs.add("https://i.redd.it/pwduhknig00z.jpg");
//        imgURLs.add("https://i.redd.it/clusqsm4oxzy.jpg");
//        imgURLs.add("https://i.redd.it/svqvn7xs420z.jpg");
//        imgURLs.add("http://i.imgur.com/j4AfH6P.jpg");
//        imgURLs.add("https://i.redd.it/89cjkojkl10z.jpg");
//        imgURLs.add("https://i.redd.it/aw7pv8jq4zzy.jpg");
//
//        setGridImage(imgURLs);
//    }
//
//    private void setProfileImage(){
//        Log.d(TAG, "setProfileImage: set profile image");
//        String imgURL = "www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge/public/article_images/2016/08/ac-lloyd.jpg?itok=bb72IeLf";
//        LoadUniversalImage.setImage(pProgressBar, imgURL, "https://", profileImage );
//    }
//
//
//    private void setGridImage(ArrayList<String> imageURLs){
//        GridView gridView = (GridView) findViewById(R.id.profile_grid_photo);
//
//        int gridWidth = getResources().getDisplayMetrics().widthPixels;
//        int imageWidth = gridWidth/NUM_GRID_COLUMNS;
//        gridView.setColumnWidth(imageWidth);
//
//        GridImageAdapter adapter = new GridImageAdapter(pContext, R.layout.layout_gridimage, "", imageURLs);
//        gridView.setAdapter(adapter);
//    }
//
//
//    private void setActivityTool(){
//        pProgressBar = (ProgressBar) findViewById(R.id.profileProgressBar);
//        pProgressBar.setVisibility(View.GONE);
//        profileImage = (ImageView) findViewById(R.id.profile_image);
//    }
//
//    private void setupProfileTopBar() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.profile_toolbar);
//        setSupportActionBar(toolbar);
//
//        ImageView profile_menu = (ImageView) findViewById(R.id.profile_menu);
//        profile_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "onClick: navigating to account settings");
//                Intent intent = new Intent(pContext, AccountActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
}


