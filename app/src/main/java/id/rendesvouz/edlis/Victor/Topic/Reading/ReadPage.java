package id.rendesvouz.edlis.Victor.Topic.Reading;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import id.rendesvouz.edlis.DatabaseAccess;
import id.rendesvouz.edlis.R;
import id.rendesvouz.edlis.Singelton;
import id.rendesvouz.edlis.Victor.DatabaseData;

public class ReadPage extends Fragment {

    TextView tvTitle;
    TextView tvIsi;


    public ReadPage() {

    }

    public static final ReadPage newInstance(int topicID, int pageNumber) {
        ReadPage readPage = new ReadPage();
        Bundle bundle = new Bundle();
        bundle.putInt("ID", topicID);
        bundle.putInt("PN", pageNumber);
        readPage.setArguments(bundle);
        return readPage;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_materi, container, false);

        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvIsi = (TextView) view.findViewById(R.id.tvIsi);

        tvIsi.setMovementMethod(new ScrollingMovementMethod());

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());
        databaseAccess.open();

        int TopicID = getArguments().getInt("ID");
        int PageNumber = getArguments().getInt("PN");

        DatabaseData d = databaseAccess.getData(TopicID, PageNumber);

        tvTitle.setText(d.judul);
        tvIsi.setText(d.desc);

        databaseAccess.close();

        return view;
    }


}
