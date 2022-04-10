package com.rkdev.dkr.crm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Person implements Serializable, Cloneable {

    @Id
    @GeneratedValue
    private Long id;

    private String vezeteknev = "";
    private String keresztnev = "";
    private String szuletesidatum = "";
    private String szuletesihely = "";
    private String neme = "";
    private String emailcim = "";
    private String telefonszam = "";
    private String webhely = "";
    private String iranyitoszam = "";
    private String telepules = "";
    private String cim = "";
    private String motivacioslevel = "";
    private String rogzitesdatuma;

    public Person() {

    }

    public Person(String vezeteknev, String keresztnev, String szuletesidatum, String szuletesihely, String neme, String emailcim, String telefonszam, String webhely, String iranyitoszam, String telepules, String cim, String motivacioslevel, String rogzitesdatuma) {
        this.vezeteknev = vezeteknev;
        this.keresztnev = keresztnev;
        this.szuletesidatum = szuletesidatum;
        this.szuletesihely = szuletesihely;
        this.neme = neme;
        this.emailcim = emailcim;
        this.telefonszam = telefonszam;
        this.webhely = webhely;
        this.iranyitoszam = iranyitoszam;
        this.telepules = telepules;
        this.cim = cim;
        this.motivacioslevel = motivacioslevel;
        this.rogzitesdatuma = rogzitesdatuma;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVezeteknev() {
        return vezeteknev;
    }

    public void setVezeteknev(String vezeteknev) {
        this.vezeteknev = vezeteknev;
    }

    public String getKeresztnev() {
        return keresztnev;
    }

    public void setKeresztnev(String keresztnev) {
        this.keresztnev = keresztnev;
    }

    public String getSzuletesidatum() {
        return szuletesidatum;
    }

    public void setSzuletesidatum(String szuletesidatum) {
        this.szuletesidatum = szuletesidatum;
    }

    public String getSzuletesihely() {
        return szuletesihely;
    }

    public void setSzuletesihely(String szuletesihely) {
        this.szuletesihely = szuletesihely;
    }

    public String getNeme() {
        return neme;
    }

    public void setNeme(String neme) {
        this.neme = neme;
    }

    public String getEmailcim() {
        return emailcim;
    }

    public void setEmailcim(String emailcim) {
        this.emailcim = emailcim;
    }

    public String getTelefonszam() {
        return telefonszam;
    }

    public void setTelefonszam(String telefonszam) {
        this.telefonszam = telefonszam;
    }

    public String getWebhely() {
        return webhely;
    }

    public void setWebhely(String webhely) {
        this.webhely = webhely;
    }

    public String getIranyitoszam() {
        return iranyitoszam;
    }

    public void setIranyitoszam(String iranyitoszam) {
        this.iranyitoszam = iranyitoszam;
    }

    public String getTelepules() {
        return telepules;
    }

    public void setTelepules(String telepules) {
        this.telepules = telepules;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getMotivacioslevel() {
        return motivacioslevel;
    }

    public void setMotivacioslevel(String motivacioslevel) {
        this.motivacioslevel = motivacioslevel;
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
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(vezeteknev, person.vezeteknev) &&
                Objects.equals(keresztnev, person.keresztnev) &&
                Objects.equals(szuletesidatum, person.szuletesidatum) &&
                Objects.equals(szuletesihely, person.szuletesihely) &&
                Objects.equals(neme, person.neme) &&
                Objects.equals(emailcim, person.emailcim) &&
                Objects.equals(telefonszam, person.telefonszam) &&
                Objects.equals(webhely, person.webhely) &&
                Objects.equals(iranyitoszam, person.iranyitoszam) &&
                Objects.equals(telepules, person.telepules) &&
                Objects.equals(cim, person.cim) &&
                Objects.equals(motivacioslevel, person.motivacioslevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vezeteknev, keresztnev, szuletesidatum, szuletesihely, neme, emailcim, telefonszam, webhely, iranyitoszam, telepules, cim, motivacioslevel);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", vezeteknev='" + vezeteknev + '\'' +
                ", keresztnev='" + keresztnev + '\'' +
                ", szuletesidatum='" + szuletesidatum + '\'' +
                ", szuletesihely='" + szuletesihely + '\'' +
                ", neme='" + neme + '\'' +
                ", emailcim='" + emailcim + '\'' +
                ", telefonszam='" + telefonszam + '\'' +
                '}';
    }
}

