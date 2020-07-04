package com.acepirit.ghumou.main.GhumouMain.Service;

import com.acepirit.ghumou.main.GhumouMain.Entity.OurServices;
import com.acepirit.ghumou.main.GhumouMain.Repository.OurServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OurServiceImpl implements OurServicesService {
    @Autowired
    private OurServicesRepository ourServicesRepository;


    @Override
    public void save(OurServices ourservice) {
        ourServicesRepository.save(ourservice);
    }

    @Override
    public void deleteById(int id) {
        ourServicesRepository.deleteById(id);

    }

    @Override
    public List<OurServices> findAll() {
        List<OurServices> allServices = ourServicesRepository.findAll();
        return allServices;
    }
}
