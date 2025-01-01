package com.example.spliti.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.spliti.R;

public class SplitDialogFragment extends DialogFragment {

    private static final String ARG_POSITION = "position";
    private static final String ARG_USER_NAME = "user_name";
    private int position;
    private String userName;
    private boolean isPercentage = true;

    public interface SplitDialogListener {
        void onSplitValueChanged(int position, String value);
    }

    public static SplitDialogFragment newInstance(int position, String userName) {
        SplitDialogFragment fragment = new SplitDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        args.putString(ARG_USER_NAME, userName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
            userName = getArguments().getString(ARG_USER_NAME);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getDialog() != null && getDialog().getWindow() != null) {
            // Set the dialog to take 90% of the screen width
            int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.9); // 90% of the screen width
            int height = ViewGroup.LayoutParams.WRAP_CONTENT; // Dynamic height
            getDialog().getWindow().setLayout(width, height);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_split_dialog, container, false);

        ToggleButton toggleButton = view.findViewById(R.id.toggleInputMode);
        EditText inputField = view.findViewById(R.id.inputField);
        Button confirmButton = view.findViewById(R.id.confirmButton);
        TextView fragmentHeader = view.findViewById(R.id.fragmentHeader);

        fragmentHeader.append(userName);

        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isPercentage = !isChecked;
            inputField.setHint(isPercentage ? "Enter percentage (e.g., 50%)" : "Enter amount (e.g., $50.00)");
        });

        confirmButton.setOnClickListener(v -> {
            String value = inputField.getText().toString();
            if (getActivity() instanceof SplitDialogListener) {
                ((SplitDialogListener) getActivity()).onSplitValueChanged(position, isPercentage ? value + "%" : "$" + value);
            }
            dismiss();
        });

        return view;
    }
}


