package com.shoemaster.shoesservice.services;

import com.shoemaster.shoesservice.models.Shoe;
import com.shoemaster.shoesservice.repositories.ShoeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoeService {

    @Autowired
    ShoeRepository shoeRepository;

    public List<Shoe> getAllShoes() {
        return shoeRepository.findAll();
    }

    public Shoe getShoeById(Long id) {
        Optional<Shoe> optional;
        if ((optional = shoeRepository.findById(id)).isEmpty()) {
            return null;
        } else {
            return optional.get();
        }
    }

    public Shoe getShoeByShoeName(String make) {
        return shoeRepository.findByMake(make);
    }

    public Shoe createNewShoe(Shoe newShoe) {
        return shoeRepository.save(newShoe);
    }

    public Shoe updateShoe(Shoe updatedShoe) {
        Shoe shoe = shoeRepository.findByMake(updatedShoe.getMake());
        BeanUtils.copyProperties(updatedShoe, shoe);
        return shoeRepository.save(shoe);
    }

    public void deleteShoe(Long id) {
        shoeRepository.deleteById(id);
    }

    public Shoe insertNewShoe(Shoe shoe) {
        return shoeRepository.save(shoe);
    }
}
