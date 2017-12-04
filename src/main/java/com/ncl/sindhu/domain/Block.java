package com.ncl.sindhu.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Block.
 */
@Entity
@Table(name = "block")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Block implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "blockid", nullable = false)
    private String blockid;

    @Column(name = "blockdesc")
    private String blockdesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlockid() {
        return blockid;
    }

    public Block blockid(String blockid) {
        this.blockid = blockid;
        return this;
    }

    public void setBlockid(String blockid) {
        this.blockid = blockid;
    }

    public String getBlockdesc() {
        return blockdesc;
    }

    public Block blockdesc(String blockdesc) {
        this.blockdesc = blockdesc;
        return this;
    }

    public void setBlockdesc(String blockdesc) {
        this.blockdesc = blockdesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Block block = (Block) o;
        if (block.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), block.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Block{" +
            "id=" + getId() +
            ", blockid='" + getBlockid() + "'" +
            ", blockdesc='" + getBlockdesc() + "'" +
            "}";
    }
}
