package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InsertInfoFragment extends Fragment implements IDate {

    private View rootView;
    private IShow mainActivity;
    private User user;
    private final List<EditText> editTextList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_insertinfo, container, false);
        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (IShow) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setOnClickValidateButton();
        setOnClickShowDialogButton();
        setOnClickAddPhoneButton();


        if (savedInstanceState != null) {
            List<String> phones = savedInstanceState.getStringArrayList("phones");

            if (phones != null && phones.size() > 0 ){
                for (String phone: phones) {
                    addEditTextAndButton((LinearLayout) rootView.findViewById(R.id.layout), phone);
                }
            }
        }
    }

    private void setOnClickValidateButton() {
        Button button = rootView.findViewById(R.id.validate);

        EditText name = rootView.findViewById(R.id.name_input);
        EditText surname = rootView.findViewById(R.id.surname_input);
        EditText city = rootView.findViewById(R.id.city_input);
        EditText birth = rootView.findViewById(R.id.date_text);
        Spinner department = rootView.findViewById(R.id.departments_input);

        if (button != null){
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    user = new User(
                            name.getText().toString(),
                            surname.getText().toString(),
                            city.getText().toString(),
                            birth.getText().toString(),
                            department.getSelectedItemPosition(),
                            getPhoneNumbers());
                    mainActivity.onValidate(user);
                }
            });
        }
    }

    private void setOnClickShowDialogButton() {
        Button pickDate = rootView.findViewById(R.id.show_dialog);

        if (pickDate != null) {
            pickDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDatePickerDialog();
                }
            });
        }
    }

    private void setOnClickAddPhoneButton() {
        Button addButton = rootView.findViewById(R.id.add_phone);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEditTextAndButton((LinearLayout) rootView.findViewById(R.id.layout), null);
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setOnDateSelectedListener(this); // Set the listener
        newFragment.show(getChildFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSelected(String date) {
        EditText birth = rootView.findViewById(R.id.date_text);
        if (birth != null) {
            birth.setText(date); // Set the selected date to the EditText
        }
    }

    private void addEditTextAndButton(LinearLayout container, String textValue) {
        // Create a horizontal LinearLayout to hold EditText, Call Button, and Delete Button
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        LinearLayout horizontalLayout = new LinearLayout(getActivity());
        horizontalLayout.setLayoutParams(layoutParams);
        horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);

        // Create EditText
        EditText editText = new EditText(getActivity());
        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1
        );
        editText.setLayoutParams(editTextParams);
        editTextList.add(editText);

        if (textValue != null ){
            editText.setText(textValue);
        }

        // Create Call Button
        Button callButton = new Button(getActivity());

        callButton.setBackgroundResource(android.R.drawable.ic_menu_call);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                100,
                100
                );
        callButton.setLayoutParams(buttonParams);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editText.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });

        // Create Delete Button
        Button deleteButton = new Button(getActivity());

        deleteButton.setBackgroundResource(android.R.drawable.ic_menu_close_clear_cancel);
        deleteButton.setLayoutParams(buttonParams);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove the layout containing EditText, Call Button, and Delete Button
                container.removeView(horizontalLayout);
                // Remove EditText from the list
                editTextList.remove(editText);
            }
        });

        // Add EditText, Call Button, and Delete Button to the horizontal layout
        horizontalLayout.addView(editText);
        horizontalLayout.addView(callButton);
        horizontalLayout.addView(deleteButton);

        // Add the horizontal layout to the container
        container.addView(horizontalLayout);
    }

    // Method to retrieve the list of phone numbers entered in EditText fields
    public List<String> getPhoneNumbers() {
        List<String> phoneNumbers = new ArrayList<>();
        for (EditText editText : editTextList) {
            phoneNumbers.add(editText.getText().toString());
        }
        return phoneNumbers;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (user != null) {
            outState.putStringArrayList("phones", (ArrayList<String>) user.getPhones());
        }
    }

    private void openWikipediaPage(String cityName) {
        Uri webpage = Uri.parse("http://fr.wikipedia.org/?search=" + Uri.encode(cityName));
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        if (intent.resolveActivity(getContext().getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(requireContext(), "Aucune application pour ouvrir le lien", Toast.LENGTH_LONG).show();
        }
    }

    private void shareCityName() {
        EditText cityNameEditText = rootView.findViewById(R.id.city_input);
        String cityName = cityNameEditText.getText().toString();

        if (!cityName.isEmpty()) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Je suis né(e) à " + cityName + ".");

            Intent chooser = Intent.createChooser(shareIntent, "Partager via");
            if (shareIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
                startActivity(chooser);
            } else {
                Toast.makeText(requireContext(), "Aucune application disponible pour partager", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(requireContext(), "Veuillez d'abord entrer le nom d'une ville", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu); // Replace fragment_menu with your menu file name

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_wikipedia) {
            EditText city = rootView.findViewById(R.id.city_input);
            openWikipediaPage(city.getText().toString());
            return true;
        } else if (item.getItemId() == R.id.action_share) {
            shareCityName();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
