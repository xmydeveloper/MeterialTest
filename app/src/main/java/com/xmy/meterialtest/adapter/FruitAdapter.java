package com.xmy.meterialtest.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xmy.meterialtest.R;
import com.xmy.meterialtest.activity.FruitActivity;
import com.xmy.meterialtest.bean.Fruit;

import java.util.List;

/**
 * @projectName: MeterialTest
 * @packageName: com.xmy.meterialtest
 * @className: FruitAdapter
 * @author:xiamingyan
 * @time: 2017/2/14	17:02
 * @E-mail：xmydeveloper@163.com
 * @desc: TODO
 * @upDateAuthor: lenovo
 * @upDate: 2017/2/14
 * @upDateDesc: TODO
 */
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    List<Fruit> mFruitList;
    private Context mContext;

    public FruitAdapter(List<Fruit> fruitListlist) {
        this.mFruitList = fruitListlist;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        ImageView mFruitImage;
        TextView mFruitName;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView;
            mFruitImage = (ImageView) itemView.findViewById(R.id.fruit_image);
            mFruitName = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                FruitActivity.startFruitActivity(mContext, mFruitList.get(position).getName(), mFruitList.get(position).getImageId());
                Log.e("AAAAAAAAAAA","name-----"+mFruitList.get(position).getName()+"------------image------"+mFruitList.get(position).getImageId());

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.mFruitName.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(holder.mFruitImage);
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }


}
