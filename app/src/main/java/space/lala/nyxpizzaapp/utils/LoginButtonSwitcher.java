package space.lala.nyxpizzaapp.utils;

import android.widget.Button;
import android.widget.EditText;

public class LoginButtonSwitcher {
    private final EditText loginPhone;
    private final EditText loginPassword;
    private final Button loginButton;

    public LoginButtonSwitcher(EditText loginPhone, EditText loginPassword, Button loginButton) {
        this.loginPhone = loginPhone;
        this.loginPassword = loginPassword;
        this.loginButton = loginButton;
    }

    public void switchLoginButton() {
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
