/*
 * Copyright (c) 2018. Created by Alexander Toop. Assignment for Aberystwyth University.
 */

package alt28.assignment.university.aberystwyth.languagelearner.datasource;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import alt28.assignment.university.aberystwyth.languagelearner.Injection;
import alt28.assignment.university.aberystwyth.languagelearner.model.Phrase;
import alt28.assignment.university.aberystwyth.languagelearner.model.PhraseDao;

/**
 * Serves as a abstraction layer between the UI ViewModels and the RoomDatabase
 */
public class LlRepository {
    private PhraseDao phraseDao;
    private RoomDatabaseI db;


    /**
     * initialises repository
     *
     * @param context
     */
    public LlRepository(Context context) {
        db = Injection.getDatabase(context);
        phraseDao = db.getPhraseDao();
    }

    /**
     * Starts thread then uses dao
     *
     * @param phrase
     */
    public void insert(Phrase phrase) {
        new InsertAsyncTask(phraseDao).execute(phrase);
    }


    /**
     * Getter method for livedata
     *
     * @return LiveData list of phrases
     */
    public LiveData<List<Phrase>> getAllPhrases() {
        return phraseDao.getAllPhrases();
    }


    /**
     * Getter method for a list of phrases in dao
     *
     * @return list of phrases
     */
    public List<Phrase> getNonLivePhrases() {
        return phraseDao.getNonLivePhrases();
    }


    /**
     * Calls async task to delete all stored phrases
     */
    public void deleteAll() {
        new DeleteAsyncTask(phraseDao).execute();
    }


    /**
     * Asynchronous task insertion
     */
    private static class InsertAsyncTask extends AsyncTask<Phrase, Void, Void> {

        private PhraseDao mAsyncTaskDao;

        InsertAsyncTask(PhraseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Phrase... params) {
            mAsyncTaskDao.insertMultiplePhrases(params);
            return null;
        }
    }


    /**
     * Asynchronous task deletion
     */
    private static class DeleteAsyncTask extends AsyncTask<Phrase, Void, Void> {

        private PhraseDao mAsyncTaskDao;

        DeleteAsyncTask(PhraseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Phrase... params) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}
