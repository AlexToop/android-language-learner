package alt28.assignment.university.aberystwyth.languagelearner.ui.games.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import alt28.assignment.university.aberystwyth.languagelearner.model.Phrase;
import alt28.assignment.university.aberystwyth.languagelearner.ui.games.MultipleChoiceQuestion;

/**
 * Provides abstraction from the ui implementation with logic required for the multiple choice game
 * This also makes the content easier to write tests for
 */
public class MultipleChoiceHelper {
    private int totalNoPhrases;
    private List<Phrase> phrases;


    /**
     * Constructor that stores phrases for use
     *
     * @param phrases
     */
    public MultipleChoiceHelper(List<Phrase> phrases){
        if (phrases == null) {
            this.totalNoPhrases = 0;
        } else {
            this.totalNoPhrases = phrases.size();
        }
        this.phrases = phrases;
    }


    /**
     * Gets populated MultipleChoiceQuestion objects of parameter specified size
     *
     * @param numberOfQuestions
     * @return List of MultipleChoiceQuestion
     */
    public List<MultipleChoiceQuestion> getRandomQuestions(int numberOfQuestions) {
        List<MultipleChoiceQuestion> questions = new ArrayList<>();

        if (totalNoPhrases >= numberOfQuestions) {
            List<Integer> donePhraseIndexes = new ArrayList<>();
            Random random = new Random();

            for (int i = 0; i < numberOfQuestions; i++) {
                int chosenQuestionPhraseIndex = random.nextInt(totalNoPhrases);

                while (donePhraseIndexes.contains(chosenQuestionPhraseIndex)) {
                    chosenQuestionPhraseIndex = random.nextInt(totalNoPhrases);
                }

                MultipleChoiceQuestion multipleChoiceQuestion =
                        getMultipleChoiceQuestionFromPhraseIndex(chosenQuestionPhraseIndex);
                donePhraseIndexes.add(chosenQuestionPhraseIndex);
                questions.add(multipleChoiceQuestion);
            }
        }
        return questions;
    }


    /**
     * Determines if the string answered by the user matches the expected results of the question
     *
     * @param question
     * @param answer
     * @return boolean
     */
    public boolean isAnswerCorrect(MultipleChoiceQuestion question, String answer){
        return question.getNativePhrase().equals(answer);
    }


    /**
     * Makes a MultipleChoiceQuestion while ensuring that the user is given a correct choice and 2
     * other choices from different stored phrases
     *
     * @param chosenQuestionPhraseIndex
     * @return MultipleChoiceQuestion
     */
    private MultipleChoiceQuestion getMultipleChoiceQuestionFromPhraseIndex(int chosenQuestionPhraseIndex) {
        Phrase chosenPhrase = phrases.get(chosenQuestionPhraseIndex);
        Random random = new Random();

        List<Integer> choices = new ArrayList<>();
        choices.add(chosenQuestionPhraseIndex);

        int choice1No = random.nextInt(totalNoPhrases);
        while (choice1No == chosenQuestionPhraseIndex) {
            choice1No = random.nextInt(totalNoPhrases);
        }
        choices.add(choice1No);

        int choice2No = random.nextInt(totalNoPhrases);
        while (choice2No == chosenQuestionPhraseIndex || choice2No == choice1No) {
            choice2No = random.nextInt(totalNoPhrases);
        }
        choices.add(choice2No);

        Collections.shuffle(choices);

        return new MultipleChoiceQuestion(chosenPhrase.getNativePhrase(),
                chosenPhrase.getTranslatedPhrase(),
                phrases.get(choices.get(0)).getNativePhrase(),
                phrases.get(choices.get(1)).getNativePhrase(),
                phrases.get(choices.get(2)).getNativePhrase());
    }
}
