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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import alt28.assignment.university.aberystwyth.languagelearner.R;
import alt28.assignment.university.aberystwyth.languagelearner.model.views.GamesViewModel;
import alt28.assignment.university.aberystwyth.languagelearner.model.Phrase;
import alt28.assignment.university.aberystwyth.languagelearner.ui.games.util.TranslatePhraseHelper;


/**
 * Tests users input against phrases recorded.
 */
public class TranslatePhraseActivity extends AppCompatActivity {
    private List<Phrase> phrases;
    private EditText q1Answer;
    private EditText q2Answer;
    private EditText q3Answer;
    private List<Phrase> shuffledPhrases;
    private boolean areQuestionsMarkedComplete = false;
    private TranslatePhraseHelper translatePhraseHelper = new TranslatePhraseHelper();


    /**
     * Presents the user with the relevant questions
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_phrase);

        Toolbar toolbar = this.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.game_translate_title);

        q1Answer = this.findViewById(R.id.q1EditText);
        q2Answer = this.findViewById(R.id.q2EditText);
        q3Answer = this.findViewById(R.id.q3EditText);

        populatePhrasesData();
        shuffledPhrases = translatePhraseHelper.getShuffledPhrases(phrases);

        setQuestionTitles();
        setupSubmissionButton();
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
                .setTitle(getResources().getText(R.string.generic_game_leave_title))
                .setMessage(getResources().getText(R.string.generic_game_leave_body))
                .setNegativeButton(android.R.string.no, null) // Do nothing
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        TranslatePhraseActivity.super.onBackPressed();
                    }
                }).create().show();
    }


    /**
     * Obtains and populates data stored regarding the users phrases
     */
    private void populatePhrasesData() {
        GamesViewModel gamesViewModel = ViewModelProviders.of(this).get(GamesViewModel.class);
        this.phrases = gamesViewModel.getPhrases();
    }


    /**
     * Presents the correct questions to the user
     */
    private void setQuestionTitles() {
        TextView q1Title = this.findViewById(R.id.translateQ1);
        q1Title.setText(getResources().getString(R.string.generic_q1) + " " + shuffledPhrases.get(0).getNativePhrase());
        TextView q2Title = this.findViewById(R.id.translateQ2);
        q2Title.setText(getResources().getString(R.string.generic_q2) + " " + shuffledPhrases.get(1).getNativePhrase());
        TextView q3Title = this.findViewById(R.id.translateQ3);
        q3Title.setText(getResources().getString(R.string.generic_q3) + " " + shuffledPhrases.get(2).getNativePhrase());
    }


    /**
     * Ensures user can finish the game and see their results
     */
    private void setupSubmissionButton() {
        Button submitBtn = this.findViewById(R.id.game_translate_submit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkQuestionsAnswered();

                if (areQuestionsMarkedComplete) {
                    boolean isQ1Correct = translatePhraseHelper.
                            isAnswerCorrect(shuffledPhrases.get(0), q1Answer.getText().toString());
                    boolean isQ2Correct = translatePhraseHelper.
                            isAnswerCorrect(shuffledPhrases.get(1), q2Answer.getText().toString());
                    boolean isQ3Correct = translatePhraseHelper.
                            isAnswerCorrect(shuffledPhrases.get(2), q3Answer.getText().toString());
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
     * variable so rest of activity is aware
     */
    private void checkQuestionsAnswered() {
        if ("".equals(q1Answer.getText().toString())) {
            Toast.makeText(getBaseContext(), R.string.game_q1_toast_input_required, Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(q2Answer.getText().toString())) {
            Toast.makeText(getBaseContext(), R.string.game_q2_toast_input_required, Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(q3Answer.getText().toString())) {
            Toast.makeText(getBaseContext(), R.string.game_q3_toast_input_required, Toast.LENGTH_SHORT).show();
            return;
        }
        areQuestionsMarkedComplete = true;
    }
}
