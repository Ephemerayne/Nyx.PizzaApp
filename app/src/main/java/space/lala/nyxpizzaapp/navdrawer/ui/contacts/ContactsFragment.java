package space.lala.nyxpizzaapp.navdrawer.ui.contacts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import space.lala.nyxpizzaapp.R;


public class ContactsFragment extends Fragment {
    TextView numberTextView;
    String callNumber = "";
    TextView mailTextView;
    ImageView vk;
    ImageView fb;
    ImageView tlg;
    ImageView inst;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        Spinner spinner = view.findViewById(R.id.spinner_city);
        numberTextView = view.findViewById(R.id.text_view_callnumber);
        mailTextView = view.findViewById(R.id.text_view_mail_press);
        vk = view.findViewById(R.id.icon_vk);
        fb = view.findViewById(R.id.icon_fb);
        tlg = view.findViewById(R.id.icon_tlg);
        inst = view.findViewById(R.id.icon_inst);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        numberTextView.setText(getString(R.string.moscow_number));
                        callNumber = getString(R.string.moscow_number);
                        break;
                    case 1:
                        numberTextView.setText(getString(R.string.spb_number));
                        callNumber = getString(R.string.spb_number);
                        break;
                    case 2:
                        numberTextView.setText(getString(R.string.kazan_number));
                        callNumber = getString(R.string.kazan_number);
                        break;
                    case 3:
                        numberTextView.setText(getString(R.string.novosibirsk_number));
                        callNumber = getString(R.string.novosibirsk_number);
                        break;
                    case 4:
                        numberTextView.setText(getString(R.string.sochi_number));
                        callNumber = getString(R.string.sochi_number);
                        break;
                }


                numberTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showBottomSheetDialogCall();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void showBottomSheetDialogCall() {
                FragmentTransaction transaction =
                        getActivity().getSupportFragmentManager().beginTransaction();

                CallBottomSheetFragment.newInstance(callNumber).show(transaction, "tag");

            }
        });

        mailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialogMail();
            }


            public void showBottomSheetDialogMail() {
                FragmentTransaction transaction =
                        getActivity().getSupportFragmentManager().beginTransaction();

                new MailBottomSheetFragment().show(transaction, "tag");
            }
        });


        //SOCIAL BUTTONS LISTENER//
        vk.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        vk.setImageResource(R.mipmap.vk__click);
                        break;
                    case MotionEvent.ACTION_UP:
                        vk.setImageResource(R.mipmap.vk);
                        Uri uri = Uri.parse("http://vk.com");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        vk.setImageResource(R.mipmap.vk);
                        break;
                }
                return true;
            }
        });

        fb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        fb.setImageResource(R.mipmap.facebook_click);
                        break;
                    case MotionEvent.ACTION_UP:
                        fb.setImageResource(R.mipmap.facebook);
                        Uri uri = Uri.parse("http://facebook.com");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        fb.setImageResource(R.mipmap.facebook);
                        break;
                }
                return true;
            }
        });

        tlg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        tlg.setImageResource(R.mipmap.telegram__click);
                        break;
                    case MotionEvent.ACTION_UP:
                        tlg.setImageResource(R.mipmap.telegram);
                        Uri uri = Uri.parse("http://telegram.org");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        tlg.setImageResource(R.mipmap.telegram);
                        break;
                }
                return true;
            }
        });

        inst.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        inst.setImageResource(R.mipmap.instagram_click);
                        break;
                    case MotionEvent.ACTION_UP:
                        inst.setImageResource(R.mipmap.instagram);
                        Uri uri = Uri.parse("http://instagram.com");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        inst.setImageResource(R.mipmap.instagram);
                        break;
                }
                return true;
            }
        });

        return view;
    }
}