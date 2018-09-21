package piapro.github.io.instax.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import piapro.github.io.instax.R;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG,"onCreate: Started");
    }
}
