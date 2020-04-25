package com.acepirit.ghumou.main.GhumouMain.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.acepirit.ghumou.main.GhumouMain.Entity.AddToFavourites;
import com.acepirit.ghumou.main.GhumouMain.Entity.Packagess;
import com.acepirit.ghumou.main.GhumouMain.Entity.User;

public interface FavouriteRepository extends JpaRepository<AddToFavourites, Integer>{
	
	public List<AddToFavourites> findAllByUserId(User user);
	
	public void deleteByPackageId(int packageid);
	
	//@Query("SELECT f FROM AddToFavourites f WHERE f.userId =:user AND f.packageId=:packages")
	public AddToFavourites findByUserIdAndPackageId(User user,Packagess packages);
	
	
}
