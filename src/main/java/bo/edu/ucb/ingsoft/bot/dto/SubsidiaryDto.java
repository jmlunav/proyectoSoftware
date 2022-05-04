package bo.edu.ucb.ingsoft.bot.dto;

public class SubsidiaryDto {
    private int id;
    private String name;
    private String location;
    private String timetable;
    private String status;

    public SubsidiaryDto(int id, String name, String location, String timetable, String status) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.timetable = timetable;
        this.status = status;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
