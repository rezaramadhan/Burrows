package id.wesudgitgud.burrows;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import id.wesudgitgud.burrows.Controller.DatabaseManager;
import id.wesudgitgud.burrows.models.Item;
import id.wesudgitgud.burrows.models.User;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

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

    public void buyApple(View v) {

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                // code goes here.
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if (firebaseUser != null) {
                    String username = firebaseUser.getDisplayName();

                    User u = new User(username);
                    u.retrieveUserData(username);

                    if (u.money >= 500) {
                        u.money -= 500;


                        DatabaseReference userDB = FirebaseDatabase.getInstance().getReference().child("user").child(username).child("money");
                        Log.d("buyitem","setvalueuser");
                        userDB.setValue(u.money);
                        Log.d("buyitem","setvalueitem");
                        Item apple = new Item("fruit", "apple", 500);
                        apple.buyItem(username);

                        Log.d("TAG", "OK");
//                        Toast.makeText(ShopActivity.this, "Apple bought!", Toast.LENGTH_SHORT).show();
                    } else {
//                        Toast.makeText(ShopActivity.this, "You don't have enough money!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.w("Shop_Activity", "No user logged on");
                }
            }
        });
        t1.start();
    }

    public void buyGrape(View v) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                // code goes here.
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if (firebaseUser != null) {
                    String username = firebaseUser.getDisplayName();

                    User u = new User(username);
                    u.retrieveUserData(username);

                    if (u.money >= 500) {
                        u.money -= 500;

                        DatabaseReference userDB = FirebaseDatabase.getInstance().getReference().child("user").child(username).child("money");
                        Log.d("buyitem","setvalueuser");
                        userDB.setValue(u.money);
                        Log.d("buyitem","setvalueitem");
                        Item apple = new Item("fruit", "grape", 700);
                        apple.buyItem(username);

                        Log.d("TAG", "OK");
//                        Toast.makeText(ShopActivity.this, "Apple bought!", Toast.LENGTH_SHORT).show();
                    } else {
//                        Toast.makeText(ShopActivity.this, "You don't have enough money!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.w("Shop_Activity", "No user logged on");
                }
            }
        });
        t1.start();
    }

    public void buyBanana(View v) {

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                // code goes here.
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if (firebaseUser != null) {
                    String username = firebaseUser.getDisplayName();

                    User u = new User(username);
                    u.retrieveUserData(username);

                    if (u.money >= 300) {
                        u.money -= 300;


                        DatabaseReference userDB = FirebaseDatabase.getInstance().getReference().child("user").child(username).child("money");
                        Log.d("buyitem","setvalueuser");
                        userDB.setValue(u.money);
                        Log.d("buyitem","setvalueitem");
                        Item apple = new Item("fruit", "banana", 300);
                        apple.buyItem(username);

                        Log.d("TAG", "OK");
//                        Toast.makeText(ShopActivity.this, "Apple bought!", Toast.LENGTH_SHORT).show();
                    } else {
//                        Toast.makeText(ShopActivity.this, "You don't have enough money!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.w("Shop_Activity", "No user logged on");
                }
            }
        });
        t1.start();
    }
}
