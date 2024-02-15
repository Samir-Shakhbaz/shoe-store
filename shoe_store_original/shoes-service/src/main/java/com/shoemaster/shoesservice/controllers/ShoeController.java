package com.shoemaster.shoesservice.controllers;

import com.shoemaster.shoesservice.models.Shoe;
import com.shoemaster.shoesservice.services.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/shoes")
public class ShoeController {

    @Autowired
    ShoeService shoeService;

    @GetMapping
    public ResponseEntity<?> getAllShoes() {
        return ResponseEntity.ok(shoeService.getAllShoes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getShoeById(@PathVariable Long id) {
        return ResponseEntity.ok(shoeService.getShoeById(id));
    }

    @PostMapping
    public ResponseEntity<?> insertNewItem(@RequestBody Shoe shoe) {
        try {
            Shoe newShoe= shoeService.insertNewShoe(shoe);
            return ResponseEntity.created(URI.create("/item/" + newShoe.getShoeId())).body(newShoe);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createShoe(@RequestBody Shoe shoe) {
//        try {
//            // checking if username is in use
//            if (userService.existsByUsername(user.getUsername())) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already in use.");
//            }

            // creating and saving new user
            Shoe newShoe = shoeService.createNewShoe(shoe);
            return ResponseEntity.status(HttpStatus.CREATED).body(newShoe);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed: " + e.getMessage());
//        }
    }

    @PatchMapping
    public ResponseEntity<?> updateItem(@RequestBody Shoe shoe) {
        try {
            return ResponseEntity.ok(shoeService.updateShoe(shoe));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        try {
            shoeService.deleteShoe(id);
            return ResponseEntity.ok("item with " + id + " deleted");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

//    @GetMapping("/shoe-list")
//    public String displayShoes(Model model) {
//        model.addAttribute("shoes", shoeService.getAllShoes());
//        return "shoe-list";
//    }

}

