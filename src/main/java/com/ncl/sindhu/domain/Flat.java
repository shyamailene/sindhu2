package com.ncl.sindhu.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Flat.
 */
@Entity
@Table(name = "flat")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Flat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "flatid", nullable = false)
    private Integer flatid;

    @Column(name = "flatdesc")
    private String flatdesc;

    @ManyToOne(optional = false)
    @NotNull
    private Block blockflat_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFlatid() {
        return flatid;
    }

    public Flat flatid(Integer flatid) {
        this.flatid = flatid;
        return this;
    }

    public void setFlatid(Integer flatid) {
        this.flatid = flatid;
    }

    public String getFlatdesc() {
        return flatdesc;
    }

    public Flat flatdesc(String flatdesc) {
        this.flatdesc = flatdesc;
        return this;
    }

    public void setFlatdesc(String flatdesc) {
        this.flatdesc = flatdesc;
    }

    public Block getBlockflat_id() {
        return blockflat_id;
    }

    public Flat blockflat_id(Block block) {
        this.blockflat_id = block;
        return this;
    }

    public void setBlockflat_id(Block block) {
        this.blockflat_id = block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Flat flat = (Flat) o;
        if (flat.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), flat.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Flat{" +
            "id=" + getId() +
            ", flatid='" + getFlatid() + "'" +
            ", flatdesc='" + getFlatdesc() + "'" +
            "}";
    }
}
