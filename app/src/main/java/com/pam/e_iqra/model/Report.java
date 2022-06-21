package com.pam.e_iqra.model;

public class Report
{
    private String rid, sid, iid, ivalue;

    public Report()
    {

    }

    public Report(String rid, String sid, String iid, String ivalue)
    {
        this.rid = rid;
        this.sid = sid;
        this.iid = iid;
        this.ivalue = ivalue;
    }

    public String getRid()
    {
        return rid;
    }

    public void SetRid()
    {
        this.rid = rid;
    }

    public String getSid()
    {
        return sid;
    }

    public void  setSid()
    {
        this.sid = sid;
    }

    public String getIid()
    {
        return iid;
    }

    public void setIid()
    {
        this.iid = iid;
    }

    public String getIvalue()
    {
        return ivalue;
    }

    public void setIvalue()
    {
        this.ivalue = ivalue;
    }
}
