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


/**
 * Generically allows for games to access the repository to deal with recorded phrases
 */
public class GamesViewModel extends AndroidViewModel {
    private LlRepository repository;


    /**
     * Locally initiates the repository
     *
     * @param application
     */
    public GamesViewModel(@NonNull Application application) {
        super(application);
        repository = new LlRepository(application);
    }


    /**
     * Gets phrases from the repository
     *
     * @return List of Phrases
     */
    public List<Phrase> getPhrases() {
        return repository.getNonLivePhrases();
    }
}
