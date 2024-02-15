package com.shoemaster.cartservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> shoes;

    public void addCartItem(Long shoeId) {
        shoes.add(CartItem.builder().shoeId(shoeId).amount(1).cart(this).build());
    }

//    public void addCartItem(Long shoeId) {
//        CartItem cartItem = new CartItem();
//        cartItem.setShoeId(shoeId);
//        cartItem.setAmount(1);
//        cartItem.setCart(this); // Set the back-reference to this Cart
//        this.shoes.add(cartItem);
//    }


    public void removeCartItem(Long shoeId) {
        shoes.removeIf(ci -> ci.getShoeId().equals(shoeId));
    }

}
