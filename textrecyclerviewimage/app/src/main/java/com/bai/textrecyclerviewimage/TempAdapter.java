package com.bai.textrecyclerviewimage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Cerated by bailing
 * Create date : 2019/8/5 16:30
 * description :
 */
public class TempAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Integer> mIntegerList;
    private AdapterOnClickListener mAdapterOnClickListener;

    public TempAdapter(Context context, List<Integer> list){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mIntegerList = list;
    }

    public void SetClick(AdapterOnClickListener listener){
        mAdapterOnClickListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if (i==0) {
            view = mLayoutInflater.inflate(R.layout.item_temp2,viewGroup,false);
        }
        else {
            view = mLayoutInflater.inflate(R.layout.item_temp,viewGroup,false);

        }
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mIntegerList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position==10||position==0){
            return 0;
        }else
            return 1;
    }

    public class myHolder extends RecyclerView.ViewHolder{

        public myHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAdapterOnClickListener.onClick(getLayoutPosition());
                }
            });
        }


    }

}
