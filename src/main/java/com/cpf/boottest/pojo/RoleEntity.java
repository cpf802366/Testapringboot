package com.cpf.boottest.pojo;

import javax.persistence.*;
import java.util.List;

/**
 * Created by cpf on 2017/9/7.
 */
@Entity
@Table(name = "role")
public class RoleEntity {
    private String rid;
    private String  rname;
    private String  functions;
    private String  zid;

    @Id
    @Column(name = "rid")
    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    @Basic
    @Column(name = "rname")
    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    @Basic
    @Column(name = "functions")
    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }
    @Basic
    @Column(name = "zid")
    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (rid != null ? !rid.equals(that.rid) : that.rid != null) return false;
        if (rname != null ? !rname.equals(that.rname) : that.rname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rid != null ? rid.hashCode() : 0;
        result = 31 * result + (rname != null ? rname.hashCode() : 0);
        return result;
    }
}
