package com.jt.basemodule.bean;
/**
 * @author 贾婷
 * @date 2019/12/30.
 * GitHub：https://github.com/jiating5
 * description：
 */
public class TokenBean {

    /**
     * access_token : bnOiEH38WhsWNbd1kCRS3K4VGL05R-yFvaa37hTTH9qS7VbwWNwnRkW-YDhhpD7vXtoXhGUDfA6v-cj4j3Wjx2U8SV9KyZeL7pYJdP0FUKWbtz_a-QNWbKLVHXpKkntARauIN-m8bIz3aLVP6oGRDF_Rd-OBvrH5DSIKBR6ogJVNxFSNT1wVleysFnouTeRWn4OGhQDDkosdzxyidSOgy7XtL0pkszbbIr4_rOn8TAcoza_d8F1gl6Je7_jl-0X0TXUU6jM0Cx7S9VwpralNgm-ednj1fpT2CPoDUCjaWpg
     * token_type : bearer
     * expires_in : 86399
     */

    private String access_token;
    private String token_type;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
