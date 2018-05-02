package cz.bilysklep.trainings.webshop.ordering.service;

import cz.bilysklep.trainings.webshop.ordering.service.json.AddressJson;
import cz.bilysklep.trainings.webshop.ordering.service.json.OrderItemJson;
import cz.bilysklep.trainings.webshop.ordering.service.json.OrderJson;
import cz.bilysklep.trainings.webshop.ordering.service.json.Unit;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderWS {
  
    private final List<OrderJson> orders = new CopyOnWriteArrayList<>(createTestData());
    
//    
//    public void createOrder(@RequestBody OrderJson order) {
//        
//    }
    
    @GetMapping("/orders")
    public @ResponseBody List<OrderJson> getOrders() {
        return orders;
    }
    
    private static List<OrderJson> createTestData() {
        
        AddressJson address = new AddressJson();
        address.setCity("Ostrava");
        address.setName("Ponozky bez smradu s.r.o.");
        address.setStreet("Stodolni 1");
        address.setZip("123 45");
        
        OrderItemJson ponozka1 = new OrderItemJson();
        ponozka1.setDescription("Ponozka cislo 1");
        ponozka1.setUnit(Unit.PIECE);
        ponozka1.setAmount(BigDecimal.TEN);
        ponozka1.setUnitPrice(BigDecimal.valueOf(50));

        OrderItemJson doprava = new OrderItemJson();
        doprava.setDescription("Doprava");
        doprava.setUnit(Unit.KILOMETER);
        doprava.setAmount(BigDecimal.valueOf(25));
        doprava.setUnitPrice(BigDecimal.valueOf(5));
        
        OrderJson order = new OrderJson();
        order.setCustomerAddress(address);
        order.setItems(Arrays.asList(ponozka1,doprava));
        order.setCreatedTimestamp(Instant.now());
        order.setNumber("123");
        order.setId(1L);
        
        return Arrays.asList(order);
    }
    
    
}
