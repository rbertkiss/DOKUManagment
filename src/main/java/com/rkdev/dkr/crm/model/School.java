package com.rkdev.dkr.crm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class School implements Serializable, Cloneable {

    @Id
    @GeneratedValue
    private Long id;
    private Long ids;
    private String iskolaneve="";
    private String kepzetseg="";
    private String szakirany="";
    private String kezdeseve="";
    private String vegzeseve="";
    private String rogzitesdatuma;

    public School(){

    }

    public School(Long ids, String iskolaneve, String kepzetseg, String szakirany, String kezdeseve, String vegzeseve, String rogzitesdatuma) {
        this.ids = ids;
        this.iskolaneve = iskolaneve;
        this.kepzetseg = kepzetseg;
        this.szakirany = szakirany;
        this.kezdeseve = kezdeseve;
        this.vegzeseve = vegzeseve;
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

    public String getIskolaneve() {
        return iskolaneve;
    }

    public void setIskolaneve(String iskolaneve) {
        this.iskolaneve = iskolaneve;
    }

    public String getKepzetseg() {
        return kepzetseg;
    }

    public void setKepzetseg(String kepzetseg) {
        this.kepzetseg = kepzetseg;
    }

    public String getSzakirany() {
        return szakirany;
    }

    public void setSzakirany(String szakirany) {
        this.szakirany = szakirany;
    }

    public String getKezdeseve() {
        return kezdeseve;
    }

    public void setKezdeseve(String kezdeseve) {
        this.kezdeseve = kezdeseve;
    }

    public String getVegzeseve() {
        return vegzeseve;
    }

    public void setVegzeseve(String vegzeseve) {
        this.vegzeseve = vegzeseve;
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
        School school = (School) o;
        return Objects.equals(id, school.id) && Objects.equals(ids, school.ids) && Objects.equals(iskolaneve, school.iskolaneve) && Objects.equals(kepzetseg, school.kepzetseg) && Objects.equals(szakirany, school.szakirany) && Objects.equals(kezdeseve, school.kezdeseve) && Objects.equals(vegzeseve, school.vegzeseve) && Objects.equals(rogzitesdatuma, school.rogzitesdatuma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ids, iskolaneve, kepzetseg, szakirany, kezdeseve, vegzeseve, rogzitesdatuma);
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", ids=" + ids +
                ", iskolaneve='" + iskolaneve + '\'' +
                ", kepzetseg='" + kepzetseg + '\'' +
                ", szakirany='" + szakirany + '\'' +
                ", kezdeseve='" + kezdeseve + '\'' +
                ", vegzeseve='" + vegzeseve + '\'' +
                ", rogzitesdatuma='" + rogzitesdatuma + '\'' +
                '}';
    }
}
