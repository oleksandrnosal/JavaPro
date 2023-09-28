package jpa1;

import javax.persistence.*;

@Entity
@Table (name = "Transaction")

public class ExchangeOperations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "First name of sender")
    private String senderFirstName;
    @Column(name = "Last name of sender ")
    private String senderLastName;
    @Column(name = "First name of receiver")
    private String recipientFirstName;
    @Column(name = "Last name of receiver")
    private String recipientLastName;
    @Column(name = "currency")
    private String currency;
    @Column(name = "amount")
    private double amount;

    public ExchangeOperations(String senderFirstName, String senderLastName, String receiverFirstName, String receiverLastName, String currency, double amount) {
        this.senderFirstName = senderFirstName;
        this.senderLastName = senderLastName;
        this.recipientFirstName = receiverFirstName;
        this.recipientLastName = receiverLastName;
        this.currency = currency;
        this.amount = amount;
    }

    public ExchangeOperations(){
    }
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public String getSenderFirstName(){
        return senderFirstName;
    }
    public void setSenderFirstName(){
        this.senderFirstName = senderFirstName;
    }
    public String getSenderLastName(){
        return  senderLastName;
    }
    public void setSenderLastName(){
        this.senderLastName = senderLastName;
    }
    public String getRecipientFirstName(){
        return recipientFirstName;
    }
    public void setRecipientLastName(){
        this.recipientLastName = recipientLastName;
    }
    public String getCurrency(){
        return currency;
    }
    public void setCurrency(){
        this.currency = currency;
    }
    public double getAmount(){
        return amount;
    }
    public void setAmount(){
        this.amount = amount;
    }
    @Override
    public String toString(){
        return "Transaction: " +"Id = " + id +",senderFirstName='" + senderFirstName+'\''+
                ", senderLastName='" + senderLastName+'\''+
                ", recipientFirstName='" + recipientFirstName +'\''+
                ",recipientLastName= '" + recipientLastName +'\''+
                ",currency='" + currency+'\''+",amount='"+amount+'\''+'}';
    }
}