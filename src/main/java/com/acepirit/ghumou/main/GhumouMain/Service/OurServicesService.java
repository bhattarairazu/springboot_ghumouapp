package com.acepirit.ghumou.main.GhumouMain.Service;

import com.acepirit.ghumou.main.GhumouMain.Entity.OurServices;

import java.util.List;

public interface OurServicesService {
    void save(OurServices ourservice);

    void deleteById(int id);

    List<OurServices> findAll();
}
