package id.wesudgitgud.burrows;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoShop(View v) {
        startActivity(new Intent(MainActivity.this, ShopActivity.class));
    }

    public void gotoItem(View v) {
        startActivity(new Intent(MainActivity.this, ItemsActivity.class));
    }
}
