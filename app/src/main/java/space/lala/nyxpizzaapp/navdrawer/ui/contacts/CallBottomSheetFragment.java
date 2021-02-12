package space.lala.nyxpizzaapp.navdrawer.ui.contacts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import space.lala.nyxpizzaapp.R;

public class CallBottomSheetFragment extends BottomSheetDialogFragment {
    private static final String numberKey = "numberKey";
    private String number;

    public static CallBottomSheetFragment newInstance(String number) {
        CallBottomSheetFragment callBottomSheetFragment = new CallBottomSheetFragment();
        Bundle args = new Bundle();
        args.putString(numberKey, number);
        callBottomSheetFragment.setArguments(args);
        return callBottomSheetFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_dialog_list_call, container, false);

        if (getArguments() != null) {
            number = getArguments().getString(numberKey);
        }

        Button call = view.findViewById(R.id.button_call);
        Button cancel = view.findViewById(R.id.button_cancel);

        call.setText(number);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel: " + number));
                startActivity(callIntent);
            }

        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();

            }
        });
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
