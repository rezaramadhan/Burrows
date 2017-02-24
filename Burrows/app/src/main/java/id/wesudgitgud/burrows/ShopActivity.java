package id.wesudgitgud.burrows;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        TextView titleMenu = (TextView)findViewById(R.id.textTitleMenu);
        titleMenu.setText("Shop");
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

    public void gotoAchievement(View v) {
        startActivity(new Intent(this, AchievementActivity.class));
    }

    public void gotoFriend(View v) {
        startActivity(new Intent(this, FriendsActivity.class));
    }
}
