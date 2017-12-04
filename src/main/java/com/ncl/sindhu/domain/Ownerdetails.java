package com.ncl.sindhu.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Ownerdetails.
 */
@Entity
@Table(name = "ownerdetails")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ownerdetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "carlocation")
    private String carlocation;

    @Column(name = "intercom")
    private String intercom;

    @Column(name = "carnumber")
    private String carnumber;

    @Column(name = "bikenumber")
    private String bikenumber;

    @ManyToOne(optional = false)
    @NotNull
    private Flat flatid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Ownerdetails name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public Ownerdetails mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCarlocation() {
        return carlocation;
    }

    public Ownerdetails carlocation(String carlocation) {
        this.carlocation = carlocation;
        return this;
    }

    public void setCarlocation(String carlocation) {
        this.carlocation = carlocation;
    }

    public String getIntercom() {
        return intercom;
    }

    public Ownerdetails intercom(String intercom) {
        this.intercom = intercom;
        return this;
    }

    public void setIntercom(String intercom) {
        this.intercom = intercom;
    }

    public String getCarnumber() {
        return carnumber;
    }

    public Ownerdetails carnumber(String carnumber) {
        this.carnumber = carnumber;
        return this;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public String getBikenumber() {
        return bikenumber;
    }

    public Ownerdetails bikenumber(String bikenumber) {
        this.bikenumber = bikenumber;
        return this;
    }

    public void setBikenumber(String bikenumber) {
        this.bikenumber = bikenumber;
    }

    public Flat getFlatid() {
        return flatid;
    }

    public Ownerdetails flatid(Flat flat) {
        this.flatid = flat;
        return this;
    }

    public void setFlatid(Flat flat) {
        this.flatid = flat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ownerdetails ownerdetails = (Ownerdetails) o;
        if (ownerdetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ownerdetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ownerdetails{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", carlocation='" + getCarlocation() + "'" +
            ", intercom='" + getIntercom() + "'" +
            ", carnumber='" + getCarnumber() + "'" +
            ", bikenumber='" + getBikenumber() + "'" +
            "}";
    }
}
