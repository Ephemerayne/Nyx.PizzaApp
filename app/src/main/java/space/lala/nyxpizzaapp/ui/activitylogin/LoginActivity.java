package space.lala.nyxpizzaapp.ui.activitylogin;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import space.lala.nyxpizzaapp.R;
import space.lala.nyxpizzaapp.utils.LoginButtonSwitcher;
import space.lala.nyxpizzaapp.utils.OnFieldChangeListener;
import space.lala.nyxpizzaapp.utils.PasswordTextWatcher;
import space.lala.nyxpizzaapp.utils.PhoneNumberTextWatcher;

public class LoginActivity extends AppCompatActivity implements OnFieldChangeListener {

    private EditText loginPhone;
    private EditText loginPassword;
    private Button loginButton;
    private TextView registerClick;
    private LoginButtonSwitcher buttonSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPhone = findViewById(R.id.login_phone);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        registerClick = findViewById(R.id.text_registration);
        buttonSwitcher = new LoginButtonSwitcher(loginPhone, loginPassword, loginButton);

        registerClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                new RegisterDialog().show(transaction, "Dialog");
            }
        });


        loginPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher("RU"));
        loginPhone.addTextChangedListener(
                new PhoneNumberTextWatcher(loginPhone, this)
        );
        loginPassword.addTextChangedListener(new PasswordTextWatcher(this));
    }


    @Override
    public void onFieldChange() {
        buttonSwitcher.switchLoginButton();
    }
}

