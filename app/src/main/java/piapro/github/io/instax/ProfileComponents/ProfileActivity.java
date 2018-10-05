package piapro.github.io.instax.ProfileComponents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import piapro.github.io.instax.R;
import piapro.github.io.instax.utilities.BottomNavigationViewHelper;

import static piapro.github.io.instax.utilities.BottomNavigationViewHelper.bottomNavigationViewSetup;

public class ProfileActivity extends AppCompatActivity{
    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY = 4;
    private Context pContext = ProfileActivity.this;

    private ProgressBar pProgressBar;

    @Override
    //Alt+Insert open Generate Method
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_setting);
        //pProgressBar = (ProgressBar) findViewById(R.id.profile_progressbar);
        //pProgressBar.setVisibility(View.GONE);
        Log.d(TAG, "onCreate: Started");
        bottomNavigationViewSetup();
        setupProfileTopBar();
    }

    private void bottomNavigationViewSetup() {
        Log.d(TAG, "bottomNavigationViewSetup: Bottom Navigation View Setting up");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNaviBar);
        BottomNavigationViewHelper.bottomNavigationViewSetup(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(pContext, bottomNavigationViewEx);//Parse the context
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY);
        menuItem.setChecked(true);
    }


    private void setupProfileTopBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);

        ImageView profile_menu = (ImageView) findViewById(R.id.profile_menu);
        profile_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: navigating to account settings");
                Intent intent = new Intent(pContext, AccountActivity.class);
                startActivity(intent);
            }
        });
    }
}


