package space.lala.nyxpizzaapp.ui.activitylogin;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;

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
    private FirebaseAuth auth;
    AuthCredential credential;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        loginPhone = findViewById(R.id.login_phone);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        registerClick = findViewById(R.id.text_registration);
        buttonSwitcher = new LoginButtonSwitcher(loginPhone, loginPassword, loginButton);
//
//        signInWithPhoneAuthCredential(credential);
        registerClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                new RegisterDialog().show(transaction, "Dialog");
            }
        });


        loginPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher("US"));
        loginPhone.addTextChangedListener(
                new PhoneNumberTextWatcher(loginPhone, this)
        );
        loginPassword.addTextChangedListener(new PasswordTextWatcher(this));
    }

    @Override
    public void onFieldChange() {
        buttonSwitcher.switchLoginButton();
    }


//    private void signInWithPhoneAuthCredential(AuthCredential credential) {
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                auth.getCurrentUser().reauthenticateAndRetrieveData(
//                        PhoneAuthProvider.getCredential(
//                                auth.getCurrentUser().getPhoneNumber();
//
////                auth.getCurrentUser().getPhoneNumber(
////                        (String)getText(Integer.parseInt(loginPhone.toString(),
////                                Integer.parseInt((String) getText(Integer.parseInt(loginPassword.toString()
////                                ))))));
//
//                Intent intent = new Intent(LoginActivity.this, OrderActivity.class);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                    startActivity(intent);
//
//
//
//
////                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
////               AuthCredential credential = PhoneAuthProvider
////                        .getCredential(loginPhone.toString(), loginPassword.toString());
////                user.reauthenticate(credential)
////                        .addOnCompleteListener(new OnCompleteListener<Void>() {
////                            @Override
////                            public void onComplete(@NonNull Task<Void> task) {
////                                Intent intent = new Intent(LoginActivity.this, OrderActivity.class);
////                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                                    startActivity(intent);
////                            }
////                        });
////                auth.signInWithCredential(credential)
////                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
////                            @Override
////                            public void onComplete(@NonNull Task<AuthResult> task) {
////                                if (task.isSuccessful()) {
////                                    Intent intent = new Intent(LoginActivity.this, OrderActivity.class);
////                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                                    startActivity(intent);
////                                } else {
////                                    Toast.makeText(LoginActivity.this, "Ошибка!", Toast.LENGTH_SHORT).show();
////
////                                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
////                                        Toast.makeText(LoginActivity.this, "Неверный номер телефона, либо пароль", Toast.LENGTH_LONG).show();
////                                    }
////                                }
////
////                            }
////                        });


    }


