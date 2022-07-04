package com.micropos.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Data
@Accessors(fluent = true, chain = true)
public class OrderItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


//    @Column(name = "product_id")
//    private Integer productId;
//
//    @Column(name = "order_id")
//    private Integer orderId;

    @Column(name = "quantity")
    private Integer quantity;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
