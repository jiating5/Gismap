package com.jt.chatmodule.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Location;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.OSSResult;
import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.callback.IMsgCallback;
import com.baweigame.xmpplibrary.contract.IXmppMsg;
import com.baweigame.xmpplibrary.entity.MsgEntity;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iceteck.silicompressorr.SiliCompressor;
import com.ilike.voicerecorder.widget.VoiceRecorderView;
import com.jt.basemodule.finalStr.BaseConstant;
import com.jt.chatmodule.R;
import com.jt.chatmodule.utils.FaceUtils;
import com.jt.chatmodule.utils.LocationUtils;
import com.jt.chatmodule.utils.ThreadUtils;
import com.jt.chatmodule.view.adapter.FaceAdapter;
import com.jt.chatmodule.view.adapter.SendMessageAdapter;
import com.jt.commonmodule.arouter.ARouterConfig;
import com.jt.basemodule.finalStr.AliyunUtils;
import com.wyp.avatarstudio.AvatarStudio;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Route(path = ARouterConfig.CHATMODULE_CHATROOM)
public class ChatRoomActivity extends AppCompatActivity {

    @Autowired
    String name;
    @Autowired
    String username;

    Button btn_send;
    TextView tv_name;
    EditText edt_msg;
    String friendjid;
    ImageView iv_back,iv_photo,iv_voice,iv_video,iv_location,iv_face;
    VoiceRecorderView voiceRecorderView;
    List<MsgEntity> msgList = new ArrayList<>();
    SendMessageAdapter adapter;
    FaceAdapter faceAdapter;
    RecyclerView recyclerView,rv_face;
    XmppManager xMmanager;
    IXmppMsg xMsgManager;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                final String videoPath = (String) msg.obj;
                final String fileName = username + "_" + System.currentTimeMillis();
                AliyunUtils.getInstance().upload(BaseConstant.ALI_BUCKETNAME,"video/" + fileName,videoPath,new OSSCompletedCallback(){
                    @Override
                    public void onSuccess(OSSRequest request, OSSResult result) {
                        xMsgManager.sendSingleMessage(friendjid,videoPath);
                    }
                    @Override
                    public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {

                    }
                });
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        ARouter.getInstance().inject(this);
        /** 所要申请的权限*/
        String[] perms = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.INTERNET
        };
        ActivityCompat.requestPermissions(this,perms,100);
        initView();
    }

    private void initView() {
        voiceRecorderView = findViewById(R.id.chatromm_vrv_voice);
        rv_face = findViewById(R.id.chatroom_rv_face);
        iv_face = findViewById(R.id.chatroom_iv_face);
        iv_location = findViewById(R.id.chatromm_iv_location);
        iv_video = findViewById(R.id.chatroom_iv_video);
        iv_voice = findViewById(R.id.chatromm_iv_voice);
        iv_photo = findViewById(R.id.chatroom_iv_photo);
        iv_back = findViewById(R.id.chatromm_iv);
        tv_name = findViewById(R.id.chatroom_tv_name);
        btn_send = findViewById(R.id.chatromm_btn_send);
        edt_msg = findViewById(R.id.chatroom_edt_msg);
        tv_name.setText(name);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerView = findViewById(R.id.chatromm_recycler);
        //初始化
        xMmanager = XmppManager.getInstance();
        xMsgManager = xMmanager.getXmppMsgManager();
        friendjid = username + "@" + xMmanager.getXmppConfig().getDomainName();
        //适配器
        adapter = new SendMessageAdapter(username,this,msgList);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(msgList.size()-1);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initClick();
        initListener();
    }

    private void initListener() {
        ThreadUtils.getInstance().getExecutorService().execute(new Runnable() {
            @Override
            public void run() {
                XmppManager.getInstance().addMessageListener(new IMsgCallback(){
                    @Override
                    public void Success(final MsgEntity msgEntity) {
                        final String msg = msgEntity.getMsg();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ChatRoomActivity.this,msg,Toast.LENGTH_SHORT).show();
                                msgList.add(new MsgEntity("",username,msg,msgEntity.getMsgType()));
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                    @Override
                    public void Failed(Throwable throwable) {
                        Toast.makeText(ChatRoomActivity.this,"未接收到消息",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initClick() {
        //消息
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rv_face.setVisibility(View.GONE);
                ThreadUtils.getInstance().getExecutorService().execute(new Runnable() {
                    @Override
                    public void run() {
                        String message = edt_msg.getText().toString();
                        xMsgManager.sendSingleMessage(friendjid,message);
                        msgList.add(new MsgEntity("","",message,MsgEntity.MsgType.Txt));
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });

        //图片
        iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rv_face.setVisibility(View.GONE);
                new AvatarStudio.Builder(ChatRoomActivity.this)
                        .needCrop(true)//是否裁剪，默认裁剪
                        .setTextColor(Color.BLUE)
                        .dimEnabled(true)//背景是否dim 默认true
                        .setAspect(1, 1)//裁剪比例 默认1：1
                        .setOutput(200, 200)//裁剪大小 默认200*200
                        .setText("打开相机", "从相册中选取", "取消")
                        .show(new AvatarStudio.CallBack() {
                            @Override
                            public void callback(final String uri) {
                                final String fileName = username + "_" + System.currentTimeMillis();
                                AliyunUtils.getInstance().upload(BaseConstant.ALI_BUCKETNAME,"img/"+fileName+".png", uri,new OSSCompletedCallback(){
                                    @Override
                                    public void onSuccess(OSSRequest request, OSSResult result) {
                                        msgList.add(new MsgEntity("", "", BaseConstant.ALI_FILE_PATH+"img/"+fileName + ".png", MsgEntity.MsgType.Img));
                                        xMsgManager.sendSingleMessage(friendjid,BaseConstant.ALI_FILE_PATH + fileName + ".png");
                                    }

                                    @Override
                                    public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {
                                        Toast.makeText(ChatRoomActivity.this,"上传失败",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                adapter.notifyDataSetChanged();
                            }
                        });
            }
        });

        //发语音
        iv_voice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                rv_face.setVisibility(View.GONE);
                return  voiceRecorderView.onPressToSpeakBtnTouch(view, motionEvent, new VoiceRecorderView.EaseVoiceRecorderCallback() {

                    @Override
                    public void onVoiceRecordComplete(final String voiceFilePath, final int voiceTimeLength) {
                        final String fileName = username + "_" + System.currentTimeMillis();
                        AliyunUtils.getInstance().upload(BaseConstant.ALI_BUCKETNAME, "audio/" + fileName, voiceFilePath, new OSSCompletedCallback() {
                            @Override
                            public void onSuccess(OSSRequest request, OSSResult result) {
                                msgList.add(new MsgEntity("","",BaseConstant.ALI_FILE_PATH + "audio/"+fileName, MsgEntity.MsgType.Audio));
                                xMsgManager.sendSingleMessage(friendjid,BaseConstant.ALI_FILE_PATH +fileName );
                            }

                            @Override
                            public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {
                                Toast.makeText(ChatRoomActivity.this,"上传失败",Toast.LENGTH_SHORT).show();
                            }
                        });
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

        //视频
        iv_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rv_face.setVisibility(View.GONE);
                new AlertView("上传视频", null, "取消", null,
                        new String[]{"拍照", "从相册中选择"},
                        ChatRoomActivity.this, AlertView.Style.ActionSheet, new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        if (position == 0){
                            Intent intent = new Intent();
                            intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
                            startActivityForResult(intent,101);
                        }else {
                            Intent intent2 = new Intent();
                            intent2.setAction(Intent.ACTION_PICK);
                            intent2.setType("video/*");
                            startActivityForResult(intent2, 102);
                        }
                    }
                }).show();
            }
        });

        //定位
        iv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rv_face.setVisibility(View.GONE);
                LocationUtils.getInstance(ChatRoomActivity.this).getLngAndLat(new LocationUtils.OnLocationResultListener() {
                    @Override
                    public void onLocationResult(Location location) {
                        String loStr = "纬度:" + location.getLatitude() + "经度:" + location.getLongitude();
                        msgList.add(new MsgEntity("","",loStr,MsgEntity.MsgType.Location));
                        adapter.notifyDataSetChanged();
                        Log.i("xxx",loStr);
                        xMsgManager.sendSingleMessage(friendjid,loStr);
                    }
                    @Override
                    public void OnLocationChange(Location location) {

                    }
                });
            }
        });

        //表情包
        iv_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rv_face.setVisibility(View.VISIBLE);
                final List<String> strList = FaceUtils.getInstance().getStrList();
                faceAdapter = new FaceAdapter(R.layout.chatroom_face_item,strList);
                rv_face.setLayoutManager(new GridLayoutManager(ChatRoomActivity.this,10));
                rv_face.setAdapter(faceAdapter);
                faceAdapter.notifyDataSetChanged();
                faceAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        if (view.getId() == R.id.face_tv){
                            StringBuilder builder = new StringBuilder();
                            builder.append(edt_msg.getText().toString());
                            builder.append(strList.get(position));
                            Toast.makeText(ChatRoomActivity.this,strList.get(position),Toast.LENGTH_SHORT).show();
                            edt_msg.setText(builder.toString());
                        }
                    }
                });
            }
        });
    }

    //视频回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 & resultCode == RESULT_OK) {
//            取视频
            String videoPath = null;
            String[] proj = {MediaStore.Video.Media.DATA};
            Cursor cursor = getContentResolver().query(data.getData(), proj, null, null, null);
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
                videoPath = cursor.getString(column_index);
            }
            cursor.close();
            compressVideo(videoPath);
        }else if (requestCode == 102 & resultCode == RESULT_OK){
            //取视频
            String videoPath = null;
            String[] proj = {MediaStore.Video.Media.DATA};
            Cursor cursor = getContentResolver().query(data.getData(), proj, null, null, null);
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
                videoPath = cursor.getString(column_index);
            }
            cursor.close();
            compressVideo(videoPath);
        }
    }

    private void compressVideo(final String path) {
        msgList.add(new MsgEntity("","",path, MsgEntity.MsgType.Video));
        Log.i("视频文件",path);
        adapter.notifyDataSetChanged();
        ThreadUtils.getInstance().getExecutorService().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String videoPath = SiliCompressor.with(ChatRoomActivity.this).compressVideo(path, "/sdcard/Movies");
                    if (videoPath!=null){
                        Message message = new Message();
                        message.what = 1;
                        message.obj = videoPath;
                        handler.sendMessage(message);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
