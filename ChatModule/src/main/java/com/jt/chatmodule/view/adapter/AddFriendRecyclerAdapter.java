package com.jt.chatmodule.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jt.chatmodule.R;
import com.jt.chatmodule.view.bean.FriendBean;

import java.util.List;

/**
 * @author 贾婷
 * @date 2020/1/2.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class AddFriendRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<FriendBean> friendBeans;

    public AddFriendRecyclerAdapter(Context context, List<FriendBean> friendBeans) {
        this.context = context;
        this.friendBeans = friendBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new HodlerChild(LayoutInflater.from(context).inflate(R.layout.addfriend_layout_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            HodlerChild hodlerChild = (HodlerChild) holder;
            hodlerChild.name.setText(friendBeans.get(position).getNick());
            hodlerChild.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnItemClick(position);
                }
            });
    }

    @Override
    public int getItemCount() {
        return friendBeans.size();
    }

    class HodlerChild extends RecyclerView.ViewHolder{

        TextView name;
        ImageView add;

        public HodlerChild(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.addfriend_name);
            add = itemView.findViewById(R.id.addfriend_add);
        }
    }

    //声明item回调接口
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    //定义
    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
}
