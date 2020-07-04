package com.acepirit.ghumou.main.GhumouMain.Service;

import com.acepirit.ghumou.main.GhumouMain.Entity.Review;
import com.acepirit.ghumou.main.GhumouMain.Entity.User;

import java.util.List;

public interface ReviewService {
    void save(Review review);

    void deleteById(int id);

    List<Review> findAll();

    List<Review> findAllByPackageId(int packageid);

    Review findById(int id);
    void calculateAverageRating(int packageid);

    List<Review> findByUser(User user);
}
