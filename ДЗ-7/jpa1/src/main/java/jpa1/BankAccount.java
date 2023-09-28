package jpa1;

import javax.persistence.*;

    @Entity
@Table(name = "BankAccount")
    public class BankAccount {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    @Column (name = "card_number")
    private long cardNumber;
    @Column (name = "money_USD")
    private double moneyUSD;
    @Column (name = "money_EUR")
    private double moneyEUR;
    @Column (name = "money_UAH")
    private double moneyUAH;

    @ManyToOne
    @JoinColumn (name = "client id")
    private Client client;
            public BankAccount (){
            }
            public BankAccount (long cardNumber, double moneyUSD, double moneyEUR, double moneyUAH){
        this.cardNumber = cardNumber;
        this.moneyUSD = moneyUSD;
        this.moneyEUR = moneyEUR;
        this.moneyUAH = moneyUAH;
            }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getMoneyEUR() {
        return moneyEUR;
    }

    public void setMoneyEUR(double moneyEUR) {
        this.moneyEUR = moneyEUR;
    }

    public double getMoneyUSD() {
        return moneyUSD;
    }

    public void setMoneyUSD(double moneyUSD) {
        this.moneyUSD = moneyUSD;
    }

    public double getMoneyUAH() {
        return moneyUAH;
    }

    public void setMoneyUAH(double moneyUAH) {
        this.moneyUAH = moneyUAH;
    }

    public Client getClient(){
                return client;
    }
    public void setClient(Client client){
                this.client = this.client;
    }

    public void setMoney (String currency, double amount){
        if (currency.equalsIgnoreCase ("UAH")) {
            this.moneyUAH = amount;
    }else if (currency.equalsIgnoreCase("USD")) {
            this.moneyUSD = amount;
        }else if (currency.equalsIgnoreCase("EUR")) {
            this.moneyEUR = amount;
        }
    }
    public double getMoney (String currency){
        if (currency.equalsIgnoreCase (("UAH"))) {
            return moneyUAH;
        }else if (currency.equalsIgnoreCase("USD")) {
            return moneyUSD;
        }else if (currency.equalsIgnoreCase("EUR")) {
            return moneyEUR;
    }
        return 0;
    }
    @Override
    public String toString() {
        return "BankClient{" +
                "id=" + id +
                ", card number='" + cardNumber + '\'' +
                ", money USD=" + moneyUSD + '\''+
                ", money EUR=" + moneyEUR + '\''+
                ", money UAH=" + moneyUAH + '\''+
                ", client=" + client +
                '}';
    }
    }