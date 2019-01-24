/*
 * Copyright (c) 2018. Created by Alexander Toop. Assignment for Aberystwyth University.
 */

package alt28.assignment.university.aberystwyth.languagelearner.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import alt28.assignment.university.aberystwyth.languagelearner.R;


/**
 * Allows for users to define the language of their choice
 */
public class LanguagePickerActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText nativeLanguageText;
    private EditText learnLanguageText;


    /**
     * Ensures that we have access to the edit texts and adds click listener
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_picker);

        nativeLanguageText = findViewById(R.id.nativeLanguageEditText);
        learnLanguageText = findViewById(R.id.translatedLanguageEditText);

        Button button = findViewById(R.id.saveLanguage);
        button.setOnClickListener(this);
    }


    /**
     * Deals with the ui click events
     *
     * @param v - View
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveLanguage:
                if (nativeLanguageText.getText().toString().length() > 0 &&
                        learnLanguageText.getText().toString().length() > 0) {

                    saveLanguagesToPrefs(nativeLanguageText.getText().toString(), learnLanguageText.getText().toString());
                    Intent loadMain = new Intent(this, MainActivity.class);
                    startActivity(loadMain);
                } else {
                    Toast.makeText(this, "Please enter your native language and language you want to learn", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    /**
     * Saves data for application use
     *
     * @param nativeLan
     * @param learnLan
     */
    private void saveLanguagesToPrefs(String nativeLan, String learnLan) {
        SharedPreferences sharedPref;
        SharedPreferences.Editor editor;

        sharedPref = getSharedPreferences("LlPreferences", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        editor.putString("user_native_language", nativeLan);
        editor.putString("user_learn_language", learnLan);
        editor.putBoolean("app_need_lang_def", false);
        editor.apply();
    }
}
