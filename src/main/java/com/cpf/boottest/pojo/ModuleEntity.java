package com.cpf.boottest.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by cpf on 2017/9/7.
 */
@Entity
@Table(name = "module")
public class ModuleEntity implements Serializable {
    private String mid;
    private String mname;
    private String murl;
    private String zid;
    private String icon;
    private String state; // 0 表示关闭 1 表示启用
    private String morder; // 顺序
    private String type;  //类别 0菜单  1按钮

    private List<ModuleEntity> modulelist;
    @Id
    @Column(name = "mid")
    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    @Basic
    @Column(name = "mname")
    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }
    @Basic
    @Column(name = "murl")
    public String getMurl() {
        return murl;
    }

    public void setMurl(String murl) {
        this.murl = murl;
    }



    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    @Basic
    @Column(name = "morder")
    public String getMorder() {
        return morder;
    }

    public void setMorder(String morder) {
        this.morder = morder;
    }
    @Basic
    @Column(name ="state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Basic
    @Column(name = "zid")
    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }


    @Transient
    public List<ModuleEntity> getModulelist() {
        return modulelist;
    }

    public void setModulelist(List<ModuleEntity> modulelist) {
        this.modulelist = modulelist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModuleEntity that = (ModuleEntity) o;

        if (mid != null ? !mid.equals(that.mid) : that.mid != null) return false;
        if (mname != null ? !mname.equals(that.mname) : that.mname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mid != null ? mid.hashCode() : 0;
        result = 31 * result + (mname != null ? mname.hashCode() : 0);
        return result;
    }
}
