package jpa1;


import javax.persistence.*;
import java.util.List;
import java.util.Scanner;


public class App {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            emf = Persistence.createEntityManagerFactory("Bank");
            em = emf.createEntityManager();
            try {
                while (true) {
                    System.out.println("1: bank clients");
                    System.out.println("2: transactions");
                    System.out.println("3: bank accounts");
                    System.out.println("4: exchange rate");
                    System.out.println("5: top up a bank account");
                    System.out.println("6: transfer funds to another account");
                    System.out.println("7: currency conversion at the exchange rate");
                    System.out.println("8: check all money of a client in UAH");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1" -> addBankClient(sc);
                        case "2" -> transaction();
                        case "3" -> addBankAccounts(sc);
                        case "4" -> exchangeRate(sc);
                        case "5" -> topUpBankAccount(sc);
                        case "6" -> transferFunds(sc);
                        case "7" -> currencyConversion(sc);
                        case "8" -> checkAllMoneyUAH(sc);
                        default -> {
                            return;
                        }
                    }
                }
            } finally {
                sc.close();
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private static void addBankClient(Scanner sc) {
        System.out.print("Enter bank client first name: ");
        String fName = sc.nextLine();
        System.out.print("Enter bank client last name: ");
        String lName = sc.nextLine();

        em.getTransaction().begin();

        try {
            Client bc = new Client(fName, lName);
            em.persist(bc);
            em.getTransaction().commit();
            System.out.println(bc.getId());
            System.out.println("New bank client add" + bc);
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }


    private static void addBankAccounts(Scanner sc) {
        System.out.println("Enter card number: ");
        String cardNumberIn = sc.nextLine();

        System.out.println("Enter client id: ");
        String ClientIdIn = sc.nextLine();

        try {
            int cardNumber = Integer.parseInt(cardNumberIn);
            long clientId = Long.parseLong(ClientIdIn);

            Client client = em.find(Client.class, clientId);

            if (client != null) {
                BankAccount bankAccount = new BankAccount();
                bankAccount.setClient(client);
                bankAccount.setCardNumber(cardNumber);

                em.getTransaction().begin();
                em.persist(bankAccount);
                client.getBa().add(bankAccount);
                em.getTransaction().commit();

                System.out.println("Bank account added");
            } else {
                System.out.println("Client not found");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid format");
        }
    }
        private static void exchangeRate(Scanner sc) {
            System.out.println("Enter USD buy: ");
            String USDBuyInput = sc.nextLine();
            double USDBuy = Double.parseDouble(USDBuyInput);

            System.out.println("Enter USD sell: ");
            String USDSellInput = sc.nextLine();
            double USDSell = Double.parseDouble(USDSellInput);

            System.out.println("Enter EUR buy: ");
            String EURBuyInput = sc.nextLine();
            double EURBuy = Double.parseDouble(EURBuyInput);

            System.out.println("Enter EUR sell:");
            String EURSellInput = sc.nextLine();
            double EURSell = Double.parseDouble(EURSellInput);
            em.getTransaction().begin();
            try {
                ExchangeRate exchangeRate = new ExchangeRate(USDBuy, USDSell, EURBuy, EURSell);
                em.persist(exchangeRate);
                em.getTransaction().commit();
                System.out.println("Exchange rate updated:" + exchangeRate);
            } catch (Exception e) {
                System.out.println("Something wrong");
                em.getTransaction().rollback();
            }
        }
    private static void topUpBankAccount(Scanner sc) {
        try {
            System.out.println("chose the currency for a top up your bank account:");
            String currency = sc.nextLine();
            System.out.println("Enter card number:");
            String sCardNumber = sc.nextLine();
            long cardNumber = Long.parseLong(sCardNumber);

            System.out.println("How much money you want to top up7");
            String sMoney = sc.nextLine();

            Query queryBankAccount = em.createQuery("SELECT x FROM BankAccount x WHERE x.cardNumber =: cardNumber");
            queryBankAccount.setParameter("cardNumber", cardNumber);
            BankAccount bankAccount = (BankAccount) queryBankAccount.getSingleResult();

            double money = Double.parseDouble(sMoney);
            double moneyInAccount = 0;

            boolean USD = currency.equalsIgnoreCase("USD");
            boolean EURO = currency.equalsIgnoreCase("EURO");
            boolean UAH = currency.equalsIgnoreCase("UAH");

            em.getTransaction().begin();

            if (USD) {
                moneyInAccount = bankAccount.getMoneyUSD();
                bankAccount.setMoneyUSD(moneyInAccount + money);
            }
            if (EURO) {
                moneyInAccount = bankAccount.getMoneyEUR();
                bankAccount.setMoneyEUR(moneyInAccount + money);
            }
            if (UAH) {
                moneyInAccount = bankAccount.getMoneyUAH();
                bankAccount.setMoneyUAH(moneyInAccount + money);
            }
            em.getTransaction().commit();
        } catch (NumberFormatException e) {
            System.out.println("invalid input format");
        } catch (NoResultException e) {
            System.out.println("Card number not found");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void transferFunds(Scanner sc) {
        System.out.println("Enter card number of the client");
        String recipientCardNumberIn = sc.nextLine();
        System.out.println("Enter the number of card for transfer your funds");
        String sendCardNumberIn = sc.nextLine();
        System.out.println("Chose the currency of transfer your funds ");
        String transferCurrency = sc.nextLine();
        System.out.println("How much money do you want to send:");
        String transfersMoneyIn = sc.nextLine();

        try {

            long recipientCardNumber = Long.parseLong(recipientCardNumberIn);
            long sendCardNumber = Long.parseLong(sendCardNumberIn);

            double transferMoney = Double.parseDouble(transfersMoneyIn);
            double sendMoney;

            Query query = em.createQuery("SELECT x FROM BankAccount x WHERE x.cardNumber = :cardNumber");
            query.setParameter("cardNumber", recipientCardNumber);
            BankAccount bankAccountRecipient = (BankAccount) query.getSingleResult();
            query.setParameter("cardNumber", sendCardNumber);
            BankAccount bankAccountSender = (BankAccount) query.getSingleResult();

            boolean UAH = transferCurrency.equalsIgnoreCase("UAH");
            boolean USD = transferCurrency.equalsIgnoreCase("USD");
            boolean EURO = transferCurrency.equalsIgnoreCase("EURO");

            em.getTransaction().begin();
            if (UAH) {
                sendMoney = bankAccountSender.getMoneyUAH();
                if (sendMoney > transferMoney && (sendMoney - transferMoney) > 0) {
                    sendMoney = sendMoney - transferMoney;
                    bankAccountSender.setMoneyUAH(sendMoney);

                    double recipientMoney = bankAccountRecipient.getMoneyUAH();
                    recipientMoney = recipientMoney + transferMoney;
                    bankAccountRecipient.setMoneyUAH(recipientMoney);
                } else
                    System.out.println("Not enough money");
            }
            if (USD) {
                sendMoney = bankAccountSender.getMoneyUSD();
                if (sendMoney > transferMoney && (sendMoney - transferMoney) > 0) {
                    sendMoney = sendMoney - transferMoney;
                    bankAccountSender.setMoneyUSD(sendMoney);

                    double recipientMoney = bankAccountRecipient.getMoneyUSD();
                    recipientMoney = recipientMoney + transferMoney;
                    bankAccountRecipient.setMoneyUSD(recipientMoney);
                } else
                    System.out.println("Not enough money");
            }
            if (EURO) {
                sendMoney = bankAccountSender.getMoneyEUR();
                if (sendMoney > transferMoney && (sendMoney - transferMoney) > 0) {
                    sendMoney = sendMoney - transferMoney;
                    bankAccountSender.setMoneyEUR(sendMoney);

                    double recipientMoney = bankAccountRecipient.getMoneyUAH();
                    recipientMoney = recipientMoney + transferMoney;
                    bankAccountRecipient.setMoneyEUR(recipientMoney);
                } else
                    System.out.println("Not enough money");
            }
            ExchangeOperations eo = new ExchangeOperations(bankAccountSender.getClient().getFName(), bankAccountSender.getClient().getLName(), bankAccountRecipient.getClient().getFName(),
                    bankAccountRecipient.getClient().getLName(), transfersMoneyIn, transferMoney);
            em.persist(eo);
            em.getTransaction().commit();

        } catch (NumberFormatException e) {
            em.getTransaction().rollback();
            System.out.println("Invalid card number format");

        } catch (NoResultException e) {
            em.getTransaction().rollback();
            System.out.println("Bank account not found");

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("error" + e.getMessage());
        }
    }
    public static void checkAllMoneyUAH(Scanner sc){
        System.out.println("Enter client card number:");
        String cardNumberInput = sc.nextLine();
        long cardNumber = Long.parseLong(cardNumberInput);
        Query queryBA = em.createQuery("SELECT x FROM BankAccount x WHERE x.cardNumber= :cardNumber ");
        BankAccount bankAccount = (BankAccount)queryBA.getSingleResult();

        Query queryER = em.createQuery("SELECT u FROM ExchangeRate u ORDER BY u.id DESC");
        queryER.setMaxResults(1);
        ExchangeRate exchangeRate = (ExchangeRate) queryER.getSingleResult();
        double USDExchange = exchangeRate.getUSD_sell();
        double EURExchange = exchangeRate.getEURO_sell();
        double bankAccountUSD = bankAccount.getMoneyUSD();
        double bankAccountEURO = bankAccount.getMoneyEUR();
        double bankAccountUAH = bankAccount.getMoneyUAH();

        double sum = (USDExchange * bankAccountUSD) + (EURExchange * bankAccountEURO) + bankAccountUAH;
        System.out.println("The total amount of money in UAH" + sum + bankAccount.getCardNumber());
        }
    public static void transaction() {
        Query query = em.createQuery("SELECT o FROM ExchangeOperations o", ExchangeOperations.class);
        List<ExchangeOperations> list = (List<ExchangeOperations>) query.getResultList();

        for (ExchangeOperations eo : list) {
            System.out.println(eo);
        }
    }
    public static void currencyConversion(Scanner sc){
        try {
            System.out.println("Enter your card number for conversion : ");
            String cardNumberIn = sc.nextLine();
            long cardNumber = Long.parseLong(cardNumberIn);

            System.out.println("Choose the currency for conversion: ");
            String sourceCurrencyInput = sc.nextLine();

            System.out.println("Choose the currency you want to conversion: ");
            String CurrencyInput = sc.nextLine();

            System.out.println("How much money do you want to conversion?: ");
            String moneyToConversionIn = sc.nextLine();
            double moneyToConversion = Double.parseDouble(moneyToConversionIn);

            em.getTransaction().begin();

            Query query = em.createQuery("SELECT x FROM BankAccount x WHERE x.cardNumber = :cardNumber");
            query.setParameter("cardNumber", cardNumber);
            BankAccount bankAccount = (BankAccount) query.getSingleResult();

            Query queryER = em.createQuery("SELECT u FROM ExchangeRate u ORDER BY u.id DESC");
            queryER.setMaxResults(1);
            ExchangeRate ER = (ExchangeRate) queryER.getSingleResult();

            double sourceCurrencyRate = 0.0, targetCurrencyRate = 0.0;

            if (sourceCurrencyInput.equalsIgnoreCase("UAH")) {
                sourceCurrencyRate = 1.0;
            } else if (sourceCurrencyInput.equalsIgnoreCase("USD")) {
                sourceCurrencyRate = ER.getUSD_buy();
            } else if (sourceCurrencyInput.equalsIgnoreCase("EURO")) {
                sourceCurrencyRate = ER.getEUR_buy();
            }

            if (CurrencyInput.equalsIgnoreCase("UAH")) {
                targetCurrencyRate = 1.0;
            } else if (CurrencyInput.equalsIgnoreCase("USD")) {
                targetCurrencyRate = ER.getUSD_sell();
            } else if (CurrencyInput.equalsIgnoreCase("EURO")) {
                targetCurrencyRate = ER.getEURO_sell();
            }

            if (sourceCurrencyRate == 0 || targetCurrencyRate == 0) {
                System.out.println("Exchange rates not available");
                em.getTransaction().rollback();
                return;
            }

            double sourceAmountInUAH = moneyToConversion / sourceCurrencyRate;
            double targetAmountInTargetCurrency = sourceAmountInUAH * targetCurrencyRate;

            if (sourceCurrencyInput.equalsIgnoreCase(CurrencyInput)) {
                System.out.println("You can't conversion the same of currency!");
            } else if (sourceCurrencyInput.equalsIgnoreCase("UAH")) {
                bankAccount.setMoneyUAH(bankAccount.getMoneyUAH() - moneyToConversion);
                bankAccount.setMoney(CurrencyInput, bankAccount.getMoney(CurrencyInput) + targetAmountInTargetCurrency);
            } else {
                bankAccount.setMoney(sourceCurrencyInput, bankAccount.getMoney(sourceCurrencyInput) - moneyToConversion);
                bankAccount.setMoney(CurrencyInput, bankAccount.getMoney(CurrencyInput) + targetAmountInTargetCurrency);
            }

            em.getTransaction().commit();
        } catch (NumberFormatException e) {
            System.out.println("Invalid card number or amount format");
            em.getTransaction().rollback();
        } catch (NoResultException e) {
            System.out.println("Bank account not found.");
            em.getTransaction().rollback();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            em.getTransaction().rollback();
        }
    }
}