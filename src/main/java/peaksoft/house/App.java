package peaksoft.house;

import peaksoft.house.enums.OrderStatus;
import peaksoft.house.models.Address;
import peaksoft.house.models.Customer;
import peaksoft.house.models.Order;
import peaksoft.house.services.CustomerService;
import peaksoft.house.services.OrderService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws Exception {
        CustomerService customerService = new CustomerService();
        OrderService orderService = new OrderService();
//        Customer customer = new Customer("Erbol",987654);
//        Customer customer1 = new Customer("Nuriza",7645737);
//        Order order = new Order(LocalDateTime.now(),200, OrderStatus.REQUEST);
//        Order order1 = new Order(LocalDateTime.now(),2000, OrderStatus.REQUEST);
//        customer1.setOrders(List.of(order1,order));
//        customer.setOrders(List.of(order1,order));
//        order.setCustomer(customer1);
//        order1.setCustomer(customer1);
//        order.setCustomer(customer);
//        order1.setCustomer(customer);
//        customerService.merge(customer1);
//        customerService.merge(customer);

//        Customer customer = customerService.findById(1L);
//        Address address = new Address("KG", "Bishkek", "Chuy", "Manas");
//        customer.setAddress(address);
//        customerService.merge(customer);

//        Customer customer = new Customer("Asan Usonov", 7366436);
//        Address address = new Address("KG", "Tokmok", "Chuy","Kurmanjan Datka");
//        customer.setAddress(address);
//        customerService.save(customer);

//        customerService.deleteById(8L);
//        orderService.deleteById(7L);
        customerService.findAll().forEach(System.out::println);


        customerService.close();
    }
}
