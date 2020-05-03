package com.acepirit.ghumou.main.GhumouMain.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="packages")
public class Packagess {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="package_id")
	private int id;
	
	@Column(name="created_at")
	private Date createdAt;
	
	@Column(name="package_title")
	private String packageTitle;
	
	@Column(name="package_descriptoin")
	private String packageDescription;
	
	@Column(name="offers")
	private int offers;
	
	@Column(name="regular_price")
	private int regularPrice;
	
	@Column(name="sale_price")
	private int salePrice;
	
	@Column(name="duration")
	private String duration;
	
	@Column(name="package_sellar")
	private String packageSellar;
	
	@Column(name="thumnail")
	private String thumnail;
	
	@Column(name="package_type")
	private String packageType;
	
	@Column(name="rating")
	private int rating;
	
	@Column(name="views")
	private int views;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="package_id",referencedColumnName = "package_id")
	private List<Images> images;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="package_id",referencedColumnName = "package_id")
	private Inclusions inclusions;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="package_id",referencedColumnName = "package_id")
	private Exclusions exclusions;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="package_id",referencedColumnName = "package_id")
	private Itenarys itenarys;

	public Packagess() {}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getPackageTitle() {
		return packageTitle;
	}

	public void setPackageTitle(String packageTitle) {
		this.packageTitle = packageTitle;
	}

	public String getPackageDescription() {
		return packageDescription;
	}

	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}

	public int getOffers() {
		return offers;
	}

	public void setOffers(int offers) {
		this.offers = offers;
	}

	public int getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(int regularPrice) {
		this.regularPrice = regularPrice;
	}

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getPackageSellar() {
		return packageSellar;
	}

	public void setPackageSellar(String packageSellar) {
		this.packageSellar = packageSellar;
	}

	public String getThumnail() {
		return thumnail;
	}

	public void setThumnail(String thumnail) {
		this.thumnail = thumnail;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
	}

	public Inclusions getInclusions() {
		return inclusions;
	}

	public void setInclusions(Inclusions inclusions) {
		this.inclusions = inclusions;
	}

	public Exclusions getExclusions() {
		return exclusions;
	}

	public void setExclusions(Exclusions exclusions) {
		this.exclusions = exclusions;
	}

	public Itenarys getItenarys() {
		return itenarys;
	}

	public void setItenarys(Itenarys itenarys) {
		this.itenarys = itenarys;
	}
}
