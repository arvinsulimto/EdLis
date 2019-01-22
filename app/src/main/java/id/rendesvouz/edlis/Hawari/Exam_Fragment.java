package id.rendesvouz.edlis.Hawari;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import id.rendesvouz.edlis.R;

public class Exam_Fragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private String Question;

    public Exam_Fragment() {
    }

    public static Exam_Fragment newInstance(int sectionNumber) {
        Exam_Fragment fragment = new Exam_Fragment();
        Bundle args = new Bundle();
        args.putInt("ID",sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final Singelton2 data = Singelton2.getInstance();
        Bundle args = getArguments();
        final int FragmentID = args.getInt("ID") - 1;
        Question = data.getQuestion(FragmentID);
        TextView txt_soal = (TextView) rootView.findViewById(R.id.txt_soal);
        txt_soal.setText(Question);
        RadioGroup rg_answers = (RadioGroup) rootView.findViewById(R.id.rbg_jawaban);
        rg_answers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch(checkedId) {
                    case R.id.rb_a:
                        data.setAnswer(FragmentID,1);
                        break;
                    case R.id.rb_b:
                        data.setAnswer(FragmentID,2);
                        break;
                    case R.id.rb_c:
                        data.setAnswer(FragmentID,3);
                        break;
                    case R.id.rb_d:
                        data.setAnswer(FragmentID,4);
                        break;
                    case R.id.rb_e:
                        data.setAnswer(FragmentID,5);
                        break;
                }
            }
        });

        return rootView;
    }


}
