package com.acepirit.ghumou.main.GhumouMain.Service;

import java.util.List;

import com.acepirit.ghumou.main.GhumouMain.Entity.AddToFavourites;
import com.acepirit.ghumou.main.GhumouMain.Entity.Packagess;
import com.acepirit.ghumou.main.GhumouMain.Entity.User;

public interface FavouriteService {
	
	public void save(AddToFavourites addtofavorite);

	public List<AddToFavourites> findAllByUserId(User user);
	
	public void deleteByPackageId(int packageid);
	

	public AddToFavourites findByUserIdAndPackageId(User user,Packagess packages);

	public void deleteById(int id);

}
