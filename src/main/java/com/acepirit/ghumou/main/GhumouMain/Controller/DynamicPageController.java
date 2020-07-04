package com.acepirit.ghumou.main.GhumouMain.Controller;

import com.acepirit.ghumou.main.GhumouMain.Entity.DynamicPages;
import com.acepirit.ghumou.main.GhumouMain.Service.DynamicPageService;
import com.acepirit.ghumou.main.GhumouMain.Service.GlobalResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/")
public class DynamicPageController {

    @Autowired
    private DynamicPageService dynamicPageService;

    @Autowired
    private GlobalResponseService globalResponseService;

    @GetMapping("/dynamicpages")
    public ResponseEntity<?> getallDynamicPages(){
        List<DynamicPages> allPages = dynamicPageService.findAll();
        return globalResponseService.listDynamicPages(allPages);
    }

    @GetMapping("/dynamicpages/{page_slug}")
    public ResponseEntity<?> getPageBySlug(@PathVariable String page_slug){
        DynamicPages singlePage = dynamicPageService.findByPageSlug(page_slug) ;
        return globalResponseService.responseClient(singlePage);
    }

    @DeleteMapping("/dynamicpages/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deletePageById(@PathVariable int id){
        dynamicPageService.deleteById(id);
        return globalResponseService.globalResponse("Success", HttpStatus.OK.value());
    }



}
