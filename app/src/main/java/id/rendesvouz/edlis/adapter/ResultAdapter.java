package id.rendesvouz.edlis.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.rendesvouz.edlis.R;
import id.rendesvouz.edlis.model.Result;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder>{

    Context context;
    ArrayList<Result> listResult;

    public ResultAdapter(Context context, ArrayList<Result> listResult){
        this.context = context;
        this.listResult = listResult;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_result,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(listResult.get(i));
    }

    @Override
    public int getItemCount() {
        return listResult.size();
    }

    public void setData(ArrayList<Result> listPeople){
        this.listResult=listPeople;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvType,tvScore,tvLevel, tvTime, tvDate;
        public ViewHolder(View itemView){
            super(itemView);
            tvType = itemView.findViewById(R.id.tv_Type);
            tvScore = itemView.findViewById(R.id.tv_Score);
            tvLevel = itemView.findViewById(R.id.tv_Level);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
        public void bind(Result result){
            tvType.setText(result.getType());
            tvScore.setText(result.getScore());
            tvLevel.setText(result.getLevel());
            tvDate.setText(result.getDate());
            tvTime.setText(result.getTime());
        }
    }
}

