package alt28.assignment.university.aberystwyth.languagelearner.game_tests;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import alt28.assignment.university.aberystwyth.languagelearner.generic_util.TestUtil;
import alt28.assignment.university.aberystwyth.languagelearner.model.Phrase;
import alt28.assignment.university.aberystwyth.languagelearner.ui.games.MultipleChoiceQuestion;
import alt28.assignment.university.aberystwyth.languagelearner.ui.games.util.MultipleChoiceHelper;

@RunWith(AndroidJUnit4.class)
public class MultipleChoiceTest {
    private TestUtil testUtil = new TestUtil();
    private List<Phrase> phrases;
    MultipleChoiceHelper multipleChoiceHelper;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setup() {
        phrases = testUtil.getExamplePhraseList(5);
        multipleChoiceHelper = new MultipleChoiceHelper(phrases);
    }

    @Test
    public void assert_correct_when_correct_answer_provided() {
        MultipleChoiceQuestion multipleChoiceQuestion = testUtil.getExampleMultipleChoiceQuestion();
        String answerGiven = multipleChoiceQuestion.getNativePhrase();

        Assert.assertTrue(multipleChoiceHelper.isAnswerCorrect(multipleChoiceQuestion, answerGiven));
    }

    @Test
    public void assert_correct_when_incorrect_answer_provided() {
        MultipleChoiceQuestion multipleChoiceQuestion = testUtil.getExampleMultipleChoiceQuestion();
        String answerGiven = "incorrect_example_answer_provided";

        Assert.assertFalse(multipleChoiceHelper.isAnswerCorrect(multipleChoiceQuestion, answerGiven));
    }

    @Test
    public void assert_correct_when_null_answer_provided() {
        MultipleChoiceQuestion multipleChoiceQuestion = testUtil.getExampleMultipleChoiceQuestion();
        String answerGiven = null;

        Assert.assertFalse(multipleChoiceHelper.isAnswerCorrect(multipleChoiceQuestion, answerGiven));
    }

    @Test
    public void assert_random_question_picked_phrases_are_different() {
        List<MultipleChoiceQuestion> questions = multipleChoiceHelper.getRandomQuestions(2);

        Assert.assertNotEquals(questions.get(0).getTranslatedPhrase(), questions.get(1).getTranslatedPhrase());
        Assert.assertNotEquals(questions.get(0).getNativePhrase(), questions.get(1).getNativePhrase());
    }

    @Test
    public void assert_random_questions_have_translation_as_choice() {
        List<MultipleChoiceQuestion> questions = multipleChoiceHelper.getRandomQuestions(2);

        String answer = questions.get(0).getNativePhrase();
        String choice1 = questions.get(0).getChoice1();
        String choice2 = questions.get(0).getChoice2();
        String choice3 = questions.get(0).getChoice3();

        Assert.assertTrue(answer.equals(choice1) || answer.equals(choice2) || answer.equals(choice3));
    }
}
