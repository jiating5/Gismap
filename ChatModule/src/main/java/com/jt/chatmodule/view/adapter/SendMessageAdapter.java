package com.jt.chatmodule.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baweigame.xmpplibrary.entity.MsgEntity;
import com.bumptech.glide.Glide;
import com.jt.chatmodule.R;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author 贾婷
 * @date 2020/1/5.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class SendMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    String toname;
    Context context;
    List<MsgEntity> msgList;

    public SendMessageAdapter(String toname, Context context, List<MsgEntity> msgList) {
        this.toname = toname;
        this.context = context;
        this.msgList = msgList;
    }

    @Override
    public int getItemViewType(int position) {
        if (msgList.get(position).getMsgType() == MsgEntity.MsgType.Txt){
            return 0;
        }else if (msgList.get(position).getMsgType() == MsgEntity.MsgType.Img){
            return 1;
        }else if (msgList.get(position).getMsgType() == MsgEntity.MsgType.Audio){
            return 2;
        }else if (msgList.get(position).getMsgType() == MsgEntity.MsgType.Video){
            return 3;
        }else if (msgList.get(position).getMsgType() == MsgEntity.MsgType.Location){
            return 4;
        }else {
            return 5;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0){
            return new TexHodler(LayoutInflater.from(context).inflate(R.layout.chatroom_msgtxt_layout_item,parent,false));
        }else if (viewType == 1){
            return new ImgHodler(LayoutInflater.from(context).inflate(R.layout.chatroom_msgimg_layout_item,parent,false));
        }else if (viewType == 2){
            return new TexHodler(LayoutInflater.from(context).inflate(R.layout.chatroom_msgtxt_layout_item,parent,false));
        }else if (viewType == 3){
            return new VideoHodler(LayoutInflater.from(context).inflate(R.layout.chatroom_msgvideo_layout_item,parent,false));
        }else if (viewType == 4){
            return new TexHodler(LayoutInflater.from(context).inflate(R.layout.chatroom_msgtxt_layout_item,parent,false));
        }else {
            return new TexHodler(LayoutInflater.from(context).inflate(R.layout.chatroom_msgtxt_layout_item,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (msgList.get(position).getMsgType() == MsgEntity.MsgType.Txt){
            TexHodler hodler = (TexHodler) holder;
            if (msgList.get(position).getTo().equals(toname)){
                hodler.totxtLayout.setVerticalGravity(View.VISIBLE);
                hodler.fromtxtLayout.setVisibility(View.GONE);
                hodler.totxtmsg.setText(msgList.get(position).getMsg());
            }else {
                hodler.totxtLayout.setVisibility(View.GONE);
                hodler.fromtxtLayout.setVisibility(View.VISIBLE);
                hodler.fromtxtmsg.setText(msgList.get(position).getMsg());
            }
        }else if (msgList.get(position).getMsgType() == MsgEntity.MsgType.Img){
            ImgHodler imgHodler = (ImgHodler) holder;
            if (msgList.get(position).getTo().equals(toname)){
                imgHodler.totimgLayout.setVerticalGravity(View.VISIBLE);
                imgHodler.fromimgLayout.setVisibility(View.GONE);
                Glide.with(context).load(msgList.get(position).getMsg()).override(60,60).into(imgHodler.toimgmsg);
            }else {
                imgHodler.totimgLayout.setVisibility(View.GONE);
                imgHodler.fromimgLayout.setVisibility(View.VISIBLE);
                Glide.with(context).load(msgList.get(position).getMsg()).override(60,60).into(imgHodler.fromimgmsg);
            }
        }else if (msgList.get(position).getMsgType() == MsgEntity.MsgType.Audio){
            TexHodler hodler = (TexHodler) holder;
            if (msgList.get(position).getTo().equals(toname)){
                hodler.totxtLayout.setVerticalGravity(View.VISIBLE);
                hodler.fromtxtLayout.setVisibility(View.GONE);
                hodler.totxtmsg.setText("[语言]");
                hodler.totxtmsg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        initAudio(msgList.get(position).getMsg());
                    }
                });
            }else {
                hodler.totxtLayout.setVisibility(View.GONE);
                hodler.fromtxtLayout.setVisibility(View.VISIBLE);
                hodler.fromtxtmsg.setText("[语言]");
                hodler.fromtxtmsg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        initAudio(msgList.get(position).getMsg());
                    }
                });
            }
        }else if (msgList.get(position).getMsgType() == MsgEntity.MsgType.Video){
            VideoHodler hodler = (VideoHodler) holder;
            if (msgList.get(position).getTo().equals(toname)){
                hodler.tovideoLayout.setVisibility(View.VISIBLE);
                hodler.fromvideoLayout.setVisibility(View.GONE);
                Glide.with(context).load(msgList.get(position).getMsg()).override(100,100).into(hodler.tovideomsg);
                hodler.tovideomsg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        initVideo(msgList.get(position).getMsg());
                    }
                });
            }else {
                hodler.tovideoLayout.setVisibility(View.GONE);
                hodler.fromvideoLayout.setVisibility(View.VISIBLE);
                Glide.with(context).load(msgList.get(position).getMsg()).override(100,100).into(hodler.fromvideomsg);
                hodler.fromvideomsg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        initVideo(msgList.get(position).getMsg());
                    }
                });
            }
        }else if (msgList.get(position).getMsgType() == MsgEntity.MsgType.Location){
            TexHodler hodler = (TexHodler) holder;
            if (msgList.get(position).getTo().equals(toname)){
                hodler.totxtLayout.setVerticalGravity(View.VISIBLE);
                hodler.fromtxtLayout.setVisibility(View.GONE);
                hodler.totxtmsg.setText(msgList.get(position).getMsg());
            }else {
                hodler.totxtLayout.setVisibility(View.GONE);
                hodler.fromtxtLayout.setVisibility(View.VISIBLE);
                hodler.fromtxtmsg.setText(msgList.get(position).getMsg());
            }
        }
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    class TexHodler extends RecyclerView.ViewHolder{

        LinearLayout fromtxtLayout,totxtLayout;
        TextView totxtmsg,fromtxtmsg;

        public TexHodler(@NonNull View itemView) {
            super(itemView);
            fromtxtLayout = itemView.findViewById(R.id.from_layout);
            totxtLayout = itemView.findViewById(R.id.to_layout);
            totxtmsg = itemView.findViewById(R.id.to_tv_txtmsg);
            fromtxtmsg = itemView.findViewById(R.id.from_tv_txtmsg);
        }
    }

    class ImgHodler extends RecyclerView.ViewHolder{

        LinearLayout fromimgLayout,totimgLayout;
        ImageView toimgmsg,fromimgmsg;

        public ImgHodler(@NonNull View itemView) {
            super(itemView);
            fromimgLayout = itemView.findViewById(R.id.fromimg_layout);
            totimgLayout = itemView.findViewById(R.id.toimg_layout);
            toimgmsg = itemView.findViewById(R.id.to_iv_imgmsg);
            fromimgmsg = itemView.findViewById(R.id.from_iv_imgmsg);
        }
    }

    class VideoHodler extends RecyclerView.ViewHolder{
        LinearLayout fromvideoLayout,tovideoLayout;
        ImageView tovideomsg,fromvideomsg;
        public VideoHodler(@NonNull View itemView) {
            super(itemView);
            fromvideoLayout = itemView.findViewById(R.id.fromvideo_layout);
            tovideoLayout = itemView.findViewById(R.id.tovideo_layout);
            tovideomsg = itemView.findViewById(R.id.to_iv_videomsg);
            fromvideomsg = itemView.findViewById(R.id.from_iv_videomsg);
        }
    }

    private void initAudio(String path) {
        final MediaPlayer player = new MediaPlayer();
        try {
            player.setDataSource(path);
            player.prepareAsync();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    player.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void initVideo(String videoPath) {
        //构建者
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.send_video_layout_item, null);
        final VideoView videoView = view.findViewById(R.id.send_video);
        Uri uri = Uri.parse(videoPath);//将路径转换成uri
        videoView.setVideoURI(uri);//为视频播放器设置视频路径
        videoView.setMediaController(new MediaController(context));//显示控制栏
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.start();//播放
            }
        });
        builder.setView(view);
        builder.create().show();
    }
}
