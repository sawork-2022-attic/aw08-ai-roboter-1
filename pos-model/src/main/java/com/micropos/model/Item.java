package com.micropos.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(fluent = true, chain = false)
public class Item {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    @Getter
//    @Setter
//    private Long id;
//
//    @Column(name = "cart_id")
//    @Getter
//    @Setter
//    private Long cartId;

    @OneToOne
    @JoinTable(name = "product", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    @Setter
    @Getter
    private Product product;

    @ManyToOne
    @JoinTable(name = "cart", joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "id"))
    private Cart cart;

//    @Column(name = "product_id")
//    @Getter
//    @Setter
//    private Integer productId;

    @Column(name = "quantity")
    @Getter
    @Setter
    private int quantity;
}
