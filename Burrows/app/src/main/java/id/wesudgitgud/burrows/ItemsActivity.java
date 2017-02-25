package id.wesudgitgud.burrows;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessagingService;

import org.json.JSONArray;
import org.json.JSONException;

import id.wesudgitgud.burrows.Controller.DatabaseManager;

public class ItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        TextView titleMenu = (TextView)findViewById(R.id.textTitleMenu);
        titleMenu.setText("Items");

        loadItemsQuantity();
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

    public void loadItemsQuantity() {
        FirebaseUser usr = FirebaseAuth.getInstance().getCurrentUser();
        if (usr != null) {
            String username = usr.getDisplayName();
            DatabaseManager db = new DatabaseManager("useritem/" + username + "/fruit");
            if (!db.getData().equals("null")) {
                try {
                    JSONArray items = db.getJSONArray();
                    int idx = findFruit(items, "apple");
                    int nApple = 0, nGrape = 0, nBanana = 0;
                    if (idx != -1)
                        nApple = items.getJSONObject(idx).getInt("quantity");

                    idx = findFruit(items, "grape");
                    if (idx != -1)
                        nGrape = items.getJSONObject(idx).getInt("quantity");

                    idx = findFruit(items, "banana");
                    if (idx != -1)
                        nBanana = items.getJSONObject(idx).getInt("quantity");

                    TextView apple = (TextView) findViewById(R.id.n_Apple);
                    TextView grape = (TextView) findViewById(R.id.n_Grape);
                    TextView banana = (TextView) findViewById(R.id.n_Banana);

                    apple.setText(Integer.toString(nApple));
                    grape.setText(Integer.toString(nGrape));
                    banana.setText(Integer.toString(nBanana));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void useApple() {
        String napple = ((TextView) findViewById(R.id.n_Apple)).toString();
        if (Integer.parseInt(napple) > 0) {
            FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
            String username = u.getDisplayName();
            DatabaseManager db = new DatabaseManager("useritem/" + username + "/fruits");
            try {
                JSONArray j = db.getJSONArray();
                int idx = findFruit(j, "apple");

                //int q = j.getJSONObject(idx).


            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(ItemsActivity.this, "You dont have an apple yet!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void useGrape() {

    }

    public void useBanana(){

    }

    public int findFruit(JSONArray j, String fruitName) throws JSONException {
        for (int i = 0; i < j.length(); i++) {
            if (j.getJSONObject(i).getString("name").equals(fruitName))
                return i;
        }
        return -1;
    }
}
