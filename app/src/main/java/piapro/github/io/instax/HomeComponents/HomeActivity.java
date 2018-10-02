package piapro.github.io.instax.HomeComponents;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import piapro.github.io.instax.R;
import piapro.github.io.instax.utilities.BottomNavigationViewHelper;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private static final int ACTIVITY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: Starting...");

        bottomNavigationViewSetup();
        viewPagerSetup();

    }
    /**
     * Setup Top Toolbar Tabs
     */
    private void viewPagerSetup(){
        TabsPagerAdapter pagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        //Add fragments to the Tab;
        pagerAdapter.addFragment(new CameraFragment());
        pagerAdapter.addFragment(new HomeFragment());
        pagerAdapter.addFragment(new DirectFragment());


        ViewPager viewPager = (ViewPager) findViewById(R.id.middleContentViewer);//Refer to layout_middle_ContentViewer.xml
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.topTabs);
        tabLayout.setupWithViewPager(viewPager);
        //Set icon for tabs
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        //tabLayout.getTabAt(1).setIcon(R.drawable.ic_instax);
        tabLayout.getTabAt(1).setText("instax");
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_direct);

    }

    /**
     * Setup Bottom Navigation View and prompt TAG message
     */
    private void bottomNavigationViewSetup(){
        Log.d(TAG, "bottomNavigationViewSetup: Bottom Navigation View Setting up");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNaviBar);
        BottomNavigationViewHelper.bottomNavigationViewSetup(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(HomeActivity.this, bottomNavigationViewEx);//Parse the context
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY);
        menuItem.setChecked(true);
    }


}
