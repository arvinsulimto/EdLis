package id.rendesvouz.edlis.Hawari;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;

import id.rendesvouz.edlis.DatabaseAccess;
import id.rendesvouz.edlis.R;

public class ListeningExam_Fragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private String Question;
    private String Sources;
    ImageButton imgBtn_playVoice;

    public ListeningExam_Fragment() {
    }

    public static ListeningExam_Fragment newInstance(int sectionNumber) {
        ListeningExam_Fragment fragments = new ListeningExam_Fragment();
        Bundle args = new Bundle();
        args.putInt("ID",sectionNumber);
        fragments.setArguments(args);
        return fragments;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_listening, container, false);
        final Singelton2 data = Singelton2.getInstance();
        Bundle args = getArguments();
        final int FragmentID = args.getInt("ID") - 1;
        Question = data.getQuestion(FragmentID);
        TextView txt_soal = (TextView) rootView.findViewById(R.id.txt_soal);
        txt_soal.setText(Question);

        Sources = data.getSources(FragmentID);

        imgBtn_playVoice = rootView.findViewById(R.id.imgBtn_playVoice);
        imgBtn_playVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                try{
                    // mediaPlayer.setDataSource("http://audio.oxforddictionaries.com/en/mp3/coward_gb_1.mp3");
                    mediaPlayer.setDataSource(Sources);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                    mediaPlayer.prepareAsync();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

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
