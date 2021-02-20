package space.lala.nyxpizzaapp.ui.activitymain;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import space.lala.nyxpizzaapp.R;
import space.lala.nyxpizzaapp.ui.BaseActivity;
import space.lala.nyxpizzaapp.ui.activitylogin.LoginActivity;

public class MainActivity extends BaseActivity {

    public static final String PROFILE_FRAGMENT_INTENT = "PROFILE_FRAGMENT_INTENT";

    private AppBarConfiguration appBarConfiguration;
    private Button enterAccount;

    private ConstraintLayout userInfoContainer;
    private TextView userPhone;
    private TextView userName;
    private TextView userLogout;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        enterAccount = ((LinearLayout) navigationView.getHeaderView(0)).findViewById(R.id.enter_account_button);

        Intent intent = getIntent();
        String intentMessage = intent.getStringExtra(PROFILE_FRAGMENT_INTENT);
        if (intentMessage != null) {
            if (intentMessage.equals(PROFILE_FRAGMENT_INTENT)) {
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.toProfile);
            }
        }

        enterAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        initNavViews(navigationView);
        userSignIn();

        appBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_profile,
                R.id.nav_home, R.id.nav_products_menu, R.id.nav_contacts)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void initNavViews(NavigationView navigationView) {
        userInfoContainer = navigationView.getHeaderView(0).findViewById(R.id.nav_user_info);
        userName = ((LinearLayout) navigationView.getHeaderView(0)).findViewById(R.id.nav_username);
        userPhone = ((LinearLayout) navigationView.getHeaderView(0)).findViewById(R.id.nav_user_phone);
        userLogout = ((LinearLayout) navigationView.getHeaderView(0)).findViewById(R.id.nav_logout);
    }

    public void userSignIn() {
        if (auth.getCurrentUser() != null) {

            userInfoContainer.setVisibility(View.VISIBLE);
            enterAccount.setVisibility(View.GONE);

            userName.setText(auth.getCurrentUser().getUid());
            userPhone.setText(auth.getCurrentUser().getPhoneNumber());

            userLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    auth.signOut();
                }
            });

        } else {
            NavigationView navigationView = findViewById(R.id.nav_view);
            Menu navMenu = navigationView.getMenu();
            navMenu.findItem(R.id.nav_profile).setVisible(false);

            userInfoContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


}