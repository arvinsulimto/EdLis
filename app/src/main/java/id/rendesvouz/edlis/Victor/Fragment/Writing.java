package id.rendesvouz.edlis.Victor.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import id.rendesvouz.edlis.R;
import id.rendesvouz.edlis.Victor.Exam.ExamMenuReading;
import id.rendesvouz.edlis.Victor.Exam.ExamMenuWriting;
import id.rendesvouz.edlis.Victor.Topic.Writing.WritingTopic;

public class Writing extends Fragment {

    ImageButton btn_W_topic;
    ImageButton btn_W_exam;



    public Writing() {
        //
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_writing, container, false);

        btn_W_topic = (ImageButton) view.findViewById(R.id.btn_W_Topic);
        btn_W_exam = (ImageButton) view.findViewById(R.id.btn_W_Exam);

        btn_W_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), WritingTopic.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btn_W_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity().getApplication(), ExamMenuWriting.class);
                startActivity(intent2);
                getActivity().finish();
            }
        });

        return view;
    }

}