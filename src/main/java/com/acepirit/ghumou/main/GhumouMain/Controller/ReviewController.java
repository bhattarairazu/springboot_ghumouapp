package com.acepirit.ghumou.main.GhumouMain.Controller;

import com.acepirit.ghumou.main.GhumouMain.Entity.Review;
import com.acepirit.ghumou.main.GhumouMain.Entity.ReviewGet;
import com.acepirit.ghumou.main.GhumouMain.Entity.User;
import com.acepirit.ghumou.main.GhumouMain.Response.GlobalResponse;
import com.acepirit.ghumou.main.GhumouMain.Service.GlobalResponseService;
import com.acepirit.ghumou.main.GhumouMain.Service.PackageService;
import com.acepirit.ghumou.main.GhumouMain.Service.ReviewService;
import com.acepirit.ghumou.main.GhumouMain.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v2/")
public class ReviewController {

    @Autowired
    private UserService userService;

    @Autowired
    private PackageService packageService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private GlobalResponseService globalResponse;

    @PostMapping("/addreview")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addReview(@RequestBody ReviewGet review){
        User singleUser = userService.findById(review.getUserId());

        Review rev = new Review();
        rev.setCreatedAt(new Date(System.currentTimeMillis()));
        rev.setComment(review.getComment());
        rev.setPackageId(review.getPackageId());
        rev.setRating(review.getRating());
        rev.setUser(singleUser);

        reviewService.save(rev);
        //calculating average rating of all reivews of respective package id
        reviewService.calculateAverageRating(review.getPackageId());

        return globalResponse.responseClient(rev);

    }
    @GetMapping(value="/allreviews",params = {"packageid"})
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> allReview(@RequestParam("packageid") int packageid){
        System.out.println("package "+packageid);
        List<Review> packageReview = reviewService.findAllByPackageId(packageid);
        System.out.println(packageReview);
        return globalResponse.listReview(packageReview);

    }

    @PostMapping("/reviews/edit/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> editReview(@PathVariable int id,@RequestBody ReviewGet review){
        Review singlereview = reviewService.findById(id);
        singlereview.setUser(userService.findById(review.getUserId()));
        singlereview.setPackageId(review.getPackageId());
        singlereview.setRating(review.getRating());
        singlereview.setComment(review.getComment());
        reviewService.save(singlereview);
        //calculating avg rating
        reviewService.calculateAverageRating(review.getPackageId());

        return globalResponse.responseClient(singlereview);
    }

    @DeleteMapping("/reviews/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteReview(@PathVariable int id){
        reviewService.deleteById(id);
        return globalResponse.globalResponse("Success", HttpStatus.OK.value());
    }

}
