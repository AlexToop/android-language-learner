package alt28.assignment.university.aberystwyth.languagelearner;

import android.content.Context;

import alt28.assignment.university.aberystwyth.languagelearner.datasource.LlInMemoryRoomDatabase;
import alt28.assignment.university.aberystwyth.languagelearner.datasource.LlPersistentRoomDatabase;
import alt28.assignment.university.aberystwyth.languagelearner.datasource.RoomDatabaseI;

public class Injection {
    public static RoomDatabaseI getDatabase(Context context) {
        return LlPersistentRoomDatabase.getDatabase(context);
    }

}
