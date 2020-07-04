package com.acepirit.ghumou.main.GhumouMain.Service;

import com.acepirit.ghumou.main.GhumouMain.Entity.DynamicPages;

import java.util.List;

public interface DynamicPageService {
    void save(DynamicPages dypage);

    List<DynamicPages> findAll();

    DynamicPages findById(int id);

    void deleteById(int id);

    DynamicPages findByPageSlug(String slug);


}
