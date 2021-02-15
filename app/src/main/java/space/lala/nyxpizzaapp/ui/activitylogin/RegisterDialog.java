package space.lala.nyxpizzaapp.ui.activitylogin;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import space.lala.nyxpizzaapp.databinding.RegisterDialogBinding;
import space.lala.nyxpizzaapp.utils.LoginButtonSwitcher;
import space.lala.nyxpizzaapp.utils.OnFieldChangeListener;
import space.lala.nyxpizzaapp.utils.PasswordTextWatcher;
import space.lala.nyxpizzaapp.utils.PhoneNumberTextWatcher;

public class RegisterDialog extends DialogFragment implements OnFieldChangeListener {
    private RegisterDialogBinding binding;
    private LoginButtonSwitcher buttonSwitcher;
    private boolean isAgreementAccepted = false;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = RegisterDialogBinding.inflate(inflater, container, false);
        binding.applyRegistrationButton.setOnClickListener(view -> dismiss());

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

        return binding.getRoot();
    }

    @Override
    public void onFieldChange() {
        buttonSwitcher.switchLoginButton(isAgreementAccepted);
    }
}

