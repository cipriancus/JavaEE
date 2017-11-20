package com.java.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Preferences")
public class Preference implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private PreferenceCompoundPk id;

	@Column(name = "NUMNBER_PREF")
	public int noPref;

	public PreferenceCompoundPk getId() {
		return id;
	}

	public void setId(PreferenceCompoundPk id) {
		this.id = id;
	}

	public int getNoPref() {
		return noPref;
	}

	public void setNoPref(int noPref) {
		this.noPref = noPref;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + noPref;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Preference other = (Preference) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (noPref != other.noPref)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Preference [id=" + id + ", noPref=" + noPref + "]";
	}

}
