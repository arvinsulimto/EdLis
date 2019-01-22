package id.rendesvouz.edlis.Victor.Topic.Listening;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

import id.rendesvouz.edlis.DatabaseAccess;
import id.rendesvouz.edlis.MainActivity;
import id.rendesvouz.edlis.R;
import id.rendesvouz.edlis.Victor.SelectMaterial;
import id.rendesvouz.edlis.Singelton;
import id.rendesvouz.edlis.Victor.DatabaseData;

import static id.rendesvouz.edlis.R.layout.fragment_materi_listening;

public class ListeningPage extends Fragment {

    TextView tvIsi;
    ImageButton btnVoice;

    public ListeningPage() {

    }

    public static final ListeningPage newInstance(int topicID, int pageNumber) {
        ListeningPage listeningPage = new ListeningPage();
        Bundle bundle = new Bundle();
        bundle.putInt("ID", topicID);
        bundle.putInt("PN", pageNumber);
        listeningPage.setArguments(bundle);
        return listeningPage;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(fragment_materi_listening, container, false);



        tvIsi = (TextView) view.findViewById(R.id.tvIsi);
        btnVoice = (ImageButton) view.findViewById(R.id.btnVoice);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());
        databaseAccess.open();

        int TopicID = getArguments().getInt("ID");
        int PageNumber = getArguments().getInt("PN");

        final DatabaseData d = databaseAccess.getData(TopicID, PageNumber);

        btnVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                try{
                    // mediaPlayer.setDataSource("http://audio.oxforddictionaries.com/en/mp3/coward_gb_1.mp3");
                    mediaPlayer.setDataSource(d.resources);
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

//        public void PlaySound(View ){
//            DatabaseAccess databaseAccess1 = DatabaseAccess.getInstance(getActivity().getApplicationContext());
//            databaseAccess1.open();
//
//            DatabaseData de = databaseAccess1.getData(TopicID, PageNumber);
//            MediaPlayer mediaPlayer = new MediaPlayer();
//            try {
//                mediaPlayer.setDataSource(de.resources);
//                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                    @Override
//                    public void onPrepared(MediaPlayer mp) {
//                        mp.start();
//                        databaseAccess.close();
//                    }
//                });
//            }
//
//            catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        tvIsi.setText(d.desc);

        databaseAccess.close();


        return view;
    }


}
