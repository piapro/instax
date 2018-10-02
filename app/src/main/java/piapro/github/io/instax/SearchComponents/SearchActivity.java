package piapro.github.io.instax.SearchComponents;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import piapro.github.io.instax.R;
import piapro.github.io.instax.utilities.BottomNavigationViewHelper;

public class SearchActivity extends AppCompatActivity{
    private static final String TAG = "SearchActivity";
    private static final int ACTIVITY = 1;
    @Override
    //Alt+Insert open Generate Method
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Log.d(TAG, "onCreate: Started");
        bottomNavigationViewSetup();
    }

    /**
     * Setup Bottom Navigation View
     */
    private void bottomNavigationViewSetup(){
        Log.d(TAG, "bottomNavigationViewSetup: Bottom Navigation View Setting up");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNaviBar);
        BottomNavigationViewHelper.bottomNavigationViewSetup(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(SearchActivity.this, bottomNavigationViewEx);//Parse the context
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY);
        menuItem.setChecked(true);
    }
}


