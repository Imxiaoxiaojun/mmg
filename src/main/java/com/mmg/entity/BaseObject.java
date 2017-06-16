package com.mmg.entity;

import com.mmg.common.Cache;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
@MappedSuperclass
public abstract class BaseObject extends Cache implements Serializable {
    private static final long serialVersionUID = 4567041038888876723L;

    public abstract Serializable realId();

    @Override
    public int hashCode() {
        return (realId() == null) ? 0 : realId().hashCode();
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (getClass() != obj.getClass())
            return false;
        BaseObject other = (BaseObject) obj;
        return !(this.realId() != null ? !(this.realId().equals(other.realId())) : (other.realId() != null));
    }
}
