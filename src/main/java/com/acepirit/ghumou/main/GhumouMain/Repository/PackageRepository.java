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

	//search by oorder by and ordering type filter
	@Query("SELECT p FROM Packagess p WHERE p.packageTitle LIKE %:keyword% OR p.packageSellar LIKE %:keyword% OR p.packageType LIKE %:keyword% ORDER BY :ordering DESC")
	List<Packagess> findPackagessByKeywordWithOrder(@Param("keyword") String keyword,@Param("ordering") String ordering);

	//similar packages data
	@Query(value = "SELECT * from packages WHERE package_title LIKE %:packagename% ORDER BY views DESC LIMIT 5",nativeQuery = true)
	List<Packagess> findSimilarPacageks(@Param("packagename") String packagename);

	//ordering packages by rating only
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
