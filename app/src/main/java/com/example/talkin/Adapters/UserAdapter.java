package com.example.talkin.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.talkin.MessageActivity;
import com.example.talkin.Model.Users;
import com.example.talkin.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context context;
    private List<Users> mUsers;
    private boolean isChat;

    public UserAdapter(Context context, List<Users> mUsers,boolean isChat) {
        this.context = context;
        this.mUsers = mUsers;
        this.isChat=isChat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    { View view= LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);
        return new UserAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Users users=mUsers.get(position);
        holder.username.setText(users.getUsername());

        if (users.getImageURL().equals("default"))                 // users dikhane ke lie
        {
            holder.imageView.setImageResource(R.mipmap.ic_launcher);


        }else{

            Glide.with(context)
                    .load(users.getImageURL())
                    .into(holder.imageView);
        }

        if (isChat)
        {
            if (users.getStatus().equals("online"))
            {
                   holder.imageViewON.setVisibility(View.VISIBLE);
                   holder.imageViewOFF.setVisibility(View.GONE);


            }else
            {
                holder.imageViewON.setVisibility(View.GONE);
                holder.imageViewOFF.setVisibility(View.VISIBLE);

            }
        }else
        {
            holder.imageViewON.setVisibility(View.GONE);
            holder.imageViewOFF.setVisibility(View.GONE);


        }

         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i=new Intent(context, MessageActivity.class);
                 i.putExtra("userid",users.getId());
                 context.startActivity(i);
             }
         });






    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView username;
        public ImageView imageView;
        public ImageView imageViewON;
        public ImageView imageViewOFF;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username=itemView.findViewById(R.id.textView30);
            imageView=itemView.findViewById(R.id.imageView);
            imageViewON=itemView.findViewById(R.id.statusimage);
            imageViewOFF=itemView.findViewById(R.id.statusimageOFF);
        }
    }
}
