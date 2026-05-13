package backend;

public class CCA_Event implements Activity {
    private static int nextId = 1;

    private final int id;
    private String name;
    private String date;
    private String role;
    private String description;
    
    private int hours;
    private int sealPoints;

    public CCA_Event(String name, String date, String role, String description, int hours, int sealPoints) {
        this.id = nextId++;
        this.name = name;
        this.date = date;
        this.role = role;
        this.description = description;
        this.hours = hours;
        this.sealPoints = sealPoints;
    }

    @Override public int getId() { return id; }
    @Override public String getName() { return name; }
    @Override public String getDate() { return date; }
    @Override public String getRole() { return role; }
    @Override public String getDescription() { return description; }
    @Override public int getHours() { return hours; }
    @Override public int getSealPoints() { return sealPoints; }

    public void setName(String name) { this.name = name; }
    public void setDate(String date) { this.date = date; }
    public void setRole(String role) { this.role = role; }
    public void setDescription(String description) { this.description = description; }
    public void setHours(int hours) { this.hours = hours; }
    public void setSealPoints(int sealPoints) { this.sealPoints = sealPoints; }

    @Override
    public String toString() {
        return String.format("[ID: %d] %s | Date: %s | Role: %s | Hours: %d | SEAL Points: %d",
                id, name, date, role, hours, sealPoints);
        
    }
}
