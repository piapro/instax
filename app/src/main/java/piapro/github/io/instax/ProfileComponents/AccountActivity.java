package piapro.github.io.instax.ProfileComponents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import piapro.github.io.instax.HomeComponents.HomeActivity;
import piapro.github.io.instax.R;
import piapro.github.io.instax.utilities.BottomNavigationViewHelper;
import piapro.github.io.instax.utilities.MethodFirebase;
import piapro.github.io.instax.utilities.SettingPageAdapter;

public class AccountActivity extends AppCompatActivity{

    private static final String TAG = "AccountActivity";
    private static final int ACTIVITY = 4;

    private Context aContext;
    private SettingPageAdapter pagerAdapter;
    private ViewPager aViewPager;
    private RelativeLayout aRelativeLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);
        aContext = AccountActivity.this;
        Log.d(TAG, "onCreate: started");
        aViewPager = (ViewPager) findViewById(R.id.container_viewpager);
        aRelativeLayout = (RelativeLayout) findViewById(R.id.rlayout1);

        setupSettingList();
        bottomNavigationViewSetup();
        setupFragment();
        getComeinIntent();

        //arrowback to 'profileactivity'
        ImageView arrowback = (ImageView) findViewById(R.id.back_button);
        arrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: back to 'profile activity'");
                finish();
            }
        });


    }
    
    private void setupSettingList() {
        Log.d(TAG, "setupList: initialise setting list");
        ListView listView = (ListView) findViewById(R.id.context_accountsetting);

        ArrayList<String> settings = new ArrayList<>();
        settings.add(getString(R.string.edit_profile));
        settings.add(getString(R.string.sign_out));

        ArrayAdapter aadapter = new ArrayAdapter(aContext, android.R.layout.simple_list_item_1, settings);
        listView.setAdapter(aadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Log.d(TAG, "onItemClick: navigate to fragment: "+ pos);
                setViewPager(pos);
            }
        });
    }


    private void setupFragment(){
        pagerAdapter = new SettingPageAdapter(getSupportFragmentManager());
        //fragement 0
        pagerAdapter.addFrag(new EditProfileFragment(), getString(R.string.edit_profile));
        //fragment 1
        pagerAdapter.addFrag(new SignOutFragment(), getString(R.string.sign_out));
    }

    private void setViewPager(int FNumber){
        aRelativeLayout.setVisibility(View.GONE);
        Log.d(TAG, "setViewPager: navigate to fragment :" + FNumber);
        aViewPager.setAdapter(pagerAdapter);
        aViewPager.setCurrentItem(FNumber);
    }

    /**
     * Setup Bottom Navigation View and prompt TAG message
     */
    private void bottomNavigationViewSetup(){
        Log.d(TAG, "bottomNavigationViewSetup: Bottom Navigation View Setting up");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNaviBar);
        BottomNavigationViewHelper.bottomNavigationViewSetup(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(aContext, bottomNavigationViewEx);//Parse the context
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY);
        menuItem.setChecked(true);
    }

    private void getComeinIntent(){

        Intent intent = getIntent();

        if(intent.hasExtra(getString(R.string.activity_call))){
            Log.d(TAG, "getIncomingIntent: receive coming in intent from " + getString(R.string.profile_activity));
            setViewPager(pagerAdapter.getFNumber(getString(R.string.edit_profile)));
        }
    }


}


