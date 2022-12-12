package pl.put.poznan.buildinfo.logic;

public abstract class Interior extends Location {
    private int id;
    private String name;

    public Interior(int id, String name)
    {
        super(id, name);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calculateArea();

    public abstract double calculateVolume();

    public abstract double calculateLightPower();
}
