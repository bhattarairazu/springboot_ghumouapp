package com.acepirit.ghumou.main.GhumouMain.Service;

import com.acepirit.ghumou.main.GhumouMain.Entity.ComparisonPackage;
import com.acepirit.ghumou.main.GhumouMain.Entity.User;
import com.acepirit.ghumou.main.GhumouMain.Repository.ComparisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComparisonPackageServiceImpl implements ComparisonPackageService {
    @Autowired
    private ComparisonRepository comparisonRepository;

    @Override
    public void save(ComparisonPackage comapresionPackage) {
        comparisonRepository.save(comapresionPackage);
    }

    @Override
    public List<ComparisonPackage> findAll() {
        List<ComparisonPackage> allComparison = comparisonRepository.findAll();
        return allComparison;
    }

    @Override
    public void deleteById(int id) {
        comparisonRepository.deleteById(id);

    }

    @Override
    public ComparisonPackage findById(int id) {
        Optional<ComparisonPackage> result = comparisonRepository.findById(id);
        ComparisonPackage compare = null;
        if(result.isPresent()){
            compare = result.get();
            return compare;
        }else{
            throw new RuntimeException("Comparions Package with id "+id+" Not Found");
        }
    }

    @Override
    public List<ComparisonPackage> findByUser(User user) {
        List<ComparisonPackage> listByUser = comparisonRepository.findByUser(user);
        return listByUser;
    }
}
