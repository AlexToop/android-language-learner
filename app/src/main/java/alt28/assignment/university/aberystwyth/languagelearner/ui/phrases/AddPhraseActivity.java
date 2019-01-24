/*
 * Copyright (c) 2018. Created by Alexander Toop. Assignment for Aberystwyth University.
 */

package alt28.assignment.university.aberystwyth.languagelearner.ui.phrases;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import alt28.assignment.university.aberystwyth.languagelearner.R;
import alt28.assignment.university.aberystwyth.languagelearner.model.views.AddPhraseViewModel;
import alt28.assignment.university.aberystwyth.languagelearner.model.Phrase;


/**
 * Allows for a new phrase to be recorded by the user
 */
public class AddPhraseActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText nativeTranslationText;
    private EditText translTranslationText;
    private String KEY_NATIVE = "NATIVE";
    private String KEY_TRANSLATED = "TRANSLATED";
    private AddPhraseViewModel addPhraseViewModel;


    /**
     * Looks at the edit texts and ensures everything okay to proceed
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phrase);

        Toolbar toolbar = this.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.add_phrase_title);

        addPhraseViewModel = ViewModelProviders.of(this).get(AddPhraseViewModel.class);
        nativeTranslationText = findViewById(R.id.nativePhraseEditText);
        translTranslationText = findViewById(R.id.translatedPhraseEditText);

        Button addButton = findViewById(R.id.add);
        addButton.setOnClickListener(this);

        // demonstration of concept, as TextInputLayout elements will preserve text data natively
        if (savedInstanceState != null) {
            String newNativeText = savedInstanceState.getString(KEY_NATIVE, "");
            String newTranslatedText = savedInstanceState.getString(KEY_TRANSLATED, "");

            if (newNativeText.length() > 0) {
                nativeTranslationText.setText(newNativeText);
            }

            if (newTranslatedText.length() > 0) {
                translTranslationText.setText(newTranslatedText);
            }
        }
    }


    /**
     * Saves the state in-case of screen rotation - proof of concept in this example, as
     * TextInputLayout preserves the text automatically
     *
     * @param savedInstanceState
     */
    public void onSaveInstanceState(Bundle savedInstanceState) {
        if (nativeTranslationText.length() > 0) {
            savedInstanceState.putString(KEY_NATIVE, nativeTranslationText.getText().toString());
        }
        if (translTranslationText.length() > 0) {
            savedInstanceState.putString(KEY_TRANSLATED, translTranslationText.getText().toString());
        }
        super.onSaveInstanceState(savedInstanceState);
    }


    /**
     * Catches the users request to go back
     *
     * @return boolean
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * Warns the user should they wish to cancel
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(getResources().getText(R.string.add_phrase_leave_title))
                .setMessage(getResources().getText(R.string.add_phrase_leave_message_body))
                .setNegativeButton(android.R.string.no, null) // Do nothing
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        AddPhraseActivity.super.onBackPressed();
                    }
                }).create().show();
    }


    /**
     * Catches the fab click event
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add) {
            insertPhrase();
        }
    }


    /**
     * Checks and records phrases if applicable
     */
    private void insertPhrase() {
        if (nativeTranslationText.getText().length() > 0 &&
                translTranslationText.getText().length() > 0) {

            Phrase newPhrase = new Phrase(nativeTranslationText.getText().toString(), translTranslationText.getText().toString(), new Date());
            addPhraseViewModel.insertPhrase(newPhrase);
            finish();
        } else {
            Toast.makeText(this, R.string.add_phrase_submit_toast, Toast.LENGTH_SHORT).show();
        }
    }
}
