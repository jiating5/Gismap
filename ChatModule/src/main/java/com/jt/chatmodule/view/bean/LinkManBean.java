package com.jt.chatmodule.view.bean;

/**
 * @author 贾婷
 * @date 2020/1/2.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class LinkManBean {
    //联系人姓名
    private String name;
    //电话
    private String phone;
    //首字母
    private String phonebook_label;
    //多布局
    private int type;

    public LinkManBean() {
    }

    public LinkManBean(String name, String phone, String phonebook_label, int type) {
        this.name = name;
        this.phone = phone;
        this.phonebook_label = phonebook_label;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhonebook_label() {
        return phonebook_label;
    }

    public void setPhonebook_label(String phonebook_label) {
        this.phonebook_label = phonebook_label;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
