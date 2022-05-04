package bo.edu.ucb.ingsoft.bot.dto;

import java.util.Date;

public class ReserveDto {
    private int id;
    private int client_id;
    private int subsidiary_id;
    private String status;
    private Date date;

    public ReserveDto(int id, int client_id, int subsidiary_id, String status, Date date) {
        this.id = id;
        this.client_id = client_id;
        this.subsidiary_id = subsidiary_id;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getSubsidiary_id() {
        return subsidiary_id;
    }

    public void setSubsidiary_id(int subsidiary_id) {
        this.subsidiary_id = subsidiary_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
