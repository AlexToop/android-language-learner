/*
 * Copyright (c) 2018. Created by Alexander Toop. Assignment for Aberystwyth University.
 */

package alt28.assignment.university.aberystwyth.languagelearner.ui.games;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import alt28.assignment.university.aberystwyth.languagelearner.R;
import alt28.assignment.university.aberystwyth.languagelearner.model.views.GamesViewModel;
import alt28.assignment.university.aberystwyth.languagelearner.model.Phrase;
import alt28.assignment.university.aberystwyth.languagelearner.ui.games.util.MultipleChoiceHelper;


/**
 * Multiple choice game activity
 */
public class MultipleChoiceActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Phrase> phrases;
    private boolean areQuestionsMarkedComplete = false;
    private RadioButton selectedQ1Choice;
    private RadioButton selectedQ2Choice;
    private RadioButton selectedQ3Choice;
    private MultipleChoiceHelper multipleChoiceHelper;
    private List<MultipleChoiceQuestion> questions;


    /**
     * Functionality to populate layout to have questions and verify answers from users selection
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);

        Toolbar toolbar = this.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.game_multiple_title);

        populatePhrasesData();
        multipleChoiceHelper = new MultipleChoiceHelper(phrases);
        this.questions = multipleChoiceHelper.getRandomQuestions(3);
        populateUiWithQuestions();
        setupSubmissionButton();
    }


    /**
     * Populates the questions presented to the user in the UI.
     */
    private void populateUiWithQuestions() {
        // This approach was chosen in preference to a recycler view as the number of
        // questions is known and it simplifies returning the results of user onClick
        // actions and finding an items individual radio button selected
        for (int questionIndex = 0; questionIndex < 3; questionIndex++) {
            String displayQuestionTitleText = "";
            TextView textViewQuestionTitle;
            RadioButton radioButton1;
            RadioButton radioButton2;
            RadioButton radioButton3;

            if (questionIndex == 0) {
                displayQuestionTitleText += getResources().getString(R.string.generic_q1) + " ";
                textViewQuestionTitle = findViewById(R.id.translatedMcTextView_q1);
                radioButton1 = findViewById(R.id.radio_q1_option1);
                radioButton2 = findViewById(R.id.radio_q1_option2);
                radioButton3 = findViewById(R.id.radio_q1_option3);
            } else if (questionIndex == 1) {
                displayQuestionTitleText += getResources().getString(R.string.generic_q2) + " ";
                textViewQuestionTitle = findViewById(R.id.translatedMcTextView_q2);
                radioButton1 = findViewById(R.id.radio_q2_option1);
                radioButton2 = findViewById(R.id.radio_q2_option2);
                radioButton3 = findViewById(R.id.radio_q2_option3);
            } else {
                displayQuestionTitleText += getResources().getString(R.string.generic_q3) + " ";
                textViewQuestionTitle = findViewById(R.id.translatedMcTextView_q3);
                radioButton1 = findViewById(R.id.radio_q3_option1);
                radioButton2 = findViewById(R.id.radio_q3_option2);
                radioButton3 = findViewById(R.id.radio_q3_option3);
            }
            textViewQuestionTitle.setText(displayQuestionTitleText + questions.get(questionIndex).getTranslatedPhrase());
            radioButton1.setText(questions.get(questionIndex).getChoice1());
            radioButton2.setText(questions.get(questionIndex).getChoice2());
            radioButton3.setText(questions.get(questionIndex).getChoice3());
        }
    }


    /**
     * Populates the phrases stored in this activity using view model abstraction
     */
    private void populatePhrasesData() {
        GamesViewModel gamesViewModel = ViewModelProviders.of(this).get(GamesViewModel.class);
        this.phrases = gamesViewModel.getPhrases();
    }


    /**
     * Ensures user is finished and can now see their results
     */
    private void setupSubmissionButton() {
        Button submitBtn = this.findViewById(R.id.game_mc_submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkQuestionsAnswered();

                if (areQuestionsMarkedComplete) {
                    boolean isQ1Correct = multipleChoiceHelper.
                            isAnswerCorrect(questions.get(0), selectedQ1Choice.getText().toString());
                    boolean isQ2Correct = multipleChoiceHelper.
                            isAnswerCorrect(questions.get(1), selectedQ2Choice.getText().toString());
                    boolean isQ3Correct = multipleChoiceHelper.
                            isAnswerCorrect(questions.get(2), selectedQ3Choice.getText().toString());
                    Intent resultsIntent = new Intent(getBaseContext(), GameResultsActivity.class);
                    resultsIntent.putExtra(getResources().getString(R.string.game_q1_correct_intent_extra), isQ1Correct);
                    resultsIntent.putExtra(getResources().getString(R.string.game_q2_correct_intent_extra), isQ2Correct);
                    resultsIntent.putExtra(getResources().getString(R.string.game_q3_correct_intent_extra), isQ3Correct);
                    startActivity(resultsIntent);
                    finish();
                }
            }
        });
    }


    /**
     * Prompts the user to enter question answers if they have not done so already and sets local
     * variables so that locally it is known if and which questions have been answered
     *
     * @return
     */
    private void checkQuestionsAnswered() {
        RadioGroup radioQ1Group = findViewById(R.id.radio_q1_group_1);
        RadioGroup radioQ2Group = findViewById(R.id.radio_q2_group_1);
        RadioGroup radioQ3Group = findViewById(R.id.radio_q3_group_1);
        int q1SelectedId = radioQ1Group.getCheckedRadioButtonId();
        int q2SelectedId = radioQ2Group.getCheckedRadioButtonId();
        int q3SelectedId = radioQ3Group.getCheckedRadioButtonId();

        if (q1SelectedId == -1) {
            Toast.makeText(getBaseContext(), R.string.game_q1_toast_input_required, Toast.LENGTH_SHORT).show();
            return;
        }
        if (q2SelectedId == -1) {
            Toast.makeText(getBaseContext(), R.string.game_q2_toast_input_required, Toast.LENGTH_SHORT).show();
            return;
        }
        if (q3SelectedId == -1) {
            Toast.makeText(getBaseContext(), R.string.game_q3_toast_input_required, Toast.LENGTH_SHORT).show();
            return;
        }
        selectedQ1Choice = radioQ1Group.findViewById(q1SelectedId);
        selectedQ2Choice = radioQ2Group.findViewById(q2SelectedId);
        selectedQ3Choice = radioQ3Group.findViewById(q3SelectedId);
        areQuestionsMarkedComplete = true;
    }


    /**
     * Catches the users request to go back and displays warnings
     *
     * @return boolean
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    /**
     * Warns the user should they wish to go back answers will be deleted
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(getResources().getText(R.string.generic_game_leave_title))
                .setMessage(getResources().getText(R.string.generic_game_leave_body))
                .setNegativeButton(android.R.string.no, null) // Do nothing
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        MultipleChoiceActivity.super.onBackPressed();
                    }
                }).create().show();
    }


    @Override
    public void onClick(View v) {

    }
}
