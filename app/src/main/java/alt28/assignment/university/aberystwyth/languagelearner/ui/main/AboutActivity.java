package alt28.assignment.university.aberystwyth.languagelearner.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import alt28.assignment.university.aberystwyth.languagelearner.R;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = this.findViewById(R.id.about_toolbar);
        toolbar.setTitle(R.string.nav_about);

        Button button = findViewById(R.id.aboutDone);
        button.setOnClickListener(this);
    }


    /**
     *  UI Click events are controlled using this
     *
     * @param v - View
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.aboutDone) {
            finish();
        }
    }
}
