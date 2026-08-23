package com.lldcoding.designpatterns.orderstate;

interface OrderState {
    void handleRequest(Order context);
    String cancelOrder(Order context);
}

class NewOrderState implements OrderState {
    @Override
    public void handleRequest(Order context) {
        System.out.println("Handling new order state");
        context.setOrderState(new ProcessingOrderState());
    }

    @Override
    public String cancelOrder(Order context) {
        return "Cancelling new order";
    }
}

class ProcessingOrderState implements OrderState {
    @Override
    public void handleRequest(Order context) {
        System.out.println("Handling processing order state");
        context.setOrderState(new ShippedOrderState());
    }

    @Override
    public String cancelOrder(Order context) {
    System.out.println();
        return "Cancelling processing order";
    }
}

class ShippedOrderState implements OrderState {
    @Override
    public void handleRequest(Order context) {
        System.out.println("Handling shipped order state");
        context.setOrderState(new DeliveredOrderState());
    }

    @Override
    public String cancelOrder(Order context) {
        return "Cannot cancel shipped order";
    }
}

class DeliveredOrderState implements OrderState {
    @Override
    public void handleRequest(Order context) {
        System.out.println("Handling delivered order state");
        // No further state transition
    }

    @Override
    public String cancelOrder(Order context) {
        return "Cannot cancel delivered order";
    }
}