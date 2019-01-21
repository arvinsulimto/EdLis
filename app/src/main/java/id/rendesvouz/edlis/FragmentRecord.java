package id.rendesvouz.edlis;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import id.rendesvouz.edlis.adapter.ResultAdapter;
import id.rendesvouz.edlis.model.Result;


public class FragmentRecord extends Fragment {

    View view;
    String Username = "";
    String Email ="";

    RecyclerView rvResult;
    ArrayList<Result> listResult;

    public FragmentRecord() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.record_fragment,container,false);
        Singelton Passing = Singelton.getInstance();
        Email = Passing.getPassingEmail();
        Username = Passing.getPassingUsername();

        listResult = new ArrayList<>();

        rvResult = (RecyclerView) view.findViewById(R.id.rv_Result);
        rvResult.setLayoutManager(new LinearLayoutManager(getActivity()));

        final ResultAdapter resultAdapter = new ResultAdapter(getActivity(),listResult);
        rvResult.setAdapter(resultAdapter);


        LinearLayout RL_nodata = (LinearLayout) view.findViewById(R.id.RL_nodata);
        Button btnStartExam = (Button) view.findViewById(R.id.btnStartExam);

        RL_nodata.setVisibility(View.GONE);
        rvResult.setVisibility(View.GONE);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getActivity().getApplicationContext());
        Cursor cursor = databaseAccess.ViewResult(Email);
        if(cursor.getCount()==0){
            RL_nodata.setVisibility(View.VISIBLE);
            btnStartExam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),MainActivity.class);
//                    intent.putExtra("dataUsername", Username);
//                    intent.putExtra("dataEmail",Email);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
        }
        else{
            rvResult.setVisibility(View.VISIBLE);
            while (cursor.moveToNext()){
                Result result = new Result();
                result.setResultID(cursor.getString(0));
                result.setLevel(cursor.getString(2));
                result.setType(cursor.getString(3));
                result.setScore(cursor.getString(4));
                result.setDate(cursor.getString(5));
                result.setTime(cursor.getString(6));
                listResult.add(result);
            }
            resultAdapter.notifyDataSetChanged();
        }

        return view;
    }


}
