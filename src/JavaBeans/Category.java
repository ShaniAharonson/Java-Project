package JavaBeans;

public enum Category {
    //1        2           3           4
    Food (0,"food"),
    Electricity(1,"electricity"),
    Restaurant(2,"Restaurant"),
    Vacation(3,"vacation");

    Category(int ordinal, String name) {
        this.ordinal = ordinal;
        this.name = name;
    }

    private int ordinal;
    private String name;
}

