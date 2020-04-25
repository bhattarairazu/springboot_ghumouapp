package com.acepirit.ghumou.main.GhumouMain.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="itenarys")
public class Itenarys {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name="itenary")
	private String itenary;
	
	@Column(name="package_id")
	private int packageId;
	
	public Itenarys() {}

	public Itenarys(String itenary) {
		this.itenary = itenary;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItenary() {
		return itenary;
	}

	public void setItenary(String itenary) {
		this.itenary = itenary;
	}
	
	
}
