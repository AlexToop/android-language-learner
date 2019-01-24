/*
 * Copyright (c) 2018. Created by Alexander Toop. Assignment for Aberystwyth University.
 */

package alt28.assignment.university.aberystwyth.languagelearner.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import java.util.List;

import alt28.assignment.university.aberystwyth.languagelearner.model.util.DateTimeConverter;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


/**
 * Phrase 'data access object' that interacts with the RoomDatabase as an intermediary
 */
@Dao
@TypeConverters({DateTimeConverter.class})
public interface PhraseDao {

    @Insert(onConflict = IGNORE)
    void insertSinglePhrase(Phrase phrase);


    // useful for testing purposes
    @Insert
    void insertMultiplePhrases(Phrase[] phraseList);


    @Insert
    void insertMultiplePhrases(List<Phrase> PhraseList);


    // possible future feature
    @Update(onConflict = REPLACE)
    void updatePhrase(Phrase phrase);


    // possible future feature
    @Delete
    void deletePhrase(Phrase phrase);


    @Query("DELETE FROM phrases")
    void deleteAll();


    @Query("SELECT * FROM phrases WHERE id = :id")
    Phrase fetchPhraseByPhraseId(int id);


    @Query("SELECT * FROM phrases")
    LiveData<List<Phrase>> getAllPhrases();


    @Query("SELECT * FROM phrases")
    List<Phrase> getNonLivePhrases();

}
