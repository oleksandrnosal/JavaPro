package jpa1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import static jpa1.App.emf;

@RestController
public class Web {
    @GetMapping("/transaction")
    public List<ExchangeOperations> showTransaction(){
        emf = Persistence.createEntityManagerFactory("Bank");
       EntityManager em = emf.createEntityManager();
        Query queryBankAccount = em.createQuery("SELECT o FROM BankAccount o", BankAccount.class);
        List<ExchangeOperations> list = (List<ExchangeOperations>) queryBankAccount.getResultList();
        em.close();
        return list;
    }
    @GetMapping("/total_amount")
    public List<ExchangeOperations> showTotalAmount() {
        emf = Persistence.createEntityManagerFactory("Bank");
        EntityManager em = emf.createEntityManager();
        Query queryBankAccount = em.createQuery("SELECT o FROM BankAccount o", BankAccount.class);
        List<ExchangeOperations> list = (List<ExchangeOperations>) queryBankAccount.getResultList();
        double moneyUSD = 0;
        double  moneyEUR = 0;
        double moneyUAH = 0;

        for (BankAccount bankAccount : list) {
            moneyUSD = moneyUSD + bankAccount.getMoneyUSD();
            moneyEUR = moneyEUR + bankAccount.getMoneyEUR();
            moneyUAH = moneyUAH + bankAccount.getMoneyUAH();
        }
        String res = "Money in USD ="+moneyUSD + "MoneyEUR ="+moneyEUR+"Money UAH ="+moneyUAH;
        em.close();
        return list;
    }
}
