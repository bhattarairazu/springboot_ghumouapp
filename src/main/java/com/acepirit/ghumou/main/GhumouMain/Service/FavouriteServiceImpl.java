package com.acepirit.ghumou.main.GhumouMain.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acepirit.ghumou.main.GhumouMain.Entity.AddToFavourites;
import com.acepirit.ghumou.main.GhumouMain.Entity.Packagess;
import com.acepirit.ghumou.main.GhumouMain.Entity.User;
import com.acepirit.ghumou.main.GhumouMain.Repository.FavouriteRepository;

@Service
public class FavouriteServiceImpl implements FavouriteService{

	@Autowired
	private FavouriteRepository favouriteRepository;
	@Override
	public List<AddToFavourites> findAllByUserId(User user) {
		List<AddToFavourites> allFavouresi = favouriteRepository.findAllByUserId(user);
		return allFavouresi;
	}

	@Override
	public void deleteByPackageId(int packageid) {
		favouriteRepository.deleteById(packageid);
		
	}

	@Override
	public void save(AddToFavourites addtofavorite) {
		favouriteRepository.save(addtofavorite);
		
	}

	@Override
	public AddToFavourites findByUserIdAndPackageId(User user_id, Packagess package_id) {
		AddToFavourites fav = favouriteRepository.findByUserIdAndPackageId(user_id, package_id);
		if(fav!=null) {
			return fav;
		}else {
			throw new RuntimeException("Favoureit with user id "+user_id+" and package id "+package_id+" Not found");
		}
		
	}

	@Override
	public void deleteById(int id) {
		favouriteRepository.deleteById(id);
	}

	

}
