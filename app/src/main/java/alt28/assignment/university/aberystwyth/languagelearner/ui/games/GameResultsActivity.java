package alt28.assignment.university.aberystwyth.languagelearner.ui.games;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import alt28.assignment.university.aberystwyth.languagelearner.R;

/**
 * Displays the results of the calling game
 */
public class GameResultsActivity extends AppCompatActivity {
    private boolean isQ1Correct;
    private boolean isQ2Correct;
    private boolean isQ3Correct;


    /**
     * Grabs results, processes and presents them
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_results);

        populateResults();

        int noCorrect = 0;
        if (isQ1Correct){
            TextView textview = this.findViewById(R.id.game_stats_q1);
            textview.setText(R.string.game_translate_stats_question_q1_correct);
            noCorrect++;
        }
        if (isQ2Correct){
            TextView textview = this.findViewById(R.id.game_stats_q2);
            textview.setText(R.string.game_translate_stats_question_q2_correct);
            noCorrect++;
        }
        if (isQ3Correct){
            TextView textview = this.findViewById(R.id.game_stats_q3);
            textview.setText(R.string.game_translate_stats_question_q3_correct);
            noCorrect++;
        }
        TextView textview = this.findViewById(R.id.game_stats_score);
        textview.setText(noCorrect + "/3");

        Toolbar toolbar = this.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.game_stats_results_toolbar);

        Button exitBtn = this.findViewById(R.id.game_stats_exit_btn);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /**
     * Stores result of booleans from previous activity.
     */
    private void populateResults() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            isQ1Correct = extras.getBoolean("isQ1Correct");
            isQ2Correct = extras.getBoolean("isQ2Correct");
            isQ3Correct = extras.getBoolean("isQ3Correct");
        }
    }
}
