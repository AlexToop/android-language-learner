/*
 * Copyright (c) 2018. Created by Alexander Toop. Assignment for Aberystwyth University.
 */

package alt28.assignment.university.aberystwyth.languagelearner.model.views;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import alt28.assignment.university.aberystwyth.languagelearner.datasource.LlRepository;
import alt28.assignment.university.aberystwyth.languagelearner.model.Phrase;

/**
 * Simple interaction view model to insert new phrases into the repository
 */
public class AddPhraseViewModel extends AndroidViewModel {
    private LlRepository repository;


    /**
     * Constructor that makes repository accessible
     *
     * @param application - Gives context
     */
    public AddPhraseViewModel(@NonNull Application application) {
        super(application);
        repository = new LlRepository(application);
    }


    /**
     * Inserts provided phrase object into the repository
     *
     * @param phrase - Object containing details about the learning phrase
     */
    public void insertPhrase(Phrase phrase) {
        repository.insert(phrase);
    }
}
