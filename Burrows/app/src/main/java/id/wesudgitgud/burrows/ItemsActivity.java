package id.wesudgitgud.burrows;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import id.wesudgitgud.burrows.Controller.DatabaseManager;

public class ItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

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

    public void useApple(View v) {
        TextView apel = (TextView) findViewById(R.id.n_Apple);
        String napple = apel.getText().toString();
        if (Integer.parseInt(napple) > 0) {

            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    try {
                        FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
                        String username = u.getDisplayName();
                        DatabaseManager db = new DatabaseManager("useritem/" + username + "/fruit");
                        JSONArray j = db.getJSONArray();
                        int idx = findFruit(j, "apple");

                        int q = j.getJSONObject(idx).getInt("quantity");
                        q = q - 1;

                        DatabaseReference d = FirebaseDatabase.getInstance().getReference().child("useritem")
                                .child(username).child("fruit").child(Integer.toString(idx)).child("quantity");

                        d.setValue(q);

                        TextView appel = (TextView) findViewById(R.id.n_Apple);
                        appel.setText(Integer.toString(q));

                        db.setURL("userpet/" + username + "/rabbit");
                        JSONObject jo = db.getJSONDObject();

                        int exp = jo.getInt("exp");

                        exp += 500;

                        d = FirebaseDatabase.getInstance().getReference().child("userpet").child(username).child("rabbit").child("exp");

                        d.setValue(exp);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.run();
            Toast.makeText(ItemsActivity.this, "Apple used to gained Rabbit's exp !",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ItemsActivity.this, "You dont have an apple yet!",
                    Toast.LENGTH_SHORT).show();
        }
    }


    public void useGrape(View v) {
        TextView apel = (TextView) findViewById(R.id.n_Grape);
        String napple = apel.getText().toString();
        if (Integer.parseInt(napple) > 0) {

            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    try {
                        FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
                        String username = u.getDisplayName();
                        DatabaseManager db = new DatabaseManager("useritem/" + username + "/fruit");
                        JSONArray j = db.getJSONArray();
                        int idx = findFruit(j, "grape");

                        int q = j.getJSONObject(idx).getInt("quantity");
                        q = q - 1;

                        DatabaseReference d = FirebaseDatabase.getInstance().getReference().child("useritem")
                                .child(username).child("fruit").child(Integer.toString(idx)).child("quantity");

                        d.setValue(q);

                        TextView appel = (TextView) findViewById(R.id.n_Grape);
                        appel.setText(Integer.toString(q));

                        db.setURL("userpet/" + username + "/mouse");
                        JSONObject jo = db.getJSONDObject();

                        int exp = jo.getInt("exp");

                        exp += 300;

                        d = FirebaseDatabase.getInstance().getReference().child("userpet").child(username).child("mouse").child("exp");

                        d.setValue(exp);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.run();
            Toast.makeText(ItemsActivity.this, "Grape used to gained Mouse's exp !",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ItemsActivity.this, "You dont have an grape yet!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void useBanana(View view){
        TextView apel = (TextView) findViewById(R.id.n_Banana);
        String napple = apel.getText().toString();
        if (Integer.parseInt(napple) > 0) {

            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    try {
                        FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
                        String username = u.getDisplayName();
                        DatabaseManager db = new DatabaseManager("useritem/" + username + "/fruit");
                        JSONArray j = db.getJSONArray();
                        int idx = findFruit(j, "banana");

                        int q = j.getJSONObject(idx).getInt("quantity");
                        q = q - 1;

                        DatabaseReference d = FirebaseDatabase.getInstance().getReference().child("useritem")
                                .child(username).child("fruit").child(Integer.toString(idx)).child("quantity");

                        d.setValue(q);

                        TextView appel = (TextView) findViewById(R.id.n_Banana);
                        appel.setText(Integer.toString(q));

                        db.setURL("userpet/" + username + "/beaver");
                        JSONObject jo = db.getJSONDObject();

                        int exp = jo.getInt("exp");

                        exp += 700;

                        d = FirebaseDatabase.getInstance().getReference().child("userpet").child(username).child("beaver").child("exp");

                        d.setValue(exp);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            t1.run();
            Toast.makeText(ItemsActivity.this, "Banana used to gained Beaver's exp !",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ItemsActivity.this, "You dont have any Banana yet!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public int findFruit(JSONArray j, String fruitName) throws JSONException {
        for (int i = 0; i < j.length(); i++) {
            if (j.getJSONObject(i).getString("name").equals(fruitName))
                return i;
        }
        return -1;
    }
}
