package Jdbc1;

import java.sql.*;
import java.util.Scanner;

public class Main {
    // CREATE DATABASE apartments;
    static final String APARTMENTS_CONNECTION = "jdbc:mysql://localhost:3306/apartments?serverTimezone=Europe/Kyiv";
    static final String APARTMENTS_USER = "root";
    static final String APARTMENTS_PASSWORD = "Oleks@ndr1998";

    static Connection conn;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            try {
                // create connection
                conn = DriverManager.getConnection(APARTMENTS_CONNECTION, APARTMENTS_USER, APARTMENTS_PASSWORD);
                initDB();

                while (true) {
                    System.out.println("1: add apartments");
                    System.out.println("2: delete apartments");
                    System.out.println("3: change price apartments");
                    System.out.println("4: view apartments");
                    System.out.println("5: view apartments by price");
                    System.out.println("6: view apartments by number of rooms");
                    System.out.println("7: view apartments by district");
                    System.out.println("8: view apartments by address");
                    System.out.println("9: view apartments by area");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addApartments(sc);
                            break;
                        case "2":
                            deleteApartments(sc);
                            break;
                        case "3":
                            changePriceApartments(sc);
                            break;
                        case "4":
                            viewApartment();
                            break;
                        case "5":
                            viewApartmentPrice();
                            break;
                        case "6":
                            viewApartmentNumberOfRooms();
                            break;
                        case "7":
                            viewApartmentDistrict();
                            break;
                        case "8":
                            viewApartmentAddress();
                            break;
                        case "9":
                            viewApartmentArea();
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                if (conn != null) conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
    }
    private static void initDB() throws SQLException {
        Statement st = conn.createStatement();
        try {
            st.execute("DROP TABLE IF EXISTS Apartments");
            st.execute("CREATE TABLE Apartments (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "district VARCHAR(20), " + "address VARCHAR(25) , " + "area INT, " + "NumberOfRooms INT, " +
                    "price INT)");
        } finally {
            st.close();
        }
    }

    /*
    CREATE TABLE Apartments (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    district VARCHAR(20), address VARCHAR(25), area INT, NumberOfRooms INT, price INT);
     */
    private static void addApartments(Scanner sc) throws SQLException {

        System.out.print("Enter apartment district: ");
        String district = sc.nextLine();

        System.out.print("Enter apartment address: ");
        String address = sc.nextLine();

        System.out.print("Enter apartment area m^2: ");
        String sArea = sc.nextLine();
        int area = Integer.parseInt(sArea);

        System.out.print("Enter apartment number of rooms: ");
        String sNumberOfRooms = sc.nextLine();
        int NumberOfRooms = Integer.parseInt(sNumberOfRooms);

        System.out.print("Enter apartment price $: ");
        String sPrice = sc.nextLine();
        int price = Integer.parseInt(sPrice);

        PreparedStatement ps = conn.prepareStatement("INSERT INTO Apartments (district, address, area, NumberOfRooms, price) VALUES(?, ?,?,?,?)");
        try {
            ps.setString(1, district);
            ps.setString(2, address);
            ps.setInt(3, area);
            ps.setInt(4, NumberOfRooms);
            ps.setInt(5, price);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }
    }

    private static void deleteApartments(Scanner sc) throws SQLException {
        System.out.print("Enter district for delete apartments: ");
        String district = sc.nextLine();

        PreparedStatement ps = conn.prepareStatement("DELETE FROM Apartments WHERE district = ?");
        try {
            ps.setString(1, district);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }
    }
    private static void changePriceApartments(Scanner sc) throws SQLException {
        System.out.print("Enter apartments address for change: ");
        String address = sc.nextLine();
        System.out.print("Enter new price: ");
        String sPrice = sc.nextLine();
        int price = Integer.parseInt(sPrice);

        PreparedStatement ps = conn.prepareStatement("UPDATE Apartments SET price = ? WHERE address = ?");
        try {
            ps.setString(1, address);
            ps.setInt(2, price);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }
    }

    private static void viewApartment() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Apartments");
        try {
            ResultSet rs = ps.executeQuery();

            try {
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }
    private static void viewApartmentPrice() throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Apartments ORDER BY price");
    try {
        ResultSet rs = ps.executeQuery();

        try {
            ResultSetMetaData md = rs.getMetaData();

            for (int i = 1; i <= md.getColumnCount(); i++)
                System.out.print(md.getColumnName(i) + "\t\t");
            System.out.println();

            while (rs.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + "\t\t");
                }
                System.out.println();
            }
        } finally {
            rs.close();
        }
    } finally {
        ps.close();
    }
}
    private static void viewApartmentNumberOfRooms() throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Apartments ORDER BY NumberOfRooms");
        try {
            ResultSet rs = ps.executeQuery();

            try {
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }
    private static void viewApartmentDistrict() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Apartments ORDER BY district");
        try {
            ResultSet rs = ps.executeQuery();

            try {
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }

private static void viewApartmentAddress() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Apartments ORDER BY address");
    try {
        ResultSet rs = ps.executeQuery();

        try {
            ResultSetMetaData md = rs.getMetaData();

            for (int i = 1; i <= md.getColumnCount(); i++)
                System.out.print(md.getColumnName(i) + "\t\t");
            System.out.println();

            while (rs.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + "\t\t");
                }
                System.out.println();
            }
        } finally {
            rs.close();
        }
    } finally {
        ps.close();
    }
}
    private static void viewApartmentArea() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Apartments ORDER BY area");
        try {
            ResultSet rs = ps.executeQuery();

            try {
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }
    }

