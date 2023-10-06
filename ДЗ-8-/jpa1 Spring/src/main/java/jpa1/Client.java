package jpa1;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="BankClient")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String fName;

    @Column(name = "last_name")
    private String lName;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BankAccount> ba = new ArrayList<>();

    public Client(String fName, String lName) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
    }
public Client(){
}
    public long getId() {
        return id;
    }
    public List<BankAccount> getBa(){
        return ba;
    }
        public void setBa(List<BankAccount> ba){
        this.ba= ba;
    }
    public String getFName() {
        return fName;
    }
    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    @Override
    public String toString() {
        return "BankClient{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName=" + lName +
                '}';
    }
}