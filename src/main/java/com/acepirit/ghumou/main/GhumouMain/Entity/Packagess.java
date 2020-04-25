package com.acepirit.ghumou.main.GhumouMain.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="package_id",referencedColumnName = "package_id")
	private List<Inclusions> inclusions;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="package_id",referencedColumnName = "package_id")
	private List<Exclusions> exclusions;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="package_id",referencedColumnName = "package_id")
	private List<Itenarys> itenarys;

	public Packagess() {}
	public Packagess(Date createdAt, String packageTitle, String packageDescription, int offers, int regularPrice,
			int salePrice, String duration, String packageSellar, String thumnail, String packageType, int rating,
			int views, List<Images> images, List<Inclusions> inclusions, List<Exclusions> exclusions,
			List<Itenarys> itenarys) {
		this.createdAt = createdAt;
		this.packageTitle = packageTitle;
		this.packageDescription = packageDescription;
		this.offers = offers;
		this.regularPrice = regularPrice;
		this.salePrice = salePrice;
		this.duration = duration;
		this.packageSellar = packageSellar;
		this.thumnail = thumnail;
		this.packageType = packageType;
		this.rating = rating;
		this.views = views;
		this.images = images;
		this.inclusions = inclusions;
		this.exclusions = exclusions;
		this.itenarys = itenarys;
	}

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

	public List<Inclusions> getInclusions() {
		return inclusions;
	}

	public void setInclusions(List<Inclusions> inclusions) {
		this.inclusions = inclusions;
	}

	public List<Exclusions> getExclusions() {
		return exclusions;
	}

	public void setExclusions(List<Exclusions> exclusions) {
		this.exclusions = exclusions;
	}

	public List<Itenarys> getItenarys() {
		return itenarys;
	}

	public void setItenarys(List<Itenarys> itenarys) {
		this.itenarys = itenarys;
	}
	
	

}
