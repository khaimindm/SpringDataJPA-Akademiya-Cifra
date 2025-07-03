package ru.khaimin.springcourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.khaimin.springcourse.entity.ClientEntity;
import ru.khaimin.springcourse.models.Client;
import ru.khaimin.springcourse.models.Dish;
import ru.khaimin.springcourse.models.Order;
import ru.khaimin.springcourse.models.Pizza;
import ru.khaimin.springcourse.services.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringcourseApplication {

    public static PizzaService pizzaService;
    public static SushiService sushiService;
    public static OrderService orderService;
    public static CustomerService customerService;
    public static DishService dishService;
    public static ClientService clientService;

    @Autowired
    public SpringcourseApplication(PizzaService pizzaService_, SushiService sushiService_, OrderService orderService_,
                                   CustomerService customerService_, DishService dishService_,
                                   ClientService clientService_) {
        this.pizzaService = pizzaService_;
        this.sushiService = sushiService_;
        this.orderService = orderService_;
        this.customerService = customerService_;
        this.dishService = dishService_;
        this.clientService = clientService_;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringcourseApplication.class, args);

//        System.out.println("Menu: ");
//
//        // Получаю все имеющиеся в базе данных блюда
//        List<Dish> dishes = dishService.findAllDishes();
//        int number = 0;
//        for (Dish dish : dishes) {
//            number++;
//            System.out.println(number + ". " + dish.getName() + ". " + "Price: " + dish.getPrice());
//        }
//        number = 0;

        System.out.println();
        System.out.println("Orders: ");
        SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd-YYYY");

        // Получаю все имеющиеся в базе данных заказы
        List<Order> orders = orderService.findAllOrders();
        for (int i = 0; i < orders.size(); i++) {
            System.out.println("Order date: " + formatter.format(orders.get(i).getDate()) + " Order full cost: "
                    + orders.get(i).getOrderFullCost() + " Client: " + orders.get(i).getClient().getName());

            System.out.println();
        }

        // Создаю экземпляр объекта Order для проверки
//        Order order = new Order();
//        order.setId(2);
//        Date date = new Date();
//        date.setDate(2025 - 6 - 24);
//        order.setDate(date);
//        order.setReadiness(false);
//        order.setOrderFullCost(700.0);
//
//        ClientEntity clientEntity = clientService.getClientEntityById(1);
//
//        Client client1 = new Client();
//        client1.setName(clientEntity.getName());
//
//        order.setClient(client1);
//
//        // Создаем список для заполнения заказа блюдами
//        List<Dish> dishes1 = new ArrayList<>();
//        Pizza margaritta_SMALL = new Pizza(1, "Margaritta_SMALL", 300, "pizza");
//        Pizza margaritta_LARGE = new Pizza(3, "Margaritta_LARGE", 400, "pizza");
//        Pizza margaritta_SMALL2 = new Pizza(1, "Margaritta_SMALL", 300, "pizza");
//
//        dishes1.add(margaritta_SMALL);
//        dishes1.add(margaritta_LARGE);
//        dishes1.add(margaritta_SMALL2);
//        order.setDishes(dishes1);
//
//        orderService.saveOrder(order);
//
//        customerService.placeOrder(order);

        // Получаю все имеющиеся в базе данных заказы после добавления нового заказа
//        System.out.println("Orders: ");
//        List<Order> orders1 = orderService.findAllOrders();

//        for (int i = 0; i < orders1.size(); i++) {
//            System.out.println("Order date: " + formatter.format(orders1.get(i).getDate()) + " Order full cost: "
//                    + orders1.get(i).getOrderFullCost() + " Client: " + orders1.get(i).getClient().getName());
//
//            System.out.println();
//        }
    }

}