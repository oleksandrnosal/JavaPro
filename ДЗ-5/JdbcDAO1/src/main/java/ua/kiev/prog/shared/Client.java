package ua.kiev.prog.shared;

public class Client {
    @Id
    private int id;

    private String name;
    private int age;

    private long date;

    public Client() {
    }

    public Client(String name, int age, long date) {
        this.name = name;
        this.age = age;
        this.date = date;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) {

        this.age = age;
    }

    public long getDate() {

        return date;
    }
    public void setDate(long date) {

        this.date = date;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", date=" + date +
                '}';
    }
}
