package com.mmg.model;

import java.io.Serializable;

public abstract class BaseObject implements Serializable{
	private static final long serialVersionUID = -3658698824540003392L;
	public abstract Serializable realId();
	
	@Override
	public int hashCode() {
		return (realId() == null) ? 0 : realId().hashCode();
	}
	
	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		BaseObject other = (BaseObject) obj;
		return !(this.realId() != null ? !(this.realId().equals(other.realId())) : (other.realId() != null));
	}
}
