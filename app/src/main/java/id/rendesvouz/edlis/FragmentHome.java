package id.rendesvouz.edlis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import id.rendesvouz.edlis.Victor.SelectMaterial;

public class FragmentHome extends Fragment {
    View view;
    String Username;
    String Email;
    ImageButton imgbtn_courseMaterial;
    public FragmentHome() {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment,container,false);

        imgbtn_courseMaterial = (ImageButton) view.findViewById(R.id.imgbtn_courseMaterial);
        imgbtn_courseMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singelton Passing = Singelton.getInstance();
                Username = Passing.getPassingUsername();
                Email =  Passing.getPassingEmail();

                if(Username.equals("")){
                    Toast.makeText(getActivity().getApplicationContext(), "You Need To Login First", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(getActivity(), SelectMaterial.class);
//                    intent.putExtra("dataUsername",Username);
//                    intent.putExtra("dataEmail",Email);
                    startActivity(intent);
                    getActivity().finish();
                }

            }
        });
        return view;
    }
}
