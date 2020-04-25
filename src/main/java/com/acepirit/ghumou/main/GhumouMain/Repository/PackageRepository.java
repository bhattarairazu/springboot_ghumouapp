package com.acepirit.ghumou.main.GhumouMain.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.acepirit.ghumou.main.GhumouMain.Entity.Packagess;

@Repository
public interface PackageRepository extends JpaRepository<Packagess, Integer>{
	
	public List<Packagess> findByPackageType(String type);
	
	public List<Packagess> findByPackageSellar(String sellar);
	
	//custom query for searchr
	@Query("SELECT pr FROM Packagess pr WHERE pr.packageTitle LIKE %:keyword% OR pr.packageDescription LIKE %:keyword% OR pr.packageSellar LIKE %:keyword% OR pr.packageType LIKE %:keyword% OR pr.duration LIKE %:keyword%")
	public List<Packagess> findPackagesByKeyword(@Param("keyword") String keyword);

	public List<Packagess> findByRating(int rating);
	
	//ordering according to most view packages
	public List<Packagess> findByOrderByViewsDesc();

	//ordering according to popular packages
	public List<Packagess> findByOrderByRatingDesc();
	
	//least view
	public List<Packagess> findByOrderByViewsAsc();
	
	//least rating
	public List<Packagess> findByOrderByRatingAsc();
	

	public List<Packagess> findByOrderByCreatedAtAsc();
	

	public List<Packagess> findByOrderByCreatedAtDesc();
}
