package com.jm.springboottemplate.common.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: TestTable, change description here.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-02-07 16:30
 **/
public class TestTable implements Serializable {
    private static final long serialVersionUID = -4529532906632853380L;
    private Integer id;
    private String content;
    private Date createdTime;
    private Integer createdPerson;
    private Date modifiedTime;
    private Integer modifiedPerson;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getCreatedPerson() {
        return createdPerson;
    }

    public void setCreatedPerson(Integer createdPerson) {
        this.createdPerson = createdPerson;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getModifiedPerson() {
        return modifiedPerson;
    }

    public void setModifiedPerson(Integer modifiedPerson) {
        this.modifiedPerson = modifiedPerson;
    }

    @Override
    public String toString() {
        return "TestTable{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createdTime=" + createdTime +
                ", createdPerson=" + createdPerson +
                ", modifiedTime=" + modifiedTime +
                ", modifiedPerson=" + modifiedPerson +
                '}';
    }
}

