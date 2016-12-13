package com.qtking.chatboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Think on 2016/12/13.
 */

public class ChatAdapater extends RecyclerView.Adapter<ChatAdapater.ViewHolder> {

    private List<Message> mMessageList;

    public ChatAdapater(List<Message> messages) {

        mMessageList = messages;
        Logger.d("size=" + mMessageList.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message = mMessageList.get(position);
        Logger.d("position=" + position);
        if (message.getType() == Message.TYPE_RECEIVED) {
            Logger.d("Message.TYPE_RECEIVED");
            holder.mLayoutRight.setVisibility(View.GONE);
            holder.mLayoutLeft.setVisibility(View.VISIBLE);

            holder.tvLeft.setText(message.getContent());

        } else if (message.getType() == Message.TYPE_SEND) {
            Logger.d("Message.TYPE_SEND");
            holder.mLayoutRight.setVisibility(View.VISIBLE);
            holder.mLayoutLeft.setVisibility(View.GONE);
            holder.tvRight.setText(message.getContent());
        }

    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.layout_left)
        LinearLayout mLayoutLeft;

        @BindView(R.id.layout_right)
        LinearLayout mLayoutRight;

        @BindView(R.id.tv_left)
        TextView tvLeft;

        @BindView(R.id.tv_right)
        TextView tvRight;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
