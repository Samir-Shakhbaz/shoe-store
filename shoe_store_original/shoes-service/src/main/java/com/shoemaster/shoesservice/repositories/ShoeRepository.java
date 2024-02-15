package com.shoemaster.shoesservice.repositories;

import com.shoemaster.shoesservice.models.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoeRepository extends JpaRepository <Shoe, Long> {

    Shoe findByMake(String make);

}
