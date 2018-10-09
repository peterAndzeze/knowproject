package com.sw.project.teamshrio.orginfo;

import java.util.Date;

public class OrgInfoModel {
    private Long id;

    private String orgcode;

    private String orgname;

    private String orgdesc;

    private String state;

    private Long parentId;

    private Long createId;

    private Date createTime;

    private Long operaId;

    private Date operaTime;

    private Long version;
    private String leaf;

    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode == null ? null : orgcode.trim();
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

    public String getOrgdesc() {
        return orgdesc;
    }

    public void setOrgdesc(String orgdesc) {
        this.orgdesc = orgdesc == null ? null : orgdesc.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getOperaId() {
        return operaId;
    }

    public void setOperaId(Long operaId) {
        this.operaId = operaId;
    }

    public Date getOperaTime() {
        return operaTime;
    }

    public void setOperaTime(Date operaTime) {
        this.operaTime = operaTime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}