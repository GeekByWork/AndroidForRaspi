package com.example.techmint.myapplication;
        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button bLogout;
    EditText etName, etStudentId, etUsername;
    UserLocalStore userLocalStore;

    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String response = CommonUtilities.loginResponse;
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    private boolean authenticate() {
        return userLocalStore.getUserLoggedIn();
    }

    private void displayyUserDetails() {
        User user = userLocalStore.getLoggedInUser();
        etUsername.setText(user.username);
        etStudentId.setText(user.studentid);
        etName.setText(user.name);
    }


    public void btnClickLogout(View view) {
        //userLocalStore.clearUserData();
        //userLocalStore.setUserLoggedIn(false);
        startActivity(new Intent(this, Login.class));
    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}