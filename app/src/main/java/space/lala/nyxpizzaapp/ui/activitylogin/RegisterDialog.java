package space.lala.nyxpizzaapp.ui.activitylogin;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import space.lala.nyxpizzaapp.databinding.RegisterDialogBinding;
import space.lala.nyxpizzaapp.utils.LoginButtonSwitcher;
import space.lala.nyxpizzaapp.utils.OnFieldChangeListener;
import space.lala.nyxpizzaapp.utils.PasswordTextWatcher;
import space.lala.nyxpizzaapp.utils.PhoneNumberTextWatcher;

public class RegisterDialog extends DialogFragment implements OnFieldChangeListener {
    private RegisterDialogBinding binding;
    private LoginButtonSwitcher buttonSwitcher;
    private boolean isAgreementAccepted = false;
    private String phoneNum = "+1 650-555-1234";

    private RegisterDialogViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = RegisterDialogBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(RegisterDialogViewModel.class);
        binding.applyRegistrationButton.setOnClickListener(view -> dismiss());

        binding.userPhoneRegistration.setText(phoneNum);

        binding.userPhoneRegistration.addTextChangedListener(
                new PhoneNumberFormattingTextWatcher("RU")
        );
        binding.userPhoneRegistration.addTextChangedListener(
                new PhoneNumberTextWatcher(binding.userPhoneRegistration, this)
        );
        binding.userPasswordRegistration.addTextChangedListener(
                new PasswordTextWatcher(this)
        );

        buttonSwitcher = new LoginButtonSwitcher(
                binding.userPhoneRegistration,
                binding.userPasswordRegistration,
                binding.applyRegistrationButton
        );

        binding.agreementRegistrationCheckbox.setOnClickListener(view -> {
            isAgreementAccepted = binding.agreementRegistrationCheckbox.isChecked();
            onFieldChange();
        });

        binding.applyRegistrationButton.setOnClickListener(view -> {

            //для БД Room
//            registerUser();
//            dismiss();
//            Toast.makeText(
//                    getContext(),
//                    "Пользователь зарегистрирован",
//                    Toast.LENGTH_LONG
//            ).show();
            String userPhoneNumber = binding.userPhoneRegistration.getText().toString().trim();

           Intent intent = new Intent(getContext(), VerifyPhoneActivity.class);
           intent.putExtra(VerifyPhoneActivity.USER_MOBILE, userPhoneNumber);
           startActivity(intent);
        });

        return binding.getRoot();
    }

    @Override
    public void onFieldChange() {
        buttonSwitcher.switchLoginButton(isAgreementAccepted);
    }

//    private void registerUser() {
//        final String userName = binding.usernameRegistration.getText().toString();
//        final String userPhone = binding.userPhoneRegistration.getText().toString();
//        final String userPassword = binding.userPasswordRegistration.getText().toString();
//        final String userEmail = binding.userEmailRegistration.getText().toString();
//        final boolean isSubscribed = binding.offersAndPromotionsCheckbox.isChecked();
//        final User user = new User(
//                userName,
//                userPhone,
//                userPassword,
//                userEmail,
//                isSubscribed
//        );
//        viewModel.insertUser(user);
//    }
}

