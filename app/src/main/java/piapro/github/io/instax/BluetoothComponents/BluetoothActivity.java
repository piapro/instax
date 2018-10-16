package piapro.github.io.instax.BluetoothComponents;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

import piapro.github.io.instax.utilities.BottomNavigationViewHelper;
import piapro.github.io.instax.utilities.LoadUniversalImage;
import piapro.github.io.instax.R;
import piapro.github.io.instax.utilities.TabsPagerAdapter;
import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

public class BluetoothActivity extends AppCompatActivity {
    private static final String TAG = "BTActivity";
    private static final int ACTIVITY = 5;

    Button btn_Send,btn_Listen,btn_ListDevice,btn_Upload ;
    ListView listDevices;
    TextView status_view;
    BluetoothAdapter bluetoothAdapter;
    BluetoothDevice[] btArray;
    ImageView imageView;

    BluetoothCommunication btCommunication;

    static final int STATE_LISTENING = 0;
    static final int STATE_CONNECTING = 1;
    static final int STATE_CONNECTED = 2;
    static final int STATE_CONNECTION_FAILED = 3;
    static final int STATE_MESSAGE_RECEIVED = 4;

    int REQUEST_ENABLE_BLUETOOTH = 1;

    private static final String APP_NAME = "instax";
    private static final UUID DEVICE_UUID = UUID.fromString("10991D89-03EA-4838-99F8-0B34A6A9B3C8");
    private static final int PERMISSION_REQUEST = 0;
    private static final int RESULT_LOAD_IMAGE = 1;

    private Context hContext = piapro.github.io.instax.BluetoothComponents.BluetoothActivity.this;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        Log.d(TAG, "onCreate: Starting...");

        initImageLoader();
        bottomNavigationViewSetup();
        viewPagerSetup();

    }

    private void initImageLoader(){
        LoadUniversalImage loadUniversalImage = new LoadUniversalImage(hContext);
        ImageLoader.getInstance().init(loadUniversalImage.getConfig());
    }

    /**
     * Setup Top Toolbar Tabs
     */
    private void viewPagerSetup(){
        TabsPagerAdapter pagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = (ViewPager) findViewById(R.id.middleContentViewer);//Refer to layout_middle_ContentViewer.xml
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.topTabs);
        tabLayout.setupWithViewPager(viewPager);
        //Set icon for tabs
        //tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
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
        BottomNavigationViewHelper.enableNavigation(piapro.github.io.instax.BluetoothComponents.BluetoothActivity.this, bottomNavigationViewEx);//Parse the context
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY);
        menuItem.setChecked(true);
    }


}