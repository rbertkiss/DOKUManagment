package com.rkdev.dkr.crm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Working implements Serializable, Cloneable{

    @Id
    @GeneratedValue
    private Long id;
    private Long ids;

    private String munkahelyneve="";
    private String beosztas="";
    private String osszefoglalas="";
    private String kezdeseve="";
    private String zaraseve="";
    private String rogzitesdatuma;

    public Working(){

    }

    public Working(Long ids, String munkahelyneve, String beosztas, String osszefoglalas, String kezdeseve, String zaraseve, String rogzitesdatuma) {
        this.ids = ids;
        this.munkahelyneve = munkahelyneve;
        this.beosztas = beosztas;
        this.osszefoglalas = osszefoglalas;
        this.kezdeseve = kezdeseve;
        this.zaraseve = zaraseve;
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

    public String getMunkahelyneve() {
        return munkahelyneve;
    }

    public void setMunkahelyneve(String munkahelyneve) {
        this.munkahelyneve = munkahelyneve;
    }

    public String getBeosztas() {
        return beosztas;
    }

    public void setBeosztas(String beosztas) {
        this.beosztas = beosztas;
    }

    public String getOsszefoglalas() {
        return osszefoglalas;
    }

    public void setOsszefoglalas(String osszefoglalas) {
        this.osszefoglalas = osszefoglalas;
    }

    public String getKezdeseve() {
        return kezdeseve;
    }

    public void setKezdeseve(String kezdeseve) {
        this.kezdeseve = kezdeseve;
    }

    public String getZaraseve() {
        return zaraseve;
    }

    public void setZaraseve(String zaraseve) {
        this.zaraseve = zaraseve;
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
        Working working = (Working) o;
        return Objects.equals(id, working.id) && Objects.equals(ids, working.ids) && Objects.equals(munkahelyneve, working.munkahelyneve) && Objects.equals(beosztas, working.beosztas) && Objects.equals(osszefoglalas, working.osszefoglalas) && Objects.equals(kezdeseve, working.kezdeseve) && Objects.equals(zaraseve, working.zaraseve) && Objects.equals(rogzitesdatuma, working.rogzitesdatuma);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ids, munkahelyneve, beosztas, osszefoglalas, kezdeseve, zaraseve, rogzitesdatuma);
    }

    @Override
    public String toString() {
        return "Working{" +
                "id=" + id +
                ", ids=" + ids +
                ", munkahelyneve='" + munkahelyneve + '\'' +
                ", beosztas='" + beosztas + '\'' +
                ", osszefoglalas='" + osszefoglalas + '\'' +
                ", kezdeseve='" + kezdeseve + '\'' +
                ", zaraseve='" + zaraseve + '\'' +
                ", rogzitesdatuma='" + rogzitesdatuma + '\'' +
                '}';
    }
}
