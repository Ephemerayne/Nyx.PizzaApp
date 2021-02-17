package space.lala.nyxpizzaapp.ui.activityprofile;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import space.lala.nyxpizzaapp.R;

public class ProfileActivity extends AppCompatActivity {

    private TextView phone;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        phone = findViewById(R.id.profile_phone);
        name = findViewById(R.id.profile_name);
    }
}
