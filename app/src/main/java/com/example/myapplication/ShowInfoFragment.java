package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class ShowInfoFragment extends Fragment {
    private View rootView;
    private static final String USER = "user";
    private User user;
    public ShowInfoFragment() {
        // Required empty public constructor
    }

    public static ShowInfoFragment newInstance(User user) {
        ShowInfoFragment fragment = new ShowInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable(USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable(USER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate (R.layout.fragment_show_info, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout phoneContainer = rootView.findViewById(R.id.phone_container); // Assuming you have a LinearLayout in your layout XML with id "phone_container"

        EditText displayName = (EditText) rootView.findViewById(R.id.name_input);
        EditText displaySurname = (EditText) rootView.findViewById(R.id.surname_input);
        EditText displayCity = (EditText) rootView.findViewById(R.id.city_input);
        Spinner displayDepartment = (Spinner) rootView.findViewById(R.id.departments_input);
        EditText displayBirth = (EditText) rootView.findViewById(R.id.date_text);

        displayDepartment.setEnabled(false);

        if (user != null) {
            displayName.setText(user.getName());
            displaySurname.setText(user.getSurname());
            displayCity.setText(user.getCity());
            displayDepartment.setSelection(user.getDepartment());
            displayBirth.setText(user.getBirth());

            List<String> phones = user.getPhones();
            for (String phone : phones) {
                EditText phoneEditText = new EditText(requireContext());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                phoneEditText.setLayoutParams(params);
                phoneEditText.setText(phone);
                phoneEditText.setEnabled(false);
                phoneContainer.addView(phoneEditText);
            }
        }
    }
}