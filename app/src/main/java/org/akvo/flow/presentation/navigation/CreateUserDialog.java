/*
 * Copyright (C) 2017 Stichting Akvo (Akvo Foundation)
 *
 * This file is part of Akvo Flow.
 *
 * Akvo Flow is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Akvo Flow is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Akvo Flow.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.akvo.flow.presentation.navigation;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.akvo.flow.R;

//TODO: rotating dialog, the text gets lost
public class CreateUserDialog extends DialogFragment {

    public static final String TAG = "CreateUserDialog";

    private CreateUserListener listener;
    private EditText userNameEt;

    public CreateUserDialog() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        FragmentActivity activity = getActivity();
        if (activity instanceof CreateUserListener) {
            listener = (CreateUserListener) activity;
        } else {
            throw new IllegalArgumentException("Activity must implement CreateUserListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Context context = getContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View main = LayoutInflater.from(context).inflate(R.layout.user_name_input_dialog, null);
        builder.setTitle(R.string.add_user);
        userNameEt = (EditText) main.findViewById(R.id.user_name_et);
        userNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {
                //EMPTY
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //EMPTY
            }

            @Override
            public void afterTextChanged(Editable s) {
                updatePositiveButton();
            }
        });
        builder.setView(main);
        builder.setPositiveButton(R.string.okbutton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = userNameEt.getText().toString();
                if (listener != null) {
                    listener.createUser(name);
                }
                dismiss();
            }
        });

        builder.setNegativeButton(R.string.cancelbutton,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       dismiss();
                    }
                });

        return builder.create();
    }

    private void updatePositiveButton() {
        String text = userNameEt.getText().toString();
        if (TextUtils.isEmpty(text)) {
            disablePositiveButton();
        } else {
            enablePositiveButton();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updatePositiveButton();
    }

    private void disablePositiveButton() {
        Button button = getPositiveButton();
        button.setEnabled(false);
    }

    private Button getPositiveButton() {
        AlertDialog dialog = (AlertDialog) getDialog();
        return dialog.getButton(AlertDialog.BUTTON_POSITIVE);
    }

    private void enablePositiveButton() {
        Button button = getPositiveButton();
        button.setEnabled(true);
    }

    public interface CreateUserListener {

        void createUser(String userName);
    }
}
