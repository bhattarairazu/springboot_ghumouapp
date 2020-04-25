package com.acepirit.ghumou.main.GhumouMain.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acepirit.ghumou.main.GhumouMain.Entity.Packagess;


public interface PackageService {
	
	public void save(Packagess pack);
	
	public void deleteById(int id);
	
	public List<Packagess> findAll();
	
	public Packagess findById(int id);
	
	public List<Packagess> findByPackageType(String type);
	
	public List<Packagess> findByPackageSellar(String sellar);
	
	public List<Packagess> findPackagesByKeyword(String keyword);

	public List<Packagess> findByRating(int rating);
	
	public List<Packagess> findByOrderByViewsDesc();
	

	public List<Packagess> findByOrderByRatingDesc();
	
	public List<Packagess> findByOrderByViewsAsc();
	

	public List<Packagess> findByOrderByRatingAsc();
	
	public List<Packagess> findByOrderByCreatedAtAsc();
	

	public List<Packagess> findByOrderByCreatedAtDesc();

}
