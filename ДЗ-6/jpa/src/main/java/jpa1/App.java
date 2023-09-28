package jpa1;

import javax.persistence.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static javax.persistence.Persistence.createEntityManagerFactory;

public class App {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            emf = Persistence.createEntityManagerFactory("MenuClient");
            em = emf.createEntityManager();
            try {
                while (true) {
                    System.out.println("1: add dish");
                    System.out.println("2: add random dish");
                    System.out.println("3: delete dish");
                    System.out.println("4: change dish");
                    System.out.println("5: view dishes");
                    System.out.println("6: sort dish by price");
                    System.out.println("7: sort dish with discount");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1" -> addDish(sc);
                        case "2" -> insertRandomDish(sc);
                        case "3" -> deleteDish(sc);
                        case "4" -> changeDish(sc);
                        case "5" -> viewDishes();
                        case "6" -> sortByPrice();
                        case "7" -> sortWithDiscount();
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

    private static void addDish(Scanner sc) {
        System.out.print("Enter new dish name: ");
        String dish = sc.nextLine();

        System.out.print("Enter price of the dish: ");
        String sPrice = sc.nextLine();
        int price = Integer.parseInt(sPrice);

        System.out.print("Enter dish weight:");
        String sWeight = sc.nextLine();
        double weight = Double.parseDouble(sWeight);

        System.out.print("Enter dish discount");
        String sDiscount = sc.nextLine();
        int discount = Integer.parseInt(sDiscount);

        em.getTransaction().begin();
        try {
            MenuClient m = new MenuClient(dish, price, weight, discount);
            em.persist(m);
            em.getTransaction().commit();

            System.out.println(m.getId());
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void deleteDish(Scanner sc) {
        System.out.print("Enter dish id: ");
        String sId = sc.nextLine();
        long id = Long.parseLong(sId);

        MenuClient m = em.getReference(MenuClient.class, id);
        if (m == null) {
            System.out.println("Dish id not found!");
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(m);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void changeDish(Scanner sc) {
        System.out.print("Enter dish name: ");
        String dish = sc.nextLine();

        System.out.print("Enter new price: ");
        String sPrice = sc.nextLine();
        int newPrice = Integer.parseInt(sPrice);

        MenuClient m = null;
        try {
            Query query = em.createQuery("SELECT x FROM MenuClient x WHERE x.dish = :dish", MenuClient.class);
            query.setParameter("dish", dish);

            m = (MenuClient) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Dish not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        ///........

        em.getTransaction().begin();
        try {
            m.setPrice(newPrice);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void insertRandomDish(Scanner sc) {
        System.out.print("Enter dish count: ");
        String sCount = sc.nextLine();
        int count = Integer.parseInt(sCount);

        em.getTransaction().begin();
        try {
            for (int i = 0; i < count; i++) {
                MenuClient m = new MenuClient(randomDish(), RND.nextInt(250), RND.nextDouble(200), randomDiscount());
                em.persist(m);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static int randomDiscount() {
        if ((RND.nextInt(100) / 2) != 0) {
            return RND.nextInt(30);
        }
        return 0;
    }

    private static void viewDishes() {
        Query query = em.createQuery("SELECT m FROM MenuClient m", MenuClient.class);
        List<MenuClient> list = (List<MenuClient>) query.getResultList();

        for (MenuClient m : list)
            System.out.println(m);
    }

    private static void sortByPrice() {
        System.out.println("1: sort dish by lower price");
        System.out.println("2: sort dish by higher price");
        System.out.print("-> ");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        switch (str) {
            case "1" -> {
                List<MenuClient> list1 = em.createQuery("SELECT m FROM MenuClient m ORDER BY price DESC ", MenuClient.class).getResultList();
                for (MenuClient m : list1) {
                    System.out.println(m);
                }
            }
            case "2" -> {
                List<MenuClient> list2 = em.createQuery("SELECT m FROM MenuClient m ORDER BY price ASC ", MenuClient.class).getResultList();
                for (MenuClient m : list2) {
                    System.out.println(m);
                }
            }
        }
    }

    private static void sortWithDiscount() {
        List<MenuClient> list = em.createQuery("SELECT m FROM MenuClient m WHERE discount > 0", MenuClient.class).getResultList();
        for (MenuClient m : list) {
            System.out.println(m);
        }
    }
    static final String[] DISHES = {"Coffee", "Tea", "Salad", "Fish", "Potato", "Meat"};
    static final Random RND = new Random();
    static String randomDish() {
        return DISHES[RND.nextInt(DISHES.length)];
        }
    }