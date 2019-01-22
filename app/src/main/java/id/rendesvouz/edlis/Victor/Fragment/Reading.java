package id.rendesvouz.edlis.Victor.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import id.rendesvouz.edlis.R;
import id.rendesvouz.edlis.Victor.Exam.ExamMenuReading;
import id.rendesvouz.edlis.Victor.Topic.Reading.ReadingTopic;

public class Reading extends Fragment {

    ImageButton btn_R_topic;
    ImageButton btn_R_exam;

    public Reading() {
        //
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tampilan, container, false);

        btn_R_topic = (ImageButton) view.findViewById(R.id.btn_R_Topic);
        btn_R_exam = (ImageButton) view.findViewById(R.id.btn_R_Exam);

        btn_R_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), ReadingTopic.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btn_R_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity().getApplication(), ExamMenuReading.class);
                startActivity(intent2);
                getActivity().finish();
            }
        });


        return view;

    }

}