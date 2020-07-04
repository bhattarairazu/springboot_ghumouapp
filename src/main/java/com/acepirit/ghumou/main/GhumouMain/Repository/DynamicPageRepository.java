package com.acepirit.ghumou.main.GhumouMain.Repository;

import com.acepirit.ghumou.main.GhumouMain.Entity.DynamicPages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DynamicPageRepository extends JpaRepository<DynamicPages,Integer> {
    DynamicPages findByPageSlug(String slug);

}
