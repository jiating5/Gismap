package com.jt.chatmodule.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jt.chatmodule.R;
import com.jt.chatmodule.view.bean.FriendBean;
import com.jt.commonmodule.arouter.ARouterConfig;

import java.util.List;

/**
 * @author 贾婷
 * @date 2020/1/2.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class FriendRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<FriendBean> friendBeans;

    public FriendRecyclerAdapter(Context context, List<FriendBean> friendBeans) {
        this.context = context;
        this.friendBeans = friendBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new HodlerChild(LayoutInflater.from(context).inflate(R.layout.friendlists_layout_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            final HodlerChild hodlerChild = (HodlerChild) holder;
            hodlerChild.name.setText(friendBeans.get(position).getNick());
            hodlerChild.phone.setText(friendBeans.get(position).getUsername());
            hodlerChild.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ARouter.getInstance().build(ARouterConfig.CHATMODULE_CHATROOM).withString("name",friendBeans.get(position).getNick()).withString("username",friendBeans.get(position).getUsername()).navigation();
                }
            });
    }

    @Override
    public int getItemCount() {
        return friendBeans.size();
    }

    class HodlerChild extends RecyclerView.ViewHolder{

        TextView name,phone;

        public HodlerChild(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.lkl_name);
            phone = itemView.findViewById(R.id.lkl_phone);
        }
    }
}
