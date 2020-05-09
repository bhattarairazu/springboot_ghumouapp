package com.acepirit.ghumou.main.GhumouMain.Repository;

import com.acepirit.ghumou.main.GhumouMain.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    @Query("SELECT r FROM Review r WHERE r.packageId=:packageid")
    List<Review> findAllByPackageId(@Param("packageid") int packageid);

}
