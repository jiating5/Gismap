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
import com.jt.chatmodule.view.bean.LinkManBean;

import java.util.List;

/**
 * @author 贾婷
 * @date 2020/1/2.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<LinkManBean> linkManBeans;

    public RecyclerAdapter(Context context, List<LinkManBean> linkManBeans) {
        this.context = context;
        this.linkManBeans = linkManBeans;
    }

    @Override
    public int getItemViewType(int position) {
        if (linkManBeans.get(position).getType() == 0){
            return 0;
        }else{
            return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (linkManBeans.get(viewType).getType() == 0){
            return new HodlerGroup(LayoutInflater.from(context).inflate(R.layout.linkmangroup_layout_item,parent,false));
        }else {
            return new HodlerChild(LayoutInflater.from(context).inflate(R.layout.linkmanlist_layout_item,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0){
            HodlerGroup hodlerGroup = (HodlerGroup) holder;
            hodlerGroup.title.setText(linkManBeans.get(position).getPhonebook_label());
        }else {
            HodlerChild hodlerChild = (HodlerChild) holder;
            hodlerChild.name.setText(linkManBeans.get(position).getName());
            hodlerChild.phone.setText(linkManBeans.get(position).getPhone());
            hodlerChild.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return linkManBeans.size();
    }

    class HodlerGroup extends RecyclerView.ViewHolder{

        TextView title;

        public HodlerGroup(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.lmg_title);
        }
    }

    class HodlerChild extends RecyclerView.ViewHolder{

        TextView name,phone;
        ImageView add,head;

        public HodlerChild(@NonNull View itemView) {
            super(itemView);
            head = itemView.findViewById(R.id.linkman_head);
            name = itemView.findViewById(R.id.linkman_name);
            phone = itemView.findViewById(R.id.linkman_phone);
            add = itemView.findViewById(R.id.linkman_add);
        }
    }
}
