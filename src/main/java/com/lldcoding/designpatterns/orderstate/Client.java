package com.lldcoding.designpatterns.orderstate;

class Client{

    public static void main(String[] args){
        Order order = new Order();
        order.setOrderState(new NewOrderState());
        System.out.println(order.cancelOrder());


        order.getOrderState().handleRequest(order);
        System.out.println(order.cancelOrder());

        order.getOrderState().handleRequest(order);
        System.out.println(order.cancelOrder());

        order.getOrderState().handleRequest(order);
        System.out.println(order.cancelOrder());
    }

}