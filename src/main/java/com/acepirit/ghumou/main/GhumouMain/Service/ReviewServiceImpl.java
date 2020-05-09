package com.acepirit.ghumou.main.GhumouMain.Service;

import com.acepirit.ghumou.main.GhumouMain.Entity.Packagess;
import com.acepirit.ghumou.main.GhumouMain.Entity.Review;
import com.acepirit.ghumou.main.GhumouMain.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PackageService packageService;

    @Override
    public void save(Review review) {
        reviewRepository.save(review);

    }

    @Override
    public void deleteById(int id) {

        reviewRepository.deleteById(id);

    }

    @Override
    public List<Review> findAll() {
        List<Review> allReview = reviewRepository.findAll();
        return allReview;
    }

    @Override
    public List<Review> findAllByPackageId(int packageid) {
        List<Review> allReviewByPackage = reviewRepository.findAllByPackageId(packageid);
        return allReviewByPackage;
    }

    @Override
    public Review findById(int id) {
        Optional<Review> result = reviewRepository.findById(id);
        Review review = null;
        if(result!=null){
            review = result.get();
        }else{
            throw new RuntimeException("Review with id "+id+" Not Found");
        }
        return review;
    }
    @Override
    public void calculateAverageRating(int packageid){
        Packagess forRatingPackage = packageService.findById(packageid);
        List<Review> allReviewByPackage = reviewRepository.findAllByPackageId(packageid);
        double avg = 0.0;
        for(int i = 0;i<allReviewByPackage.size();i++){
            avg = avg + allReviewByPackage.get(i).getRating();
        }

       forRatingPackage.setRating(avg/allReviewByPackage.size());
        System.out.println("avg rating "+avg/allReviewByPackage.size());
        packageService.save(forRatingPackage);
    }

}
