package bo.edu.ucb.ingsoft.bot.dto;

public class ClientDto {
    private String name; //nombre
    private String surname; //apellido
    private String ci; //carnet
    private String reserve_status;
    private String chatid;

    public ClientDto(String name, String surname, String ci, String reserve_status, String chatid) {
        this.name = name;
        this.surname = surname;
        this.ci = ci;
        this.reserve_status = reserve_status;
        this.chatid = chatid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getReserve_status() {
        return reserve_status;
    }

    public void setReserve_status(String reserve_status) {
        this.reserve_status = reserve_status;
    }

    public String getChatid() {
        return chatid;
    }

    public void setChatid(String chatid) {
        this.chatid = chatid;
    }

    @Override
    public String toString() {
        return "PermissionDto2{" +
                "startDate='" + name + '\'' +
                ", endDate='" + surname + '\'' +
                ", reason='" + ci + '\'' +
                '}';
    }
}
