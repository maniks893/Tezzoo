package com.example.VasMobitec;

public class Member {
    private Integer R_id;
    private String name;
    private String S_name;
    private String S_address;
    private String state;
    private String city;
    private Integer m_pincode;
    private String email;
    private String phn;
    private String gst;

    public Member() {
    }

    public Integer getR_id() {
        return R_id;
    }

    public void setR_id(Integer r_id) {
        R_id = r_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getS_name() {
        return S_name;
    }

    public void setS_name(String s_name) {
        S_name = s_name;
    }

    public String getS_address() {
        return S_address;
    }

    public void setS_address(String s_address) {
        S_address = s_address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getM_pincode() {
        return m_pincode;
    }

    public void setM_pincode(Integer m_pincode) {
        this.m_pincode = m_pincode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }
}