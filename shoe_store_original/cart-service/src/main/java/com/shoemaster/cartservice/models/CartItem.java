package com.shoemaster.cartservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long shoeId;

    @Column(nullable = false)
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

}
