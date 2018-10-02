package piapro.github.io.instax.ProfileComponents;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import piapro.github.io.instax.R;

public class AccountActivity extends AppCompatActivity{

    private static final String TAG = "AccountActivity";
    private Context aContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);
        aContext = AccountActivity.this;
        Log.d(TAG, "onCreate: started");

        setupSettingList();

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
    }
}


