/*
 * Copyright (c) 2018. Created by Alexander Toop. Assignment for Aberystwyth University.
 */

package alt28.assignment.university.aberystwyth.languagelearner.ui.games;

/**
 * Stores all information needed for a multiple choice question used in the multiple choice question
 * game
 */
public class MultipleChoiceQuestion  {
    private String nativePhrase;
    private String translatedPhrase;
    private String choice1;
    private String choice2;
    private String choice3;


    /**
     * Constructor
     *
     * @param nativePhrase
     * @param translatedPhrase
     * @param choice1
     * @param choice2
     * @param choice3
     */
    public MultipleChoiceQuestion(String nativePhrase, String translatedPhrase, String choice1, String choice2, String choice3) {
        this.nativePhrase = nativePhrase;
        this.translatedPhrase = translatedPhrase;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
    }


    /**
     * Getter method
     *
     * @return String
     */
    public String getNativePhrase() {
        return nativePhrase;
    }


    /**
     * Setter method
     *
     * @param nativePhrase
     */
    public void setNativePhrase(String nativePhrase) {
        this.nativePhrase = nativePhrase;
    }


    /**
     * Getter method
     *
     * @return String
     */
    public String getTranslatedPhrase() {
        return translatedPhrase;
    }


    /**
     * Setter method
     *
     * @param translatedPhrase
     */
    public void setTranslatedPhrase(String translatedPhrase) {
        this.translatedPhrase = translatedPhrase;
    }


    /**
     * Getter method
     *
     * @return String
     */
    public String getChoice1() {
        return choice1;
    }


    /**
     * Setter method
     *
     * @param choice1
     */
    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }


    /**
     * Getter method
     *
     * @return String
     */
    public String getChoice2() {
        return choice2;
    }


    /**
     * Setter method
     *
     * @param choice2
     */
    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }


    /**
     * Getter method
     *
     * @return String
     */
    public String getChoice3() {
        return choice3;
    }


    /**
     * Setter method
     *
     * @param choice3
     */
    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }
}
