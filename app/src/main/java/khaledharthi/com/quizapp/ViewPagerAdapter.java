package khaledharthi.com.quizapp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by khaled on 17/03/17.
 */

public class ViewPagerAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<Quiz> list;
    public ViewPagerAdapter (Context context, ArrayList<Quiz> list){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }


    public void resetQuiz () {
        for (Quiz e : list){
            e.checked_item = -1;
        }
        notifyDataSetChanged();


    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = inflater.inflate(R.layout.quiz_template, container, false);

        RadioButton rb1 = (RadioButton) view.findViewById(R.id.radio_one);
        RadioButton rb2 = (RadioButton) view.findViewById(R.id.radio_two);
        RadioButton rb3 = (RadioButton) view.findViewById(R.id.radio_three);
        RadioButton rb4 = (RadioButton) view.findViewById(R.id.radio_four);
        TextView questionTV = (TextView) view.findViewById(R.id.quiz_question);
        questionTV.setText(list.get(position).question);
        rb1.setText(list.get(position).choices[0]);
        rb2.setText(list.get(position).choices[1]);
        rb3.setText(list.get(position).choices[2]);
        rb4.setText(list.get(position).choices[3]);
        int checked_item = list.get(position).checked_item;
        switch (checked_item){
            case 0: rb1.setChecked(true);
                break;
            case 1: rb2.setChecked(true);
                break;
            case 2: rb3.setChecked(true);
                break;
            case 3: rb4.setChecked(true);
        }

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radio_one: list.get(position).checked_item = 0; break;
                    case R.id.radio_two: list.get(position).checked_item = 1; break;
                    case R.id.radio_three: list.get(position).checked_item = 2; break;
                    case R.id.radio_four: list.get(position).checked_item = 3;
                }
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }



}
