package bo.edu.ucb.ingsoft.bot.dto;

public class ClientDto {
    private String name;
    private String surname;
    private String ci;
    private String reserve_status;

    public ClientDto(String name, String surname, String ci, String reserve_status) {
        this.name = name;
        this.surname = surname;
        this.ci = ci;
        this.reserve_status = reserve_status;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCi() {
        return ci;
    }

    public String getReserve_status() {
        return reserve_status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public void setReserve_status(String reserve_status) {
        this.reserve_status = reserve_status;
    }
    @Override
    public String toString() {
        return "PermissionDto{" +
                "startDate='" + name + '\'' +
                ", endDate='" + surname + '\'' +
                ", reason='" + ci + '\'' +
                '}';
    }
}
