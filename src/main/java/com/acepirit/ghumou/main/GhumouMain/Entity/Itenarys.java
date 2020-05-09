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
	

	public Itenarys() {}

	public Itenarys(String itenary) {
		this.itenary = itenary;
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
