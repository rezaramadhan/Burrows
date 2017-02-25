package id.wesudgitgud.burrows;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;

import java.util.List;

import id.wesudgitgud.burrows.Controller.DatabaseManager;

public class AchievementActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);

        TextView titleMenu = (TextView)findViewById(R.id.textTitleMenu);
        titleMenu.setText("Achievement");

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            getHighscore();
        } catch (JSONException e) {
            Log.e("error","json not found");
            e.printStackTrace();
        }
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

    public void shareHighscore(View v) {
        Log.d("share","Share highscore");
        TextView score = (TextView) findViewById(R.id.highscore);
        String message = R.string.share_template_1 + score.getText().toString() + R.string.share_template_2;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,message);

        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;
        if (isIntentSafe)
            startActivity(intent);
    }

    public void getHighscore() throws JSONException {
        FirebaseUser FU = FirebaseAuth.getInstance().getCurrentUser();
        if (FU != null) {
            String username = "user/" + FU.getDisplayName();
            DatabaseManager data = new DatabaseManager(username);
            Log.d("test", "testdone");
            String score = data.getJSONDObject().getString("highscore");
            TextView score_view = (TextView) findViewById(R.id.highscore);
            score_view.setText(score);
        }
    }
}
