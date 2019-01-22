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
import id.rendesvouz.edlis.Victor.Exam.ExamMenuListening;
import id.rendesvouz.edlis.Victor.Exam.ExamMenuWriting;
import id.rendesvouz.edlis.Victor.Topic.Listening.ListeningTopic;

public class Listening extends Fragment {

    ImageButton btn_L_topic;
    ImageButton btn_L_exam;

    public Listening() {
        //
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listening, container, false);

        btn_L_topic = (ImageButton) view.findViewById(R.id.btn_L_Topic);
        btn_L_exam = (ImageButton) view.findViewById(R.id.btn_L_Exam);

        btn_L_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), ListeningTopic.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btn_L_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity().getApplication(), ExamMenuListening.class);
                startActivity(intent2);
                getActivity().finish();
            }
        });

        return view;
    }


}