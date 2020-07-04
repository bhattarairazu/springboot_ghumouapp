package com.acepirit.ghumou.main.GhumouMain.Service;

import com.acepirit.ghumou.main.GhumouMain.Entity.ComparisonPackage;
import com.acepirit.ghumou.main.GhumouMain.Entity.User;

import java.util.List;

public interface ComparisonPackageService {

    void save(ComparisonPackage comapresionPackage);

    List<ComparisonPackage> findAll();

    void deleteById(int id);

    ComparisonPackage findById(int id);

    List<ComparisonPackage> findByUser(User user);

}
