package space.lala.nyxpizzaapp.ui.activitylogin;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

import space.lala.nyxpizzaapp.R;

public class LoginActivity extends AppCompatActivity {

    private final static String PASSWORD_PATTERN = "^[a-zA-Z0-9]*$";
    private EditText loginPhone;
    private EditText loginPassword;
    private Button loginButton;
    private TextView registerClick;
    private TextWatcher plusSignTextWatcher = new PlusSignTextWatcher();
    private TextWatcher passwordTextWatcher = new PasswordTextWatcher();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPhone = findViewById(R.id.login_phone);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        registerClick = findViewById(R.id.text_registration);

        registerClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        final PhoneNumberFormattingTextWatcher textWatcher =
                new PhoneNumberFormattingTextWatcher("RU");
        loginPhone.addTextChangedListener(textWatcher);
        loginPhone.addTextChangedListener(plusSignTextWatcher);

        loginPassword.addTextChangedListener(passwordTextWatcher);
    }

    private class PlusSignTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() > 0) {
                if (charSequence.charAt(0) == '+') {
                    loginPhone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
                } else {
                    loginPhone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
                }
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            switchLoginButton();
        }
    }

    private class PasswordTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String text = editable.toString();
            int length = text.length();
            if (length > 0 && !Pattern.matches(PASSWORD_PATTERN, text)) {
                editable.delete(length - 1, length);
            }

            switchLoginButton();
        }
    }

    private void switchLoginButton() {
        if (isPasswordInserted() && isPhoneNumberInserted()) {
            loginButton.setEnabled(true);
        } else {
            loginButton.setEnabled(false);
        }
    }

    private boolean isPasswordInserted() {
        return loginPassword.getText().length() >= 6;
    }

    private boolean isPhoneNumberInserted() {
        return (loginPhone.getText().length() >= 16 && loginPhone.getText().charAt(0) == '+') ||
                (loginPhone.getText().length() >= 15 && loginPhone.getText().charAt(0) != '+');
    }
}

