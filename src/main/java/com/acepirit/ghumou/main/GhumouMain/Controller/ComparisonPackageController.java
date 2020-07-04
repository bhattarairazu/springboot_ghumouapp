package com.acepirit.ghumou.main.GhumouMain.Controller;

import com.acepirit.ghumou.main.GhumouMain.Entity.ComparisonGet;
import com.acepirit.ghumou.main.GhumouMain.Entity.ComparisonPackage;
import com.acepirit.ghumou.main.GhumouMain.Entity.Packagess;
import com.acepirit.ghumou.main.GhumouMain.Entity.User;
import com.acepirit.ghumou.main.GhumouMain.Service.ComparisonPackageService;
import com.acepirit.ghumou.main.GhumouMain.Service.GlobalResponseService;
import com.acepirit.ghumou.main.GhumouMain.Service.PackageService;
import com.acepirit.ghumou.main.GhumouMain.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v2/")
public class ComparisonPackageController {
    @Autowired
    private UserService userService;

    @Autowired
    private GlobalResponseService globalResponseService;

    @Autowired
    private PackageService packageService;

    @Autowired
    private ComparisonPackageService comparisonPackageService;

    @PostMapping("/comparison")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> postComparison(@RequestBody ComparisonGet comparison){
        ComparisonPackage compare  = new ComparisonPackage();
        User user = userService.findById(comparison.getUserId());
        Packagess packagess = packageService.findById(comparison.getPackageId());

        compare.setPackagess(packagess);
        compare.setUser(user);

        comparisonPackageService.save(compare);
        return globalResponseService.responseClient(compare);
    }

    @GetMapping(value="/comparison",params = {"user_id"})
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getComparison(@RequestParam("user_id") int user_id){
        List<ComparisonPackage> allusercomparison = comparisonPackageService.findByUser(userService.findById(user_id));
        return globalResponseService.listComparision(allusercomparison);

    }
    @DeleteMapping("/comparison/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteComparison(@PathVariable int id){
        comparisonPackageService.deleteById(id);
        return globalResponseService.globalResponse("Success", HttpStatus.OK.value());
    }

    @GetMapping("/comparison/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getComparisionById(@PathVariable int id){
       ComparisonPackage compare = comparisonPackageService.findById(id);
        return globalResponseService.responseClient(compare);
    }


}
