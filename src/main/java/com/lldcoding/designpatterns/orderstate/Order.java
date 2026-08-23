package com.lldcoding.designpatterns.orderstate;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class Order {
    int id;
    int description;
    //order acts as context and order state acts as state
    @Setter
    OrderState orderState;

    public String cancelOrder() {
        return orderState.cancelOrder(this);

    }
}
