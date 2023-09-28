package jpa1;

import javax.persistence.*;

@Entity
@Table(name="MenuClient")
public class MenuClient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="myid")
    private long id;

    @Column(nullable = false)
    private String dish;
    private int price;
    private int discount;
    private double weight;

    public MenuClient() {

    }
    public MenuClient(String dish, int price, double weight, int discount) {
        this.dish = dish;
        this.price = price;
        this.weight = weight;
        this.discount = discount;

    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getDish() {

        return dish;
    }

    public void setDish(String dish) {

        this.dish = dish;
    }

    public int getPrice() {

        return price;
    }

    public void setPrice(int price) {

        this.price = price;
    }
    public void setDiscount(int discount){
        this.discount = discount;
    }
    public int getDiscount(){
        return discount;
    }

    @Override
    public String toString() {
        return "MenuClients{" +
                "id=" + id +
                ", dish='" + dish + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ",discount=" + discount +
                '}';
    }
}

