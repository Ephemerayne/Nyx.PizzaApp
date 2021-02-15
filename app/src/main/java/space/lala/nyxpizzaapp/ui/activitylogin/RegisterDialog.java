package space.lala.nyxpizzaapp.ui.activitylogin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import space.lala.nyxpizzaapp.databinding.RegisterDialogBinding;

public class RegisterDialog extends DialogFragment {
    private RegisterDialogBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = RegisterDialogBinding.inflate(inflater, container, false);
        binding.applyRegistrationButton.setOnClickListener(view -> getActivity().onBackPressed());


        return binding.getRoot();
    }
}

