package space.lala.nyxpizzaapp.ui.activitylogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import space.lala.nyxpizzaapp.R;
import space.lala.nyxpizzaapp.ui.activitymain.MainActivity;

public class VerifyPhoneActivity extends AppCompatActivity {
    public static final String USER_MOBILE = "userPhoneNumber";
    private String testVerificationCode = "122334";

    private EditText editTextCode;
    private Button signIn;
    private String mVerificationId;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);

        mAuth = FirebaseAuth.getInstance();
        editTextCode = findViewById(R.id.editTextCode);
        signIn = findViewById(R.id.buttonSignIn);

        editTextCode.setText(testVerificationCode);
        editTextCode.setImeOptions(EditorInfo.IME_ACTION_DONE);

        Intent intent = getIntent();
        String userPhoneNumber = intent.getStringExtra(USER_MOBILE);
        sendVerificationCode(userPhoneNumber);

        signIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String code = editTextCode.getText().toString().trim();
                verifyVerificationCode(code);
            }
        });
    }


    private void sendVerificationCode(String userPhoneNumber) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(userPhoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(mCallbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }


    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                editTextCode.setText(code);
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(VerifyPhoneActivity.this, "Failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            mVerificationId = s;
        }
    };

    private void verifyVerificationCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifyPhoneActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent intent = new Intent(VerifyPhoneActivity.this, MainActivity.class);
                            intent.putExtra(MainActivity.PROFILE_FRAGMENT_INTENT, MainActivity.PROFILE_FRAGMENT_INTENT);
                            startActivity(intent);

                            Toast.makeText(VerifyPhoneActivity.this, "Успешная регистрация! Вход выполнен", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(VerifyPhoneActivity.this, "Ошибка!", Toast.LENGTH_SHORT).show();

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(VerifyPhoneActivity.this, "Введен неверный код, введите снова", Toast.LENGTH_LONG).show();
                            }
                        }

                    }
                });
    }
}