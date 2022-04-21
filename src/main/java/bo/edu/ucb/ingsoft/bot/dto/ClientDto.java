package bo.edu.ucb.ingsoft.bot.dto;

public class ClientDto {
    private String name; //nombre
    private String lastname; //apellido
    private String ci; //carnet
    private String reserve_status;

    public ClientDto(String name, String surname, String ci, String reserve_status) {
        this.name = name;
        this.lastname = surname;
        this.ci = ci;
        this.reserve_status = reserve_status;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return lastname;
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
        this.lastname = surname;
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
                ", endDate='" + lastname + '\'' +
                ", reason='" + ci + '\'' +
                '}';
    }
}
