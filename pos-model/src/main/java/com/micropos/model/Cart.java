package com.micropos.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@SessionScope
public class Cart implements Serializable {

    private HashMap<Integer, Item> items = new HashMap<>();

    public Item addItem(Item item) {
        if (items.get(item.product().id()) != null) {
            var target = items.get(item.product().id());
            target.quantity(target.quantity() + item.quantity());
        } else {
            items.put(item.product().id(), item);
        }
        return items.get(item.product().id());
    }

    public Item modifyItem(Integer productId, Integer quantity) {
        var target = items.get(productId);
        if (target != null) {
            target.quantity(quantity);
        }
        return target;
    }
    public Item getItem(Product product) {
        return getItem(product.id());
    }

    public Item getItem(Integer productId) {
        return items.get(productId);
    }

    public double total(){
        double total = 0;
        for (var item : items.entrySet()) {
            total += item.getValue().product().price() * item.getValue().quantity();
        }
        return total;
    }

    public void setItems(List<Item> items) {
        this.items = new HashMap<>();
        for (var item: items) {
            this.addItem(item);
        }
    };

    public List<Item> getItems(){
        return new ArrayList<>(this.items.values());
    }

    public Item removeItem(Integer productId) {
        return items.remove(productId);
    }
}
