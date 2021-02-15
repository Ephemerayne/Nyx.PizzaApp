package space.lala.nyxpizzaapp.utils;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.regex.Pattern;

public class PasswordTextWatcher implements TextWatcher {
    private final static String PASSWORD_PATTERN = "^[a-zA-Z0-9]*$";
    private final OnFieldChangeListener onFieldChangeListener;

    public PasswordTextWatcher(OnFieldChangeListener onChangeListener) {
        this.onFieldChangeListener = onChangeListener;
    }

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

        onFieldChangeListener.onFieldChange();
    }
}

