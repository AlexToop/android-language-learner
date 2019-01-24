package alt28.assignment.university.aberystwyth.languagelearner.database_tests;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import alt28.assignment.university.aberystwyth.languagelearner.Injection;
import alt28.assignment.university.aberystwyth.languagelearner.database_tests.util.LiveDataTestUtil;
import alt28.assignment.university.aberystwyth.languagelearner.generic_util.TestUtil;
import alt28.assignment.university.aberystwyth.languagelearner.datasource.RoomDatabaseI;
import alt28.assignment.university.aberystwyth.languagelearner.model.Phrase;
import alt28.assignment.university.aberystwyth.languagelearner.model.PhraseDao;

@RunWith(AndroidJUnit4.class)
public class AddPhraseTest {
    private TestUtil testUtil = new TestUtil();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    public PhraseDao phraseDao;
    private RoomDatabaseI databaseI;

    @Before
    public void createDb(){
        Context context = InstrumentationRegistry.getTargetContext();

        databaseI = Injection.getDatabase(context);
        phraseDao = databaseI.getPhraseDao();
    }


    @After
    public void closeDb() throws IOException {
        databaseI.closeDb();
    }


    @Test
    public void insert_phrase_assert_phrase_added() throws Exception {
        phraseDao.insertSinglePhrase(testUtil.getExamplePhrase(""));
        LiveData<List<Phrase>> databasePhrases = phraseDao.getAllPhrases();
        Assert.assertEquals(1, LiveDataTestUtil.getValue(databasePhrases).size());
    }


    @Test
    public void insert_multiple_phrases_assert_phrases_added() throws Exception {
        phraseDao.insertMultiplePhrases(testUtil.getExamplePhraseList(2));
        LiveData<List<Phrase>> databasePhrases = phraseDao.getAllPhrases();
        Assert.assertEquals(2, LiveDataTestUtil.getValue(databasePhrases).size());
    }

    @Test
    public void delete_all_phrases_assert_deleted() throws Exception {
        phraseDao.insertMultiplePhrases(testUtil.getExamplePhraseList(2));
        phraseDao.deleteAll();
        LiveData<List<Phrase>> databasePhrases = phraseDao.getAllPhrases();
        Assert.assertEquals(0, LiveDataTestUtil.getValue(databasePhrases).size());
    }

}
