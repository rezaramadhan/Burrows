package id.wesudgitgud.burrows;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ViewFlipper viewFlipper;
    Button next;
    Button prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView titleMenu = (TextView)findViewById(R.id.textTitleMenu);
        titleMenu.setText("Burrows");

        viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);
        next = (Button) findViewById(R.id.btnNext);
        prev = (Button) findViewById(R.id.btnPrev);

        next.setOnClickListener(this);
        prev.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if (view == next){
            viewFlipper.showNext();
        } else {
            viewFlipper.showPrevious();
        }
    }

    public void gotoMain(View v) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void gotoShop(View v) {
        startActivity(new Intent(MainActivity.this, ShopActivity.class));
    }

    public void gotoItem(View v) {
        startActivity(new Intent(MainActivity.this, ItemsActivity.class));
    }

    public void gotoAchievement(View v) {
        startActivity(new Intent(MainActivity.this, AchievementActivity.class));
    }

    public void gotoFriend(View v) {
        startActivity(new Intent(MainActivity.this, FriendsActivity.class));
    }
}
