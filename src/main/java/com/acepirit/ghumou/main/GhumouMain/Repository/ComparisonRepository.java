package com.acepirit.ghumou.main.GhumouMain.Repository;

import com.acepirit.ghumou.main.GhumouMain.Entity.ComparisonPackage;
import com.acepirit.ghumou.main.GhumouMain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComparisonRepository extends JpaRepository<ComparisonPackage,Integer> {
    List<ComparisonPackage> findByUser(User user);

}
