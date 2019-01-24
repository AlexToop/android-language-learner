/*
 * Copyright (c) 2018. Created by Alexander Toop. Assignment for Aberystwyth University.
 */

package alt28.assignment.university.aberystwyth.languagelearner.ui.phrases;


import android.app.AlertDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.util.StringUtil;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import alt28.assignment.university.aberystwyth.languagelearner.R;
import alt28.assignment.university.aberystwyth.languagelearner.model.Phrase;
import alt28.assignment.university.aberystwyth.languagelearner.model.views.PhraseViewModel;


/**
 * Shows the recorded phrases to the user
 */
public class PhrasesFragment extends Fragment implements View.OnClickListener {
    private PhraseRecyclerWithListAdapter phraseRecyclerWithListAdapter;
    private FloatingActionButton floatingActionButton;
    private PhraseViewModel phraseViewModel;


    /**
     * Empty constructor
     */
    public PhrasesFragment() {
    }


    /**
     * Initialises phrases fragment
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_phrases, container, false);

        setupPhrases(view);
        setupFab();
        setupToolbar();
        return view;
    }


    /**
     * required for implementation of View.OnClickListener
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

    }


    /**
     * Interacts with the stored phrases and the recycler view
     *
     * @param view
     */
    private void setupPhrases(View view){
        phraseViewModel = ViewModelProviders.of(this).get(PhraseViewModel.class);
        phraseRecyclerWithListAdapter = phraseViewModel.getAdapter();

        if (phraseRecyclerWithListAdapter == null) {
            phraseRecyclerWithListAdapter = new PhraseRecyclerWithListAdapter(getContext());
            phraseViewModel.setAdapter(phraseRecyclerWithListAdapter);
        }

        RecyclerView listPhrases = view.findViewById(R.id.phrase_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);

        listPhrases.setLayoutManager(gridLayoutManager);
        listPhrases.setAdapter(phraseRecyclerWithListAdapter);
        phraseRecyclerWithListAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView nativeView = v.findViewById(R.id.nativeTextView);
                TextView translatedView = v.findViewById(R.id.translatedTextView);
                new AlertDialog.Builder(getContext())
                        .setTitle(R.string.phrases_expansion_title)
                        .setMessage("Translated: " + translatedView.getText()
                                + " \n\nNative: " + nativeView.getText())
                        .setPositiveButton(R.string.generic_okay, null).create().show();
            }
        });


        LiveData<List<Phrase>> phraseList = searchForPhrases();
        phraseList.observe(this, new Observer<List<Phrase>>() {
            @Override
            public void onChanged(@Nullable List<Phrase> phrases) {
                phraseRecyclerWithListAdapter.changeDataSet(phrases);
            }
        });
    }


    /**
     * Creates a listener on the fab for onClick events
     */
    private void setupFab(){
        floatingActionButton = getActivity().findViewById(R.id.fab_add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newPhraseIntent = new Intent(getActivity(), AddPhraseActivity.class);
                startActivity(newPhraseIntent);
            }
        });
    }


    /**
     * Gets names of languages used and presents them in the toolbar text
     */
    private void setupToolbar(){
        SharedPreferences sharedPref;
        sharedPref = getContext().getSharedPreferences("LlPreferences", Context.MODE_PRIVATE);

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        String nativeLanguage = sharedPref.getString("user_learn_language", "error");
        nativeLanguage = (nativeLanguage.length() < 11) ? nativeLanguage : (nativeLanguage.substring(0, 10) + "...");
        String translatedLanguage = sharedPref.getString("user_native_language", "error");
        translatedLanguage = (translatedLanguage.length() < 11) ? translatedLanguage : (translatedLanguage.substring(0, 10) + "...");
        toolbar.setTitle(nativeLanguage + " to " + translatedLanguage);
    }


    /**
     * Uses view model abstraction to obtain phrases from repository
     *
     * @return live data list of phrases
     */
    private LiveData<List<Phrase>> searchForPhrases() {
        return phraseViewModel.getPhrases();
    }
}
