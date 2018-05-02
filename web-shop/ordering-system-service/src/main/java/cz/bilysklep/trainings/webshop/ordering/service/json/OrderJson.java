package cz.bilysklep.trainings.webshop.ordering.service.json;

import java.time.Instant;
import java.util.List;

public class OrderJson {

    private Long id;
    private AddressJson customerAddress;
    private String number;
    private Instant createdTimestamp;
    private List<OrderItemJson> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public AddressJson getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(AddressJson customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Instant getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Instant createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public List<OrderItemJson> getItems() {
        return items;
    }

    public void setItems(List<OrderItemJson> items) {
        this.items = items;
    }
    
}
