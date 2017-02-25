package id.wesudgitgud.burrows;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static id.wesudgitgud.burrows.R.layout.inc_friend_panel;
import static id.wesudgitgud.burrows.R.layout.inc_friendchat;
import static id.wesudgitgud.burrows.R.layout.inc_mychat;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        addMyChat("Hallooo!");
        addFriendChat("Halo jugaaa!!");
        

    }

    public void addMyChat(String chat) {
            ViewGroup container = (ViewGroup)findViewById(R.id.container);
            View myLayout = getLayoutInflater().inflate(inc_mychat, null);
            TextView friendName = (TextView) myLayout.findViewById(R.id.myChat);
            friendName.setText(chat);
            container.addView(myLayout); // you can pass extra layout params here too
    }

    public void addFriendChat(String chat){
        ViewGroup container = (ViewGroup)findViewById(R.id.container);
        View myLayout = getLayoutInflater().inflate(inc_friendchat, null);
        TextView friendName = (TextView) myLayout.findViewById(R.id.myFriendChat);
        friendName.setText(chat);
        container.addView(myLayout); // you can pass extra layout params here too
    }
}
