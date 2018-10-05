package piapro.github.io.instax.FavoriteComponents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import piapro.github.io.instax.R;
import piapro.github.io.instax.utilities.BottomNavigationViewHelper;
import piapro.github.io.instax.HomeComponents.TabsPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.TabLayout;

public class FavoriteActivity extends AppCompatActivity{
    private static final String TAG = "FavoriteActivity";
    private static final int ACTIVITY = 3;
    @Override
    //Alt+Insert open Generate Method
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);
        Log.d(TAG, "onCreate: Started");
        bottomNavigationViewSetup();
        viewPagerSetup();
    }

    /**
     * Setup Bottom Navigation View
     */
    private void bottomNavigationViewSetup(){
        Log.d(TAG, "bottomNavigationViewSetup: Bottom Navigation View Setting up");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNaviBar);
        BottomNavigationViewHelper.bottomNavigationViewSetup(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(FavoriteActivity.this, bottomNavigationViewEx);//Parse the context
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY);
        menuItem.setChecked(true);
    }

    private void viewPagerSetup(){
        TabsPagerAdapter pagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        //Add fragments to the Tab;
        pagerAdapter.addFragment(new FollowedFragment());
        pagerAdapter.addFragment(new YourselfFragment());

        ViewPager viewPager = (ViewPager) findViewById(R.id.middleContentViewer);//Refer to layout_middle_ContentViewer.xml
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.topTabs);
        tabLayout.setupWithViewPager(viewPager);
        //Set icon for tabs
        tabLayout.getTabAt(0).setText("Followed");
        tabLayout.getTabAt(1).setText("You");
    }

}


