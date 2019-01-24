package alt28.assignment.university.aberystwyth.languagelearner.datasource;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import alt28.assignment.university.aberystwyth.languagelearner.model.Phrase;
import alt28.assignment.university.aberystwyth.languagelearner.model.PhraseDao;

/**
 * Provides in-memory version of access to the database, intended for use with testing.
 * Adapted from Chris Loftus's provided workshop material faaversion7 26/08/2018
 */
@Database(entities = {Phrase.class}, version = 1)
public abstract class LlInMemoryRoomDatabase extends RoomDatabase implements RoomDatabaseI {
    private static LlInMemoryRoomDatabase INSTANCE;


    /**
     * Getter method
     *
     * @return PhraseDao
     */
    @Override
    public abstract PhraseDao getPhraseDao();


    /**
     * Important to call in tests after getting a database object
     */
    @Override
    public void closeDb() {
        INSTANCE.close();
        INSTANCE = null;
    }


    /**
     * @param context
     * @return LlInMemoryRoomDatabase
     */
    public static LlInMemoryRoomDatabase getDatabase(final Context context) {
        // singleton pattern use
        if (INSTANCE == null) {
            synchronized (LlInMemoryRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
                            LlInMemoryRoomDatabase.class).allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
