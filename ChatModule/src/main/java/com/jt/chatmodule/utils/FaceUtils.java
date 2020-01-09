package com.jt.chatmodule.utils;

import java.util.ArrayList;
import java.util.List;

public class FaceUtils {

    private static volatile FaceUtils singleton;
    private List<String> slist;

    private FaceUtils() {
        slist = new ArrayList<>();
        initFace();
    }
    public List<String> getStrList(){
        return slist;
    }

    private void initFace() {
        slist.add("\uD83D\uDE00");
        slist.add("\uD83D\uDE01");
        slist.add("\uD83D\uDE02");
        slist.add("\uD83D\uDE03");
        slist.add("\uD83D\uDE04");
        slist.add("\uD83D\uDE05");
        slist.add("\uD83D\uDE06");
        slist.add("\uD83D\uDE07");
        slist.add("\uD83D\uDE08");
        slist.add("\uD83D\uDE09");
        slist.add("\uD83D\uDE10");
        slist.add("\uD83D\uDE11");
        slist.add("\uD83D\uDE12");
        slist.add("\uD83D\uDE13");
        slist.add("\uD83D\uDE14");
        slist.add("\uD83D\uDE15");
        slist.add("\uD83D\uDE16");
        slist.add("\uD83D\uDE17");
        slist.add("\uD83D\uDE18");
        slist.add("\uD83D\uDE19");
        slist.add("\uD83D\uDE20");
        slist.add("\uD83D\uDC66");
        slist.add("\uD83D\uDC67");
        slist.add("\uD83D\uDC68");
        slist.add("\uD83D\uDC69");
        slist.add("\uD83D\uDC70");
        slist.add("\uD83D\uDC71");
        slist.add("\uD83D\uDC72");
        slist.add("\uD83D\uDC73");
        slist.add("\uD83D\uDC74");
        slist.add("\uD83D\uDC75");
        slist.add("\uD83D\uDC76");
        slist.add("\uD83D\uDC77");
        slist.add("\uD83D\uDC78");
        slist.add("\uD83C\uDF85");
        slist.add("\uD83D\uDC8F");
        slist.add("\uD83D\uDC91");
        slist.add("\uD83D\uDC6A");
        slist.add("\uD83D\uDC7C");
        slist.add("\uD83D\uDC86");
        slist.add("\uD83D\uDC87");
        slist.add("\uD83D\uDE4D");
        slist.add("\uD83D\uDE4C");
        slist.add("\uD83D\uDE4F");
        slist.add("\uD83D\uDC63");
        slist.add("\uD83D\uDEAD");
        slist.add("\uD83D\uDE48");
        slist.add("\uD83D\uDE49");
        slist.add("\uD83D\uDC36");

    }

    public static FaceUtils getInstance() {
        if (singleton == null) {
            synchronized (FaceUtils.class) {
                if (singleton == null) {
                    singleton = new FaceUtils();
                }
            }
        }
        return singleton;
    }
}