package com.acepirit.ghumou.main.GhumouMain.Controller;

import com.acepirit.ghumou.main.GhumouMain.Entity.OurServices;
import com.acepirit.ghumou.main.GhumouMain.Service.FileUploadService;
import com.acepirit.ghumou.main.GhumouMain.Service.GlobalResponseService;
import com.acepirit.ghumou.main.GhumouMain.Service.OurServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/v2/")
public class OurServicesController {
    @Autowired
    private OurServicesService ourServicesService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private GlobalResponseService globalResponseService;

    @PostMapping("/services")
    public ResponseEntity<?> postFeatures(@RequestPart("image") MultipartFile image, @RequestPart("service") OurServices service){
        if(image.isEmpty()){
            throw new RuntimeException("You cann't leave the field image empty");
        }
        String imagepath = null;
        try {
            imagepath = fileUploadService.storeFile(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        service.setImage(imagepath);
        ourServicesService.save(service);

        return globalResponseService.responseClient(service);
    }

    @GetMapping("/services")
    public ResponseEntity<?> getAllFeatures(){
        List<OurServices> allServices = ourServicesService.findAll();
        return globalResponseService.listOurServices(allServices);
    }
    @DeleteMapping("/services/{id}")
    public ResponseEntity<?> deleteOurFeatures(@PathVariable int id){
        ourServicesService.deleteById(id);
        return globalResponseService.globalResponse("Success", HttpStatus.OK.value());
    }

}
