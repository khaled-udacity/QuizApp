package khaledharthi.com.quizapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    ViewPagerAdapter adapter;
    ArrayList<Quiz> questions_list = new ArrayList<>();
    int current_page;

    @BindView(R.id.viewPager)
    MyViewPager pager;
    @BindView(R.id.prevBtn)
    TextView prevBtn;
    @BindView(R.id.nextBtn)
    TextView nextBtn;
    @BindView(R.id.result_layout)
    View resultLayout;
    @BindView(R.id.scoreTV)
    TextView scoreTV;
    int myScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_layout);
        ButterKnife.bind(this);
        ButterKnife.bind(resultLayout);

        initQuestions();
        adapter = new ViewPagerAdapter(getApplicationContext(), questions_list);
        pager.setAdapter(adapter);



    }

    private void calculateResult (){
        float score = 0;
        ArrayList<Quiz> list = adapter.list;
        for (Quiz e : list){
            if (e.checked_item == e.correct){
                score++;
            }
        }
        myScore = (int) (score / list.size() * 100.0);
    }

    private void showResult(){
        nextBtn.setVisibility(View.GONE);
        prevBtn.setVisibility(View.GONE);
        resultLayout.setVisibility(View.VISIBLE);
        calculateResult();
        scoreTV.setText("%"+myScore);

        Toast.makeText(getApplicationContext(), getResources().getString(R.string.your_score) + ": %" + myScore, Toast.LENGTH_SHORT).show();

    }

    public void showNext (View view){
        if (current_page == questions_list.size()-1){
            pager.setVisibility(View.GONE);
            showResult();
        } else {
            pager.setCurrentItem(++current_page, true);
            prevBtn.setEnabled(true);
        }

        if (current_page == questions_list.size()-1){
            nextBtn.setText(getResources().getString(R.string.finish));
        }
    }

    public void showPrev (View v){
        if (current_page == 1) {
            v.setEnabled(false);
        }

        nextBtn.setText(getResources().getString(R.string.next));
        pager.setCurrentItem(--current_page, true);

    }

    public void doItAgain(View v){
        adapter.resetQuiz();

        pager.setVisibility(View.VISIBLE);
        resultLayout.setVisibility(View.GONE);
        prevBtn.setVisibility(View.VISIBLE);
        nextBtn.setVisibility(View.VISIBLE);

        nextBtn.setText(getResources().getString(R.string.next));

        myScore = 0;
        current_page = 0;
        pager.setCurrentItem(current_page);

        prevBtn.setEnabled(false);
        scoreTV.setText("");
    }

    public void shareResult (View v){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_message, myScore));
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share Score!"));
    }

    private void initQuestions (){
        String[] q1_choices = {"Khaled", "People Behind Android", "Me", "Aliens"};
        questions_list.add(new Quiz("Who's the best Android Developer?", q1_choices, 3));

        String[] q2_choices = {"Aliens", "God", "Light", "idk"};
        questions_list.add(new Quiz("What's faster than light?", q2_choices, 2));

        String[] q3_choices = {"no.3", "no.3", "no.3", "no.3"};
        questions_list.add(new Quiz("The correct answer is no.3", q3_choices, 2));


    }
}
