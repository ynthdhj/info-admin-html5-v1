package com.info.admin.html5.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.info.admin.html5.dto.CircleDto;
import com.info.admin.html5.entity.Circle;
import com.info.admin.html5.repository.CircleRepository;
import com.info.admin.html5.service.CircleService;

/*
 * @author 段洪杰
 */
@Service
public class CircleServiceImpl implements CircleService {

    private CircleRepository circleRepository;

    public CircleServiceImpl(CircleRepository circleRepository) {
        this.circleRepository = circleRepository;     
    }


    public void save(CircleDto circleDto) { 
    	Circle circle = new Circle();
    	circle.setName(circleDto.getName());
    	circle.setCreatedate(new Date());
    	circleRepository.save(circle);
    }
    
    public Circle findByName(String name) {
        return circleRepository.findByName(name);
    }


    
    public List<Circle> findAll() {
        List<Circle> circles = circleRepository.findAll();
        return circles;
    }
   
    public void delete(Long id) {
    	 circleRepository.deleteById(id);
    }

   

   
}
