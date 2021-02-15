package space.lala.nyxpizzaapp.utils;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.EditText;

public class PhoneNumberTextWatcher implements TextWatcher {
    private final EditText phoneField;
    private final OnFieldChangeListener onChangeListener;

    public PhoneNumberTextWatcher(EditText phoneField, OnFieldChangeListener onChangeListener) {
        this.phoneField = phoneField;
        this.onChangeListener = onChangeListener;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length() > 0) {
            if (charSequence.charAt(0) == '+') {
                phoneField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
            } else {
                phoneField.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        onChangeListener.onFieldChange();
    }
}
