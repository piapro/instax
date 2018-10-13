package piapro.github.io.instax.utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import piapro.github.io.instax.R;
import piapro.github.io.instax.Profile.ProfileActivity;
import piapro.github.io.instax.modular.Comment;
import piapro.github.io.instax.modular.Like;
import piapro.github.io.instax.modular.User;
import piapro.github.io.instax.modular.UserAccountSettings;

public class UserFeedListAdapter extends ArrayAdapter<Photo> {
    /*
    private static final String TAG = "User Feed List Adapter";
    private LayoutInflater mInflater;
    private int mLayoutResource;
    private Context mContext;
    //private DatabaseReference mReference;
    private String currentAccountName= "";
    public UserFeedListAdapter(@NonNull Context context, int resource, @NonNull List<Photo> objects) {
        super(context, resource, objects);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayoutResource = resource;
        this.mContext = context;
*/
    }

    static class ViewHolder{
        CircleImageView avatar; //=> mprofileImage
        String likesString; //likesCollection
        TextView userName,timeStamp,location,likes,comments; //username,timeDelta,caption,likes,comments;
        SquareImageView image;
        ImageView redHeart,whiteHeart,commentBubble;

        UserAccountSettings settings = new UserAccountSettings();
        User user = new User();
        StringBuilder users;
        String mLikesString;
        boolean likedByCurrentUser;
        Heart icon_heart;//Create like object : heart(video)
        GestureDetector detector;
        Photo photo;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            convertView = mInflater.inflate(mLayoutResource,parent,false);
            viewHolder = new ViewHolder();

            //SquareImageView:layout_userfeed_listposts
            viewHolder.image = (SquareImageView)convertView.findViewById(R.id.post_image);

            //CircleImageView:profile_center_bar
            viewHolder.avatar = (CircleImageView)convertView.findViewById(R.id.profile_image);

            //ImageView:redHeart/whiteHeart/commentBubble
            viewHolder.redHeart = (ImageView)convertView.findViewById(R.id.icon_heart_red);
            viewHolder.whiteHeart = (ImageView)convertView.findViewById(R.id.icon_heart_white);
            viewHolder.commentBubble = (ImageView)convertView.findViewById(R.id.icon_speech_bubble);//do we need this function in the specification?

            //TextView userName,timeStamp,location,likes,comments; //username,timeDelta,caption,likes,comments;
            viewHolder.userName = (TextView) convertView.findViewById(R.id.username);
            viewHolder.timeStamp = (TextView) convertView.findViewById(R.id.image_time_posted);
            viewHolder.location = (TextView) convertView.findViewById(R.id.image_caption);
            viewHolder.likes = (TextView) convertView.findViewById(R.id.image_likes);
            viewHolder.comments = (TextView) convertView.findViewById(R.id.image_comments_link);

            //
            viewHolder.icon_heart = new Heart(viewHolder.whiteHeart,viewHolder.redHeart);
            viewHolder.photo = getItem(position);
            viewHolder.detector = new GestureDetector(mContext, new GestureListener(viewHolder));
            viewHolder.users = new StringBuilder();

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }



        return convertView;
    }
    public class GestureListener extends GestureDetector.SimpleOnGestureListener{
        /*
        ViewHolder holder;
        public GestureListener(ViewHolder viewHolder){
            holder = viewHolder;
        }
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d(TAG, "onDoubleTap: double tap detected.");

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            Query query = reference
                    .child(mContext.getString(R.string.dbname_photos))
                    .child(holder.photo.getPhoto_id())
                    .child(mContext.getString(R.string.field_likes));
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){

                        String keyID = singleSnapshot.getKey();

                        //case1: Then user already liked the photo
                        if(holder.likedByCurrentUser &&
                                singleSnapshot.getValue(Like.class).getUser_id()
                                        .equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){

                            mReference.child(mContext.getString(R.string.dbname_photos))
                                    .child(holder.photo.getPhoto_id())
                                    .child(mContext.getString(R.string.field_likes))
                                    .child(keyID)
                                    .removeValue();
///
                            mReference.child(mContext.getString(R.string.dbname_user_photos))
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .child(holder.photo.getPhoto_id())
                                    .child(mContext.getString(R.string.field_likes))
                                    .child(keyID)
                                    .removeValue();

                            holder.icon_heart.toggleLike();
                            getLikesString(holder);
                        }
                        //case2: The user has not liked the photo
                        else if(!holder.likedByCurrentUser){
                            //add new like
                            addNewLike(holder);
                            break;
                        }
                    }
                    if(!dataSnapshot.exists()){
                        //add new like
                        addNewLike(holder);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            return true;
        }
        */
    }

    private void getLikesString(final ViewHolder viewHolder) {
        /*
        Log.d(TAG, "getLikesString: getting likes string");

        try {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            Query query = reference
                    .child(mContext.getString(R.string.dbname_photos))
                    .child(viewHolder.photo.getPhoto_id())
                    .child(mContext.getString(R.string.field_likes));
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    viewHolder.users = new StringBuilder();
                    for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                        Query query = reference
                                .child(mContext.getString(R.string.dbname_users))
                                .orderByChild(mContext.getString(R.string.field_user_id))
                                .equalTo(singleSnapshot.getValue(Like.class).getUser_id());
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                                    Log.d(TAG, "onDataChange: found like: " +
                                            singleSnapshot.getValue(User.class).getUsername());

                                    viewHolder.users.append(singleSnapshot.getValue(User.class).getUsername());
                                    viewHolder.users.append(",");
                                }

                                String[] splitUsers = viewHolder.users.toString().split(",");

                                if (viewHolder.users.toString().contains(viewHolder.user.getUsername() + ",")) {//mitch, mitchell.tabian
                                    viewHolder.likedByCurrentUser = true;
                                } else {
                                    viewHolder.likedByCurrentUser = false;
                                }

                                int length = splitUsers.length;
                                if (length == 1) {
                                    mLikesString = "Liked by " + splitUsers[0];
                                } else if (length == 2) {
                                    mLikesString = "Liked by " + splitUsers[0]
                                            + " and " + splitUsers[1];
                                } else if (length == 3) {
                                    mLikesString = "Liked by " + splitUsers[0]
                                            + ", " + splitUsers[1]
                                            + " and " + splitUsers[2];

                                } else if (length == 4) {
                                    mLikesString = "Liked by " + splitUsers[0]
                                            + ", " + splitUsers[1]
                                            + ", " + splitUsers[2]
                                            + " and " + splitUsers[3];
                                } else if (length > 4) {
                                    mLikesString = "Liked by " + splitUsers[0]
                                            + ", " + splitUsers[1]
                                            + ", " + splitUsers[2]
                                            + " and " + (splitUsers.length - 3) + " others";
                                }
                                Log.d(TAG, "onDataChange: likes string: " + viewHolder.likesString);
                                setupLikesString(viewHolder, viewHolder.likesString);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                    if (!dataSnapshot.exists()) {
                        viewHolder.likesString = "";
                        viewHolder.likedByCurrentUser = false;
                        setupLikesString(viewHolder, viewHolder.likesString);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } catch (NullPointerException e) {
            Log.e(TAG, "getLikesString:NullPointerException ", e.getMessage());
            viewHolder.likesString = "";
            viewHolder.likedByCurrentUser = false;
            //Likes Strings Setting
            setupLikesString(viewHolder, viewHolder.likesString);

        }
*/
    }
    private void addNewLike(final ViewHolder viewHolder){
        /*
        Log.d(TAG, "addNewLike: adding new like");

        String newLikeID = mReference.push().getKey();
        Like like = new Like();
        like.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());

        mReference.child(mContext.getString(R.string.dbname_photos))
                .child(viewHolder.photo.getPhoto_id())
                .child(mContext.getString(R.string.field_likes))
                .child(newLikeID)
                .setValue(like);

        mReference.child(mContext.getString(R.string.dbname_user_photos))
                .child(viewHolder.photo.getUser_id())
                .child(viewHolder.photo.getPhoto_id())
                .child(mContext.getString(R.string.field_likes))
                .child(newLikeID)
                .setValue(like);

        viewHolder.icon_heart.toggleLike();
        getLikesString(viewHolder);
        */

    }

    private void getCurrentUserName(){

        /*
        Log.d(TAG, "getCurrentUserName: Getting User Account Settings...");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference
                .child(mContext.getString(R.string.dbname_users))
                .orderByChild(mContext.getString(R.string.field_user_id))
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                    Query query = reference
                            .child(mContext.getString(R.string.dbname_users))
                            .orderByChild(mContext.getString(R.string.field_user_id))
                            .equalTo(singleSnapshot.getValue(Like.class).getUser_id());
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        */
    }

    private void setupLikesString(final ViewHolder viewHolder, String likesString){
        Log.d(TAG, "setupLikesString: likes String:"+ viewHolder.likesString);
        if(viewHolder.likedByCurrentUser){
            Log.d(TAG, "setupLikesString: Photo liked by the current user!");
            viewHolder.whiteHeart.setVisibility(View.GONE);
            viewHolder.redHeart.setVisibility(View.VISIBLE);
            viewHolder.redHeart.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return viewHolder.detector.onTouchEvent(event);
                }
            });
        }else{
            Log.d(TAG, "setupLikesString: Photo disliked by the current user!");
            viewHolder.redHeart.setVisibility(View.GONE);
            viewHolder.whiteHeart.setVisibility(View.VISIBLE);
            viewHolder.whiteHeart.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return viewHolder.detector.onTouchEvent(event);
                }
            });
        }
        viewHolder.likes.setText(likesString);
    }
    /*
    private String getDifferenceTimeStamp(Photo photo){
        Log.d(TAG, "getTimestampDifference: getting timestamp difference.");

        String difference = "";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.CANADA);
        sdf.setTimeZone(TimeZone.getTimeZone("Canada/Pacific"));//google 'android list of timezones'
        Date today = c.getTime();
        sdf.format(today);
        Date timestamp;
        final String photoTimestamp = photo.getDate_created();
        try{
            timestamp = sdf.parse(photoTimestamp);
            difference = String.valueOf(Math.round(((today.getTime() - timestamp.getTime()) / 1000 / 60 / 60 / 24 )));
        }catch (ParseException e){
            Log.e(TAG, "getTimestampDifference: ParseException: " + e.getMessage() );
            difference = "0";
        }
        return difference;
    }
    */
}
