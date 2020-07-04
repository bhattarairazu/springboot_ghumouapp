package com.acepirit.ghumou.main.GhumouMain.Service;

import com.acepirit.ghumou.main.GhumouMain.Entity.DynamicPages;
import com.acepirit.ghumou.main.GhumouMain.Repository.DynamicPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DynamicPageServiceImpl implements DynamicPageService {

    @Autowired
    private DynamicPageRepository dynamicPageRepository;

    @Override
    public void save(DynamicPages dypage) {
        dynamicPageRepository.save(dypage);


    }

    @Override
    public List<DynamicPages> findAll() {
        List<DynamicPages> allPages = dynamicPageRepository.findAll();
        return allPages;
    }

    @Override
    public DynamicPages findById(int id) {
        Optional<DynamicPages> result = dynamicPageRepository.findById(id);
        DynamicPages singepage = null;
        if(result.isPresent()){
            singepage = result.get();
            return singepage;
        }else{
            throw new RuntimeException("Page with id "+ id+" Not found");
        }
    }

    @Override
    public void deleteById(int id) {
        dynamicPageRepository.deleteById(id);

    }

    @Override
    public DynamicPages findByPageSlug(String slug) {
        DynamicPages singlePages = dynamicPageRepository.findByPageSlug(slug);
        if(singlePages==null){
            throw new RuntimeException("Dynamic Pages with slug "+slug+" Not found");
        }
        return singlePages;
    }
}
