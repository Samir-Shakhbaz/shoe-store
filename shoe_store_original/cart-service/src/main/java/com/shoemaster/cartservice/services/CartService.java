package com.shoemaster.cartservice.services;

import com.shoemaster.cartservice.models.Cart;
import com.shoemaster.cartservice.models.CartItem;
import com.shoemaster.cartservice.models.Shoe;
import com.shoemaster.cartservice.repositories.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public Cart getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = Cart.builder().userId(userId).build();
            cart = cartRepository.save(cart);
        }
        return cart;
    }

    @Transactional
    public Cart addCartItem(Long userId, Shoe shoe) {
        Cart cart = cartRepository.findByUserId(userId);

        // Initialize the cart if it doesn't exist
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(userId);
            cart.setShoes(new ArrayList<>()); // Initialize the list of shoes
            cart = cartRepository.save(cart); // Save the new cart to the database
        }

        // Check if the shoe is already in the cart
        for (CartItem item : cart.getShoes()) {
            if (item.getShoeId().equals(shoe.getShoeId())) {
                item.setAmount(item.getAmount() + 1);
                return cartRepository.save(cart);
            }
        }

        // If the shoe is not in the cart, add it
        cart.addCartItem(shoe.getShoeId());
        return cartRepository.save(cart);
    }


    @Transactional
    public Cart removeCartItem(Long cartItemId, Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        cart.removeCartItem(cartItemId);
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart updateAmount(Long userId, Long cartItemId, Integer amount) {
        Cart cart = cartRepository.findByUserId(userId);
        cart.getShoes().stream().filter(i -> i.getId().compareTo(cartItemId) == 0)
                .findFirst().ifPresent(cartItem -> cartItem.setAmount(amount));
        return cart;
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
}
