/*
 * Copyright (c) 2018. Created by Alexander Toop. Assignment for Aberystwyth University.
 */

package alt28.assignment.university.aberystwyth.languagelearner.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

import alt28.assignment.university.aberystwyth.languagelearner.model.util.DateTimeConverter;


/**
 * Phrase contains native and translated version of the body of text
 */
@Entity(tableName = "phrases")
@TypeConverters({DateTimeConverter.class})
public class Phrase {
    private String nativePhrase;
    private String translatedPhrase;

    // id will be used for the database to effectively list the phrases
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "date_added")
    private Date dateAdded;


    /**
     * Constructor, phrases will be created and used by app users. Date used for sorting.
     *
     * @param nativePhrase     String in users native language
     * @param translatedPhrase String in the translated language
     */
    public Phrase(String nativePhrase, String translatedPhrase, Date dateAdded) {
        this.nativePhrase = nativePhrase;
        this.translatedPhrase = translatedPhrase;
        this.dateAdded = dateAdded;
    }


    /**
     * Gets id
     *
     * @return int
     */
    public int getId() {
        return id;
    }


    /**
     * Sets id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets native phrase string
     *
     * @return String
     */
    public String getNativePhrase() {
        return nativePhrase;
    }


    /**
     * Sets native phrase string
     *
     * @param nativePhrase
     */
    public void setNativePhrase(String nativePhrase) {
        this.nativePhrase = nativePhrase;
    }


    /**
     * Returns the translated phrase string
     *
     * @return String
     */
    public String getTranslatedPhrase() {
        return translatedPhrase;
    }


    /**
     * Sets the translated phrase string
     *
     * @param translatedPhrase
     */
    public void setTranslatedPhrase(String translatedPhrase) {
        this.translatedPhrase = translatedPhrase;
    }


    /**
     * Sets the data added Date object
     *
     * @param dateAdded
     */
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }


    /**
     * Gets the date added Date object
     *
     * @return Date
     */
    public Date getDateAdded() {
        return dateAdded;
    }
}
