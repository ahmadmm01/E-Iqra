package com.pam.e_iqra.model;

import android.content.Context;

import java.util.List;

public class User
{
    private String tid, tpass, sid, spass, sname;
    private Context context;
    private List<User> list;

    public User()
    {

    }

    public User(String sid, String sname)
    {
        this.sid = sid;
        this.sname = sname;
    }

    public User(String tid, String tpass, String sid, String spass, String sname)
    {
        this.tid = tid;
        this.tpass = tpass;
        this.sid = sid;
        this.spass = spass;
        this.sname = sname;
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

    public String getSname()
    {
        return sname;
    }

    public void setSname(String sname)
    {
        this.sname = sname;
    }
}
