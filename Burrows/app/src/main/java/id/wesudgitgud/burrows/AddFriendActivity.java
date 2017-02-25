package id.wesudgitgud.burrows;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;

import id.wesudgitgud.burrows.Controller.DatabaseManager;
import id.wesudgitgud.burrows.models.User;

public class AddFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
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

    public void addFriend(View view) {
        String username = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        String newFriend = ((EditText) findViewById(R.id.formNewFriend)).getText().toString();
        DatabaseManager dbm = new DatabaseManager("user/" + newFriend);

        if (!dbm.getData().equals("null")) {
            User u = new User(username);
            u.addFriend(newFriend);
        } else {
            Toast.makeText(AddFriendActivity.this, "There's no user with username " + newFriend + "!", Toast.LENGTH_SHORT).show();
        }
    }
}
