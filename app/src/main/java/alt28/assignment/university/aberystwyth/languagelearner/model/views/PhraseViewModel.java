/*
 * Copyright (c) 2018. Created by Alexander Toop. Assignment for Aberystwyth University.
 */

package alt28.assignment.university.aberystwyth.languagelearner.model.views;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import alt28.assignment.university.aberystwyth.languagelearner.datasource.LlRepository;
import alt28.assignment.university.aberystwyth.languagelearner.model.Phrase;
import alt28.assignment.university.aberystwyth.languagelearner.ui.phrases.PhraseRecyclerWithListAdapter;


/**
 * Interacts with the repository for database as an abstraction
 */
public class PhraseViewModel extends AndroidViewModel {
    private LlRepository repository;
    private LiveData<List<Phrase>> phraseList;
    private PhraseRecyclerWithListAdapter phraseRecyclerWithListAdapter;


    /**
     * Allows for the phrase list to interact with the repository through a layer of abstraction
     *
     * @param application
     */
    public PhraseViewModel(@NonNull Application application) {
        super(application);
        repository = new LlRepository(application);
        phraseList = repository.getAllPhrases();
    }


    /**
     * Returns the live list of phrases in the repository
     *
     * @return List of Phrases - obtained from repository
     */
    public LiveData<List<Phrase>> getPhrases() {
        return phraseList;
    }

    /**
     * Sets PhraseRecyclerWithListAdapter
     *
     * @param phraseRecyclerWithListAdapter
     */
    public void setAdapter(PhraseRecyclerWithListAdapter phraseRecyclerWithListAdapter) {
        this.phraseRecyclerWithListAdapter = phraseRecyclerWithListAdapter;
    }


    /**
     * Gets PhraseRecyclerWithListAdapter
     *
     * @return PhraseRecyclerWithListAdapter
     */
    public PhraseRecyclerWithListAdapter getAdapter() {
        return phraseRecyclerWithListAdapter;
    }
}
