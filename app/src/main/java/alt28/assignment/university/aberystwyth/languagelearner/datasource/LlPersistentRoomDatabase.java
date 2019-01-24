/*
 * Copyright (c) 2018. Created by Alexander Toop. Assignment for Aberystwyth University.
 */

package alt28.assignment.university.aberystwyth.languagelearner.datasource;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import alt28.assignment.university.aberystwyth.languagelearner.model.Phrase;
import alt28.assignment.university.aberystwyth.languagelearner.model.PhraseDao;


/**
 * Provides access to the room database
 * Adapted from Chris Loftus's provided workshop material faaversion7 26/08/2018
 */
@Database(entities = {Phrase.class}, version = 1)
public abstract class LlPersistentRoomDatabase extends RoomDatabase implements RoomDatabaseI {
    private static LlPersistentRoomDatabase INSTANCE;


    /**
     * Getter method
     *
     * @return PhraseDao
     */
    public abstract PhraseDao getPhraseDao();


    /**
     * Closes database for memory management
     */
    @Override
    public void closeDb(){
        INSTANCE.close();
        INSTANCE = null;
    }


    /**
     * Gets room database
     *
     * @param context
     * @return LlPersistentRoomDatabase
     */
    public static LlPersistentRoomDatabase getDatabase(final Context context) {
        // singleton pattern used
        if (INSTANCE == null) {
            synchronized (LlPersistentRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LlPersistentRoomDatabase.class, "ll_database")
                            .allowMainThreadQueries().addCallback(roomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }


    /**
     * Method to create the database for the first time
     */
    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };


    /**
     * Asynchronous task to populate the database
     */
    public static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final PhraseDao phraseDao;

        PopulateDbAsync(LlPersistentRoomDatabase db) {
            phraseDao = db.getPhraseDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            // uncomment below code if you wish to initially populate the phrases with some examples
            // suggested to keep this commented until phrase individual deletion is added as a
            // feature
            /*
                Date currDate = new Date();
                List<Phrase> phraseList = new ArrayList<>();
                phraseList.add(new Phrase("Welcome to Language Learner!", "Witamy w Language Learner!", currDate));
                phraseList.add(new Phrase("Press the bottom right button to add new phrases", "Naciśnij prawy dolny przycisk, aby dodać nowe wyrażenia", currDate));
                phraseList.add(new Phrase("Click on phrases to review or remove them", "Kliknij frazy, aby je przejrzeć lub usunąć!", currDate));
                phraseList.add(new Phrase("Select the 'LEARN' tab to play learning games", "Wybierz zakładkę \"UCZ SIĘ\", aby grać w gry edukacyjne", currDate));
                phraseDao.insertMultiplePhrases(phraseList);
            */
            return null;
        }
    }
}
