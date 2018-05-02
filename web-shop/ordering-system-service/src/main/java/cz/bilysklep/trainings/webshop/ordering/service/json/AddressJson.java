package cz.bilysklep.trainings.webshop.ordering.service.json;

public class AddressJson {

    private String name;
    private String street;
    private String city;
    private String zip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Customer{" 
                + "name=" + name 
                + ", street=" + street 
                + ", city=" + city 
                + ", zip=" + zip 
                + '}';
    }
    
    
    
    
    
}
