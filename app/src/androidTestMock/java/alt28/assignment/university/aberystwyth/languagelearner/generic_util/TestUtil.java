package alt28.assignment.university.aberystwyth.languagelearner.generic_util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import alt28.assignment.university.aberystwyth.languagelearner.model.Phrase;
import alt28.assignment.university.aberystwyth.languagelearner.ui.games.MultipleChoiceQuestion;

public class TestUtil {

    /**
     * Ensures commonly used objects can be easily created for testing only purposes
     */
    public TestUtil() {
    }


    /**
     * Makes a phrase using the parameter so that values can be differentiated if desired
     *
     * @param uniqueAddOn
     * @return Phrase
     */
    public Phrase getExamplePhrase(String uniqueAddOn) {
        return new Phrase("Hello" + uniqueAddOn,
                "Bonjour" + uniqueAddOn,
                new Date());
    }


    /**
     * Returns a list of different phrases of a size specified in method calling
     *
     * @param size
     * @return List of phrases
     */
    public List<Phrase> getExamplePhraseList(int size) {
        List<Phrase> phrases = new ArrayList<>();
        for (int index = 0; size > index; index++) {
            String uniqueAddOn = "i" + index;
            phrases.add(getExamplePhrase(uniqueAddOn));
        }
        return phrases;
    }


    /**
     * Provides an example MultipleChoiceQuestion with parameters pre-populated
     *
     * @return MultipleChoiceQuestion
     */
    public MultipleChoiceQuestion getExampleMultipleChoiceQuestion() {
        return new MultipleChoiceQuestion("native", "translated",
                "incorrect", "correct", "incorrect");
    }
}
