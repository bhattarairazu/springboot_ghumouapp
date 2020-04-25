package com.acepirit.ghumou.main.GhumouMain.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="addto_favourite")
public class AddToFavourites {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name="user_id",referencedColumnName = "id")
	private User userId;
	
	@OneToOne
	@JoinColumn(name="package_id",referencedColumnName = "package_id")
	private Packagess packageId;
	
	public AddToFavourites() {}

	public AddToFavourites(User userId, Packagess packageId) {
		this.userId = userId;
		this.packageId = packageId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Packagess getPackageId() {
		return packageId;
	}

	public void setPackageId(Packagess packageId) {
		this.packageId = packageId;
	}
	
	
	
	
	

}
