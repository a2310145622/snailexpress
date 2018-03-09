package cn.raoxianhua.snailexpress;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by raoxianhua on 2018/1/11.
 */

public class ExpressAdapter extends RecyclerView.Adapter<ExpressAdapter.ViewHolder> {

    private Context mContext;
    private List<Express> mExpressList;
    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView scanTime;
        TextView trackRecord;

        public ViewHolder(View view){
            super(view);
            cardView = (CardView) view;
            scanTime = (TextView) view.findViewById(R.id.scanTime);
            trackRecord = (TextView) view.findViewById(R.id.trackRecord);
        }
    }

    public ExpressAdapter(List<Express> expressList) {
        mExpressList = expressList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.express_item, parent, false);
//        final ViewHolder holder = new ViewHolder(view);
//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                Fruit fruit = mFruitList.get(position);
//                Intent intent = new Intent(mContext, FruitActivity.class);
//                intent.putExtra(FruitActivity.FRUIT_NAME, fruit.getName());
//                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.getImageId());
//                mContext.startActivity(intent);
//            }
//        });
//        return holder;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Express express = mExpressList.get(position);
        holder.scanTime.setText((express.getExpressTime()));
        holder.trackRecord.setText((express.getExpressDetails()));
    }

    @Override
    public int getItemCount() {
        return mExpressList.size();
    }
}
