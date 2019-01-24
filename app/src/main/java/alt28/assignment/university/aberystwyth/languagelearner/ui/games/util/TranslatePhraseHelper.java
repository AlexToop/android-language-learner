package alt28.assignment.university.aberystwyth.languagelearner.ui.games.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import alt28.assignment.university.aberystwyth.languagelearner.model.Phrase;


/**
 * Provides abstraction, ideally only logic related directly to ui should ideally take place inside
 * the activities themselves
 */
public class TranslatePhraseHelper {


    /**
     * Empty constructor
     */
    public TranslatePhraseHelper(){
    }


    /**
     * Given a set of phrases and returns them in a random order
     *
     * @return List Shuffled list of phrases
     */
    public List<Phrase> getShuffledPhrases(List<Phrase> phrases) {
        List<Phrase> shuffledPhrases = new ArrayList<>(phrases);
        Collections.shuffle(shuffledPhrases);
        return shuffledPhrases;
    }


    /**
     * Checks user has provided a string that is the same as the phrases translation
     *
     * @param phrase
     * @param answer
     * @return boolean
     */
    public boolean isAnswerCorrect(Phrase phrase, String answer){
        return phrase.getTranslatedPhrase().equals(answer);
    }
}
