package at.refugeesCode.kitchencheffrontend.persistence.model;

public class Idess {

    private String id;

    public Idess(String id) {
        this.id = id;
    }

    public Idess() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Idess{" +
                "id='" + id + '\'' +
                '}';
    }
}
