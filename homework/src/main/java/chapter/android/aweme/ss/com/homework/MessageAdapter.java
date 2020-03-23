package chapter.android.aweme.ss.com.homework;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.widget.CircleImageView;

import static android.support.v4.content.ContextCompat.startActivity;

interface MessageOnClickListener {
    void onItemClick(View v, int pos);
}

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>  {
    private List<Message> messageList;
    private MessageOnClickListener messageOnClickListener;

    MessageAdapter(List<Message> list, MessageOnClickListener listener) {
        this.messageList = list;
        this.messageOnClickListener = listener;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.im_list_item, parent, false);
        return new MessageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MessageViewHolder holder, int position) {
        Message message = messageList.get(position);
        holder.titleTextView.setText(message.getTitle());
        holder.descriptionTextView.setText(message.getDescription());
        holder.timeTextView.setText(message.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                    int pos = holder.getLayoutPosition();
                messageOnClickListener.onItemClick(holder.itemView, pos);
                }
            }
        );
        int icon;
        switch (message.getIcon()) {
            case "TYPE_USER":
                icon = R.drawable.icon_girl;
                break;
            case "TYPE_ROBOT":
                icon = R.drawable.session_robot;
                break;
            case "TYPE_SYSTEM":
                icon = R.drawable.session_system_notice;
                break;
            case "TYPE_GAME":
                icon = R.drawable.icon_micro_game_comment;
                break;
            default:
                icon = R.drawable.session_stranger;
                break;
        }
        holder.imageView.setImageResource(icon);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        TextView timeTextView;
        CircleImageView imageView;

        MessageViewHolder(View view) {
            super(view);
            timeTextView = view.findViewById(R.id.tv_time);
            descriptionTextView = view.findViewById(R.id.tv_description);
            titleTextView = view.findViewById(R.id.tv_title);
            imageView = view.findViewById(R.id.iv_avatar);
        }
    }
}
