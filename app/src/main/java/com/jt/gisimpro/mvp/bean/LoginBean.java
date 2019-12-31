package com.jt.gisimpro.mvp.bean;

/**
 * @author 贾婷
 * @date 2019/12/30.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class LoginBean {

    public LoginBean(int id, String usercode, String username, Object pwd, String sex, String birthday, String headerimg, String nick, int utype, String imuseraccount, String signdesc, int openlocation, int openmsgalert) {
        this.id = id;
        this.usercode = usercode;
        this.username = username;
        this.pwd = pwd;
        this.sex = sex;
        this.birthday = birthday;
        this.headerimg = headerimg;
        this.nick = nick;
        this.utype = utype;
        this.imuseraccount = imuseraccount;
        this.signdesc = signdesc;
        this.openlocation = openlocation;
        this.openmsgalert = openmsgalert;
    }

    /**
     * id : 7
     * usercode : 8069311fdc0a492cbc0d78b60c41210c
     * username : 123
     * pwd : null
     * sex : 1
     * birthday : 123
     * headerimg : 123
     * nick : 123
     * utype : 0
     * imuseraccount : 123
     * signdesc : 123
     * openlocation : 0
     * openmsgalert : 3
     */

    private int id;
    private String usercode;
    private String username;
    private Object pwd;
    private String sex;
    private String birthday;
    private String headerimg;
    private String nick;
    private int utype;
    private String imuseraccount;
    private String signdesc;
    private int openlocation;
    private int openmsgalert;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getPwd() {
        return pwd;
    }

    public void setPwd(Object pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHeaderimg() {
        return headerimg;
    }

    public void setHeaderimg(String headerimg) {
        this.headerimg = headerimg;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getUtype() {
        return utype;
    }

    public void setUtype(int utype) {
        this.utype = utype;
    }

    public String getImuseraccount() {
        return imuseraccount;
    }

    public void setImuseraccount(String imuseraccount) {
        this.imuseraccount = imuseraccount;
    }

    public String getSigndesc() {
        return signdesc;
    }

    public void setSigndesc(String signdesc) {
        this.signdesc = signdesc;
    }

    public int getOpenlocation() {
        return openlocation;
    }

    public void setOpenlocation(int openlocation) {
        this.openlocation = openlocation;
    }

    public int getOpenmsgalert() {
        return openmsgalert;
    }

    public void setOpenmsgalert(int openmsgalert) {
        this.openmsgalert = openmsgalert;
    }
}
