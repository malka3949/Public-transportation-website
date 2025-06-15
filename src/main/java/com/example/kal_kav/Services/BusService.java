
package com.example.kal_kav.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import java.util.List;
import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.kal_kav.Converters.BusConverter;
import com.example.kal_kav.DTOs.BusDto;
import com.example.kal_kav.Models.Bus;
import com.example.kal_kav.Repositories.BusRepository;

// import com.example.lesson2.Converters.BusConverter;
// import com.example.lesson2.DTOs.BusDto;
// import com.example.lesson2.Models.Bus;
// import com.example.lesson2.Repositories.BusRepository;

@Service
public class BusService {
    @Autowired
    private BusConverter busConverter;
    // public static boolean addPrudact(Bus p){
    // return prudacts.add(p);
    // }
    @Autowired
    private BusRepository busRepository;

    public Bus add(BusDto busdto) {
        Bus bus = busConverter.toModeBus(busdto);
        if (!busRepository.exists(Example.of(bus))) {
            return busRepository.save(bus);
        }
        return null;
    }

    public List<BusDto> getAll() {
        List<Bus> buses=busRepository.findAll();
        return busConverter.toBusDtoList(buses);
    }

    public Optional<Bus> getById(Long id) {
        return busRepository.findById(id);
    }
}


