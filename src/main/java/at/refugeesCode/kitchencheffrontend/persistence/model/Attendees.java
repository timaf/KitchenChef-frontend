package at.refugeesCode.kitchencheffrontend.persistence.model;

public class Attendees {

    private String id;
    private String username;

    public Attendees(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public Attendees() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Attendees{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
