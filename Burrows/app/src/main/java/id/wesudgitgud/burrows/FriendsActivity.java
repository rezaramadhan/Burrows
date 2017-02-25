package id.wesudgitgud.burrows;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static id.wesudgitgud.burrows.R.layout.inc_friend_panel;

public class FriendsActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        for(int i = 0; i<3; i++){
            addNewFriend("Friend" + i);
        }
    }

    public void addNewFriend(String name){
        ViewGroup container = (ViewGroup)findViewById(R.id.container);
        View myLayout = getLayoutInflater().inflate(inc_friend_panel, null);
        TextView friendName = (TextView) myLayout.findViewById(R.id.textFriendName);
        friendName.setText(name);
        container.addView(myLayout); // you can pass extra layout params here too

    }

    public void gotoMain(View v) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void gotoShop(View v) {
        startActivity(new Intent(this, ShopActivity.class));
    }

    public void gotoItem(View v) {
        startActivity(new Intent(this, ItemsActivity.class));
    }

    public void gotoAchievement(View v) { startActivity(new Intent(this, AchievementActivity.class)); }

    public void gotoFriend(View v) {
        startActivity(new Intent(this, FriendsActivity.class));
    }

<<<<<<< HEAD
    public void gotoChat(View v) {
        startActivity(new Intent(this, ChatActivity.class));
    }
=======
    public void getFriendLocation(View v)  { startActivity(new Intent(this, FriendLocationActivity.class)); }
>>>>>>> bda952dcf164c6a35a2b0b0c320e4193323856f5
}
