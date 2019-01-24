/*
 * Copyright (c) 2018. Created by Alexander Toop. Assignment for Aberystwyth University.
 */

package alt28.assignment.university.aberystwyth.languagelearner.ui.learn;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import alt28.assignment.university.aberystwyth.languagelearner.R;
import alt28.assignment.university.aberystwyth.languagelearner.model.Phrase;
import alt28.assignment.university.aberystwyth.languagelearner.model.views.GamesViewModel;
import alt28.assignment.university.aberystwyth.languagelearner.ui.games.MultipleChoiceActivity;
import alt28.assignment.university.aberystwyth.languagelearner.ui.games.TranslatePhraseActivity;


/**
 * Presents the user with ways to learn their recorded phrases
 */
public class LearnFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout multipleChoiceGame;
    private RelativeLayout translatePhraseGame;


    /**
     * Required empty public constructor
     */
    public LearnFragment() {
    }


    /**
     * Sets up ui and deals with user clicks to the listed games
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learn, container, false);

        setupToolbar();
        listenToGameClicks(view);

        return view;
    }


    /**
     * Deals with clicks on the game titles displayed
     *
     * @param view
     */
    private void listenToGameClicks(View view) {
        // getPhrases() called on each click as this prevents newly added phrases from being missed
        multipleChoiceGame = view.findViewById(R.id.multipleChoiceGame);
        multipleChoiceGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((getPhrases() != null) && (getPhrases().size() >= 3)) {
                    Intent multipleChoiceIntent = new Intent(getActivity(), MultipleChoiceActivity.class);
                    startActivity(multipleChoiceIntent);
                } else {
                    Toast.makeText(getContext(), R.string.game_not_enough_phrases, Toast.LENGTH_SHORT).show();
                }
            }
        });

        translatePhraseGame = view.findViewById(R.id.translatePhraseGame);
        translatePhraseGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((getPhrases() != null) && (getPhrases().size() >= 3)) {
                    Intent translatePhraseGameIntent = new Intent(getActivity(), TranslatePhraseActivity.class);
                    startActivity(translatePhraseGameIntent);
                } else {
                    Toast.makeText(getContext(), R.string.game_not_enough_phrases, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * Displays the users stored language names in the toolbar
     */
    private void setupToolbar() {
        SharedPreferences sharedPref;
        sharedPref = getContext().getSharedPreferences("LlPreferences", Context.MODE_PRIVATE);

        // substring use to prevent long language names obscuring toolbar
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        String nativeLanguage = sharedPref.getString("user_learn_language", "error");
        nativeLanguage = (nativeLanguage.length() < 10) ? nativeLanguage : (nativeLanguage.substring(0, 9) + "...");
        String translatedLanguage = sharedPref.getString("user_native_language", "error");
        translatedLanguage = (translatedLanguage.length() < 10) ? translatedLanguage : (translatedLanguage.substring(0, 9) + "...");
        toolbar.setTitle(nativeLanguage + " to " + translatedLanguage);
    }


    /**
     * Required for implementation
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
    }


    /**
     * Obtains and populates data stored regarding the users phrases
     */
    private List<Phrase> getPhrases() {
        GamesViewModel gamesViewModel = ViewModelProviders.of(this).get(GamesViewModel.class);
        return gamesViewModel.getPhrases();
    }
}
