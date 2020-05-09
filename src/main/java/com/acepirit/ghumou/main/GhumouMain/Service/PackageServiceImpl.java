package com.acepirit.ghumou.main.GhumouMain.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acepirit.ghumou.main.GhumouMain.Entity.Packagess;
import com.acepirit.ghumou.main.GhumouMain.Repository.PackageRepository;

@Service
public class PackageServiceImpl implements PackageService {

	@Autowired
	private PackageRepository packageRepository;
	
	@Override
	public void save(Packagess pack) {
		packageRepository.save(pack);

		
	}

	@Override
	public void deleteById(int id) {
	
		packageRepository.deleteById(id);
		
	}

	@Override
	public List<Packagess> findAll() {
		List<Packagess> allPackages = packageRepository.findAll();
		return allPackages;
	}

	@Override
	public Packagess findById(int id) {
		Optional<Packagess> result = packageRepository.findById(id);
		Packagess packages = null;
		if(result.isPresent()) {
			packages = result.get();
		}else {
			throw new RuntimeException("Package with id "+id+" Not found");
		}
		return packages;
	}

	@Override
	public List<Packagess> findByPackageType(String type) {
		List<Packagess> allTypePackages = packageRepository.findByPackageType(type);
		return allTypePackages;
	}

	@Override
	public List<Packagess> findByPackageSellar(String sellar) {
		List<Packagess> allSellarPackage = packageRepository.findByPackageSellar(sellar);
		return allSellarPackage;
	}

	@Override
	public List<Packagess> findPackagesByKeyword(String keyword) {
		List<Packagess> allkeywordPackage = packageRepository.findPackagesByKeyword(keyword);
		return allkeywordPackage;
	}

	@Override
	public List<Packagess> findByRating(int rating) {
		List<Packagess> allratingPackage = packageRepository.findByRating(rating);
		return allratingPackage;

	}
	//most viewd packages
	@Override
	public List<Packagess> findByOrderByViewsDesc() {
		List<Packagess> allpopularpackage = packageRepository.findByOrderByViewsDesc();
		return allpopularpackage;
	}
	//most popular packages list

	@Override
	public List<Packagess> findByOrderByRatingDesc() {
		List<Packagess> allPopularPackage = packageRepository.findByOrderByRatingDesc();
		return allPopularPackage;
	}

	@Override
	public List<Packagess> findByOrderByViewsAsc() {
		List<Packagess> allleastViewPackage = packageRepository.findByOrderByViewsAsc();
		return allleastViewPackage;
	}

	@Override
	public List<Packagess> findByOrderByRatingAsc() {
		List<Packagess> allleastPopularPackage = packageRepository.findByOrderByRatingAsc();
		return allleastPopularPackage;
	}

	@Override
	public List<Packagess> findByOrderByCreatedAtAsc() {
		List<Packagess> oldpackages = packageRepository.findByOrderByCreatedAtAsc();
		return oldpackages;
	}

	@Override
	public List<Packagess> findByOrderByCreatedAtDesc() {
		List<Packagess> recentlyadded = packageRepository.findByOrderByCreatedAtDesc();
		return recentlyadded;
	}

	@Override
	public List<Packagess> findPackagessByKeywordWithOrder(String keyword, String ordering) {
		List<Packagess> allPakcagesOrdered = packageRepository.findPackagessByKeywordWithOrder(keyword,ordering);
		return allPakcagesOrdered;
	}

	///for similar packages
	@Override
	public List<Packagess> findPackagesBySearchWithRecent(String packageName) {
		List<Packagess> similarPackage = packageRepository.findSimilarPacageks(packageName);
		return similarPackage;
	}


}
