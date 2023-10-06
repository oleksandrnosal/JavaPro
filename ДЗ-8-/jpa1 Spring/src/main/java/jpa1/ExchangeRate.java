package jpa1;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Exchange Rate")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "EURO_buy")
    private double EURO_buy;

    @Column(name = "EURO_sell")
    private double EURO_sell;

    @Column(name = "USD_buy")
    private double USD_buy;

    @Column(name = "USD_sell")
    private double USD_sell;

    @Column(name = "UAH_buy")
    private double UAH_buy;
    @Column(name = "UAH sell")
    private double UAH_sell;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date createDate;

    public ExchangeRate(double EURO_buy, double EURO_sell, double USD_buy, double USD_sell) {
        this.EURO_buy = EURO_buy;
        this.EURO_sell = EURO_sell;
        this.USD_buy = USD_buy;
        this.USD_sell = USD_sell;

        this.createDate = new Date();
    }

    public ExchangeRate() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getEUR_buy() {
        return EURO_buy;
    }

    public void setEURO_buy(double EURO_buy) {
        this.EURO_buy = EURO_buy;
    }

    public double getEURO_sell() {
        return EURO_sell;
    }

    public void setEURO_sell(double EURO_sell) {
        this.EURO_sell = EURO_sell;
    }

    public double getUSD_buy() {
        return USD_buy;
    }

    public void setUSD_buy(double USD_buy) {
        this.USD_buy = USD_buy;
    }

    public double getUSD_sell() {
        return USD_sell;
    }

    public void setUSD_sell(double USD_sell) {
        this.USD_sell = USD_sell;
    }

    public double getUAH_buy() {
        return UAH_buy;
    }

    public void setUAH_buy(double UAH_buy) {
        this.UAH_buy = UAH_buy;
    }

    public double getUAH_sell() {
        return UAH_sell;
    }

    public void setUAH_sell(double UAH_sell) {
        this.UAH_sell = UAH_sell;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @PrePersist
    public void Create() {
        createDate = new Date();
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "id=" + id +
                ",usd_sell=" + USD_sell +
                ",usd buy=" + USD_buy +
                ",eur sell=" + EURO_sell +
                ",eur buy=" + EURO_buy +
                ",uah sell=" + UAH_sell +
                ",uah buy=" + UAH_buy +
                ",create date=" + createDate +
                '}';
    }
}