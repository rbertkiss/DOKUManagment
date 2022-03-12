package com.rkdev.dkr.crm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Skills implements Serializable, Cloneable{

    @Id
    @GeneratedValue
    private Long id;
    private Long ids;

    private String megnevezes="";
    private String tudasszint="";
    private String rogzitesdatuma;

    public Skills(){

    }

    public Skills(Long ids, String megnevezes, String tudasszint, String rogzitesdatuma) {
        this.ids = ids;
        this.megnevezes = megnevezes;
        this.tudasszint = tudasszint;
        this.rogzitesdatuma = rogzitesdatuma;
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

    public String getMegnevezes() {
        return megnevezes;
    }

    public void setMegnevezes(String megnevezes) {
        this.megnevezes = megnevezes;
    }

    public String getTudasszint() {
        return tudasszint;
    }

    public void setTudasszint(String tudasszint) {
        this.tudasszint = tudasszint;
    }

    public String getRogzitesdatuma() {
        return rogzitesdatuma;
    }

    public void setRogzitesdatuma(String rogzitesdatuma) {
        this.rogzitesdatuma = rogzitesdatuma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skills skills = (Skills) o;
        return Objects.equals(id, skills.id) && Objects.equals(ids, skills.ids) && Objects.equals(megnevezes, skills.megnevezes) && Objects.equals(tudasszint, skills.tudasszint) && Objects.equals(rogzitesdatuma, skills.rogzitesdatuma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ids, megnevezes, tudasszint, rogzitesdatuma);
    }

    @Override
    public String toString() {
        return "Skills{" +
                "id=" + id +
                ", ids=" + ids +
                ", megnevezes='" + megnevezes + '\'' +
                ", tudasszint='" + tudasszint + '\'' +
                ", rogzitesdatuma='" + rogzitesdatuma + '\'' +
                '}';
    }
}
