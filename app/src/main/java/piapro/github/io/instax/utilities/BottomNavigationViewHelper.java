package piapro.github.io.instax.utilities;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import piapro.github.io.instax.FavoriteComponents.FavoriteActivity;
import piapro.github.io.instax.HomeComponents.HomeActivity;
import piapro.github.io.instax.ProfileComponents.ProfileActivity;
import piapro.github.io.instax.R;
import piapro.github.io.instax.SearchComponents.SearchActivity;
import piapro.github.io.instax.ShareComponents.ShareActivity;

public class BottomNavigationViewHelper {
    private static final String TAG = "BottomNavigationViewHelper"; //See logt
    public static void bottomNavigationViewSetup(BottomNavigationViewEx bottomNavigationViewEx){
        // Unable all unnecessary animation features
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);

    }

    //Setup method that navigating between activities
    public static void enableNavigation(final Context context, BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.ic_home:
                            Intent intentHome = new Intent(context, HomeActivity.class);//ACTIVITY=0
                            context.startActivity(intentHome);
                            break;

                    case R.id.ic_search:
                        Intent intentSearch = new Intent(context, SearchActivity.class);//ACTIVITY=1
                        context.startActivity(intentSearch);
                        break;

                    case R.id.ic_add:
                        Intent intentShare = new Intent(context, ShareActivity.class);//ACTIVITY=2
                        context.startActivity(intentShare);
                        break;

                    case R.id.ic_favorite:
                        Intent intentFavorite = new Intent(context, FavoriteActivity.class);//ACTIVITY=3
                        context.startActivity(intentFavorite);
                        break;

                    case R.id.ic_profile:
                        Intent intentProfile = new Intent(context, ProfileActivity.class);//ACTIVITY=4
                        context.startActivity(intentProfile);
                        break;

                }
                return false;
            }
        });
    }




}
