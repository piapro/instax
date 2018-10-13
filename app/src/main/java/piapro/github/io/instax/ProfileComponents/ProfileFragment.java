package piapro.github.io.instax.ProfileComponents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import de.hdodenhof.circleimageview.CircleImageView;
import piapro.github.io.instax.FirebaseModels.SettingUser;
import piapro.github.io.instax.FirebaseModels.User;
import piapro.github.io.instax.FirebaseModels.UserAccountSettings;
import piapro.github.io.instax.R;
import piapro.github.io.instax.utilities.BottomNavigationViewHelper;
import piapro.github.io.instax.utilities.LoadUniversalImage;
import piapro.github.io.instax.utilities.MethodFirebase;

public class ProfileFragment extends Fragment{

    private static final String TAG = "ProfileFragment";
    private static final int ACTIVITY = 4;

    //firebase
    private FirebaseAuth pAuth;
    private FirebaseAuth.AuthStateListener pAuthListener;
    private FirebaseDatabase pFbDatabase;
    private DatabaseReference pRef;
    private MethodFirebase pMethodFirebase;

    private TextView pPosts, pFollowers, pFollowing, pDisplayName, pUsername, pWebsite, pDescription;
    private ProgressBar pProgressBar;
    private CircleImageView pProfilePhoto;
    private GridView pGridView;
    private Toolbar pToolbar;
    private ImageView pMenuProfile;
    private BottomNavigationViewEx pBottomNavigationView;

    private Context pContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        pDisplayName = (TextView) view.findViewById(R.id.profile_center_name);
        pUsername = (TextView) view.findViewById(R.id.profile_name);
        pWebsite = (TextView) view.findViewById(R.id.profile_center_web);
        pDescription = (TextView) view.findViewById(R.id.profile_center_bio);
        pProfilePhoto = (CircleImageView) view.findViewById(R.id.profile_image);
        pPosts = (TextView) view.findViewById(R.id.no_post);
        pFollowers = (TextView) view.findViewById(R.id.no_followers);
        pFollowing = (TextView) view.findViewById(R.id.no_following);
        pProgressBar = (ProgressBar) view.findViewById(R.id.profileProgressBar);
        pGridView = (GridView) view.findViewById(R.id.profile_grid_photo);
        pToolbar = (Toolbar) view.findViewById(R.id.profile_toolbar);
        pMenuProfile = (ImageView) view.findViewById(R.id.profile_menu);
        pBottomNavigationView = (BottomNavigationViewEx) view.findViewById(R.id.bottomNaviBar);
        pContext = getActivity();
        pMethodFirebase = new MethodFirebase(getActivity());
        Log.d(TAG, "onCreateView: start");

        bottomNavigationViewSetup();
        setupProfileTopBar();

        //setupFirebaseAuth();

        TextView editProfile = (TextView) view.findViewById(R.id.edit_profile);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigate to " + pContext.getString(R.string.edit_profile));

                Intent intent = new Intent(getActivity(), AccountActivity.class);
                intent.putExtra(getString(R.string.activity_call), getString(R.string.profile_activity));
                startActivity(intent);

            }
        });



        return view;
    }

    private void setProfileTool(SettingUser settingUser){
        Log.d(TAG, "setProfileTool: set tools based on data get from firebase: " + settingUser.toString());
        Log.d(TAG, "setProfileTool: set tools based on data get from firebase: " + settingUser.getUserSettings().getUsername());

        //User user = settingUser.getUser();
        UserAccountSettings settings = settingUser.getUserSettings();

        LoadUniversalImage.setImage(null,settings.getProfile_photo(), "",pProfilePhoto);

        pDisplayName.setText(settings.getDisplay_name());
        pUsername.setText(settings.getUsername());
        pWebsite.setText(settings.getWebsite());
        pDescription.setText(settings.getDescription());
        pPosts.setText(String.valueOf(settings.getPosts()));
        pFollowing.setText(String.valueOf(settings.getFollowing()));
        pFollowers.setText(String.valueOf(settings.getFollowers()));
        pProgressBar.setVisibility(View.GONE);
    }

    private void setupProfileTopBar() {

        ((ProfileActivity)getActivity()).setSupportActionBar(pToolbar);

        pMenuProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: navigate to account settings");
                Intent intent = new Intent(pContext, AccountActivity.class);
                startActivity(intent);
            }
        });
    }

    private void bottomNavigationViewSetup() {
        Log.d(TAG, "bottomNavigationViewSetup: Bottom Navigation View Setting up");
        BottomNavigationViewHelper.bottomNavigationViewSetup(pBottomNavigationView);
        BottomNavigationViewHelper.enableNavigation(pContext, pBottomNavigationView);//Parse the context
        Menu menu = pBottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY);
        menuItem.setChecked(true);
    }



    //Firebase


//    private void setupFirebaseAuth(){
//        Log.d(TAG, "setupFirebaseAuth: set up firebase authentication");
//
//        pAuth = FirebaseAuth.getInstance();
//        pFbDatabase = FirebaseDatabase.getInstance();
//        pRef = pFbDatabase.getReference();
//
//        pAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//
//                if (user != null) {
//                    // user sign-in
//                    Log.d(TAG, "onAuthStateChanged:sign_in:" + user.getUid());
//                } else {
//                    // user sign-out
//                    Log.d(TAG, "onAuthStateChanged:sign_out");
//                }
//
//            }
//        };
//
//        pRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                //retrieve user information from the database
//                setProfileTool(pMethodFirebase.getSettingUser(dataSnapshot));
//
//                //retrieve images for the user in question
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        pAuth.addAuthStateListener(pAuthListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (pAuthListener != null) {
//            pAuth.removeAuthStateListener(pAuthListener);
//        }
//    }
}
