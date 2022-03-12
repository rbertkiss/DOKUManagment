package com.rkdev.dkr.crm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Language implements Serializable, Cloneable{

    @Id
    @GeneratedValue
    private Long id;
    private Long ids;
    private String nyelv="";
    private String nyelviszint ="";

    public Language(Long ids, String nyelv, String nyelviszint) {
        this.ids = ids;
        this.nyelv = nyelv;
        this.nyelviszint = nyelviszint;
    }

    public Language() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIds() {
        return ids;
    }

    public void setIds(Long ids) {
        this.ids = ids;
    }

    public String getNyelv() {
        return nyelv;
    }

    public void setNyelv(String nyelv) {
        this.nyelv = nyelv;
    }

    public String getNyelviszint() {
        return nyelviszint;
    }

    public void setNyelviszint(String nyelviszint) {
        this.nyelviszint = nyelviszint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return  Objects.equals(id, language.id) &&
                Objects.equals(ids, language.ids) &&
                Objects.equals(nyelv, language.nyelv) &&
                Objects.equals(nyelviszint, language.nyelviszint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ids, nyelv, nyelviszint);
    }

    @Override
    public String toString() {
        return "Language{" +
                "ids=" + ids +
                ", id=" + id +
                ", nyelv='" + nyelv + '\'' +
                ", nyelvi_szint='" + nyelviszint + '\'' +
                '}';
    }
}
