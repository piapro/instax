package piapro.github.io.instax.ProfileComponents;

import android.icu.util.UniversalTimeScale;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nostra13.universalimageloader.core.ImageLoader;

import de.hdodenhof.circleimageview.CircleImageView;
import piapro.github.io.instax.FirebaseModels.SettingUser;
import piapro.github.io.instax.FirebaseModels.User;
import piapro.github.io.instax.FirebaseModels.UserAccountSettings;
import piapro.github.io.instax.R;
import piapro.github.io.instax.utilities.LoadUniversalImage;
import piapro.github.io.instax.utilities.MethodFirebase;

public class EditProfileFragment extends Fragment{

    private static final String TAG = "EditProfileFragment";

    //firebase
    private FirebaseAuth eAuth;
    private FirebaseAuth.AuthStateListener eAuthListener;
    private FirebaseDatabase eFbDatabase;
    private DatabaseReference eRef;
    private MethodFirebase eMethodFirebase;
    private String userID;

    //Edit Profile Fragment Tool
    private EditText eDisplayName, eUsername, eWebsite, eDescription, eEmail, ePhoneNumber;
    private CircleImageView eProfileImage;
    private TextView eChangeProfilePhoto;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editprofile, container,false);

        eProfileImage = (CircleImageView) view.findViewById(R.id.photo_profile);
        eDisplayName = (EditText) view.findViewById(R.id.username);
        eUsername = (EditText) view.findViewById(R.id.username);
        eWebsite = (EditText) view.findViewById(R.id.web);
        eDescription = (EditText) view.findViewById(R.id.bio);
        eEmail = (EditText) view.findViewById(R.id.email);
        ePhoneNumber = (EditText) view.findViewById(R.id.phone);
        eChangeProfilePhoto = (TextView) view.findViewById(R.id.change_Photo_profile);
        eMethodFirebase = new MethodFirebase(getActivity());


        //setProfileImage();
        //setupFirebaseAuth();

        //back arrow for navigating back to "ProfileActivity"
        ImageView backButton = (ImageView) view.findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigate back to profile");
                getActivity().finish();
            }
        });


        return view;
    }

    // save change
//    private void saveProfileSettings(){
//        final String displayName = eDisplayName.getText().toString();
//        final String username = eUsername.getText().toString();
//        final String website = eWebsite.getText().toString();
//        final String description = eDescription.getText().toString();
//        final String email = eEmail.getText().toString();
//        final long phoneNumber = Long.parseLong(ePhoneNumber.getText().toString());
//
//
//        eRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                User user = new User();
//                for(DataSnapshot dataSnp:  dataSnapshot.child(getString(R.string.dbname_users)).getChildren()){
//                    if(dataSnp.getKey().equals(userID)){
//                        user.setUsername(dataSnp.getValue(User.class).getUsername());
//                    }
//                }
//                Log.d(TAG, "onDataChange: current username: " + user.getUsername());
//
//                //if the user did not change username
//                if(user.getUsername().equals(username)){
//
//                }
//                //if the user changed username then need to check
//                else{
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//    }

    // call data by hand
//    private void setProfileImage(){
//        Log.d(TAG, "setProfileImage: set profile image");
//        String imgURL = "www.androidcentral.com/sites/androidcentral.com/files/styles/xlarge/public/article_images/2016/08/ac-lloyd.jpg?itok=bb72IeLf";
//        LoadUniversalImage.setImage(null, imgURL , "https://" , eProfileImage);
//    }

    // call data from database
    private void setProfileTool(SettingUser settingUser){
        Log.d(TAG, "setProfileTool: set tools based on data get from firebase: " + settingUser.toString());
        Log.d(TAG, "setProfileTool: set tools based on data get from firebase: " + settingUser.getUserSettings().getUsername());

        //User user = settingUser.getUser();
        UserAccountSettings settings = settingUser.getUserSettings();

        LoadUniversalImage.setImage(null,settings.getProfile_photo(), "",eProfileImage);

        eDisplayName.setText(settings.getDisplay_name());
        eUsername.setText(settings.getUsername());
        eWebsite.setText(settings.getWebsite());
        eDescription.setText(settings.getDescription());
        eEmail.setText(settingUser.getUser().getEmail());
        ePhoneNumber.setText(String.valueOf(settingUser.getUser().getPhone_number()));
    }

    //Firebase


//    private void setupFirebaseAuth(){
//        Log.d(TAG, "setupFirebaseAuth: set up firebase authentication");
//
//        eAuth = FirebaseAuth.getInstance();
//        eFbDatabase = FirebaseDatabase.getInstance();
//        eRef = eFbDatabase.getReference();
//        //userID = eAuth.getCurrentUser().getUid();
//
//        eAuthListener = new FirebaseAuth.AuthStateListener() {
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
//        eRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                //retrieve user information from the database
//                setProfileTool(eMethodFirebase.getSettingUser(dataSnapshot));
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
//        eAuth.addAuthStateListener(eAuthListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (eAuthListener != null) {
//            eAuth.removeAuthStateListener(eAuthListener);
//        }
//    }


}
