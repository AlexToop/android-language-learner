package alt28.assignment.university.aberystwyth.languagelearner.datasource;

import alt28.assignment.university.aberystwyth.languagelearner.model.PhraseDao;

/**
 * Simple interface for use with both persistent and in memory versions of the database
 */
public interface RoomDatabaseI {

    abstract PhraseDao getPhraseDao();

    abstract void closeDb();
}
