package com.pam.e_iqra.model;

public class User
{
    private String tid, tpass, sid, spass;

    public User()
    {

    }

    public User(String tid, String tpass, String sid, String spass)
    {
        this.tid = tid;
        this.tpass = tpass;
        this.sid = sid;
        this.spass = spass;

    }

    public String getTid()
    {
        return tid;
    }

    public void setTid(String tid)
    {
        this.tid = tid;
    }

    public String getTpass()
    {
        return tpass;
    }

    public void setTpass(String tpass)
    {
        this.tpass = tpass;
    }
    public String getSid()
    {
        return sid;
    }

    public void setSid(String sid)
    {
        this.sid = sid;
    }

    public String getSpass()
    {
        return spass;
    }

    public void setSpass(String spass)
    {
        this.spass = spass;
    }
}
