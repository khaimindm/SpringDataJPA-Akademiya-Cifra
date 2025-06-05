package ru.khaimin.springcourse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.khaimin.springcourse.models.*;
import ru.khaimin.springcourse.services.CustomerService;
import ru.khaimin.springcourse.services.OrderService;
import ru.khaimin.springcourse.services.PizzaService;
import ru.khaimin.springcourse.services.SushiService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Main {

    public static PizzaService pizzaService;
    public static SushiService sushiService;
    public static OrderService orderService;
    public static CustomerService customerService;

    @Autowired
    public Main(PizzaService pizzaService_, SushiService sushiService_, OrderService orderService_,
                CustomerService customerService_) {
        this.pizzaService = pizzaService_;
        this.sushiService = sushiService_;
        this.orderService = orderService_;
        this.customerService = customerService_;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        System.out.println("Menu");

        System.out.println("Pizzas: ");

        // Получаем все имеющиеся в базе данных объекты пицца
        List<Pizza> pizzas = pizzaService.findAllPizzas();
        for (int i = 0; i < pizzas.size(); i++) {
            System.out.println(pizzas.get(i).getName() + " Price: " + pizzas.get(i).getPrice());
        }

        System.out.println();

        System.out.println("Sushi: ");

        // Получаем все имеющиеся в базе данных объекты суши
        List<Sushi> sushi = sushiService.findAllSushi();
        for (int i = 0; i < sushi.size(); i++) {
            System.out.println(sushi.get(i).getName() + " Price: " + sushi.get(i).getPrice());
        }

        System.out.println("Orders: ");
        SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd-YYYY");

        // Получаем все имеющиеся в базе данных объекты заказов
        List<Order> orders = orderService.findAllOrders();
        for (int i = 0; i < orders.size(); i++) {
            System.out.println("Order date: " + formatter.format(orders.get(i).getDate()) + " Order full cost: "
                    + orders.get(i).getOrderFullCost() + " Client: " + orders.get(i).getClient().getName());
            System.out.println();
        }

        // Создем объект экземпляр объекта Order для проверки
        Order order1 = new Order();
        order1.setId(1);
        Date date = new Date();
        date.setDate(2025 - 05 - 21);
        order1.setDate(date);
        order1.setReadiness(false);
        order1.setOrderFullCost(700.0);
        Client client1 = new Client();
        client1.setName("Ivan");
        order1.setClient(client1);

        // Создаем список для заполнения заказа блюдами
        List<AbstractDish> abstractDishes = new ArrayList<>();
        MargaritaPizza margaritta_SMALL = new MargaritaPizza("Margaritta_SMALL", 300);
        MargaritaPizza margaritta_LARGE = new MargaritaPizza("Margaritta_LARGE", 400);
        abstractDishes.add(margaritta_SMALL);
        abstractDishes.add(margaritta_LARGE);
        order1.setItems(abstractDishes);

        customerService.placeOrder(order1);

    }

}