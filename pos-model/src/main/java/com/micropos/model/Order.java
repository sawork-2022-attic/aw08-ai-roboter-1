package com.micropos.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;


@Entity
@Table(name = "order_")
@Accessors(fluent = true, chain = true)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    private Integer id;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Optional<OrderItem> addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        return Optional.of(orderItem);
    }

    @Column(name = "paid")
    @Getter
    @Setter
    private boolean paid;

}
