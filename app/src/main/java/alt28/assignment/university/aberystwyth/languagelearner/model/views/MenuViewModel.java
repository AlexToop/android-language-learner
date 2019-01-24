/*
 * Copyright (c) 2018. Created by Alexander Toop. Assignment for Aberystwyth University.
 */

package alt28.assignment.university.aberystwyth.languagelearner.model.views;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import alt28.assignment.university.aberystwyth.languagelearner.datasource.LlRepository;
import alt28.assignment.university.aberystwyth.languagelearner.ui.phrases.PhraseRecyclerWithListAdapter;


/**
 * Allows for the menu to interact with the repository
 */
public class MenuViewModel extends AndroidViewModel {
    private LlRepository repository;


    /**
     * Makes repository accessible from class
     *
     * @param application
     */
    public MenuViewModel(@NonNull Application application) {
        super(application);
        repository = new LlRepository(application);
    }


    /**
     * Enables users to reset the phrases recorded and start the app effectively again
     */
    public void deleteAllPhrases() {
        repository.deleteAll();
    }

}
