package id.wesudgitgud.burrows.models;

/**
 * Created by rezaramadhan on 23/02/2017.
 */

public class User {
    public String fullname;
    public String email;
    public int exp;
    public int money;
    public int highscore;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String fullname, String email) {
        this.fullname = fullname;
        this.email = email;
        this.exp = 0;
        this.money = 1000;
        this.highscore = 0;
    }

}
