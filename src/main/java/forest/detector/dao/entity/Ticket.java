package forest.detector.dao.entity;

import java.sql.Date;
import java.util.Objects;

public class Ticket {
    private String number;
    private String region;
    private String forestUser;
    private Date startDate;
    private Date finishDate;
    private String forestry;
    private String cuttingType;
    private String ticketStatus;
    private String cuttingStatus;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getForestUser() {
        return forestUser;
    }

    public void setForestUser(String forestUser) {
        this.forestUser = forestUser;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getForestry() {
        return forestry;
    }

    public void setForestry(String forestry) {
        this.forestry = forestry;
    }

    public String getCuttingType() {
        return cuttingType;
    }

    public void setCuttingType(String cuttingType) {
        this.cuttingType = cuttingType;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getCuttingStatus() {
        return cuttingStatus;
    }

    public void setCuttingStatus(String cuttingStatus) {
        this.cuttingStatus = cuttingStatus;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "number='" + number + '\'' +
                ", region='" + region + '\'' +
                ", forestUser='" + forestUser + '\'' +
                ", startDate='" + startDate + '\'' +
                ", finishDate='" + finishDate + '\'' +
                ", forestry='" + forestry + '\'' +
                ", cuttingType='" + cuttingType + '\'' +
                ", ticketStatus='" + ticketStatus + '\'' +
                ", cuttingStatus='" + cuttingStatus + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(number, ticket.number) &&
                Objects.equals(region, ticket.region) &&
                Objects.equals(forestUser, ticket.forestUser) &&
                Objects.equals(startDate, ticket.startDate) &&
                Objects.equals(finishDate, ticket.finishDate) &&
                Objects.equals(forestry, ticket.forestry) &&
                Objects.equals(cuttingType, ticket.cuttingType) &&
                Objects.equals(ticketStatus, ticket.ticketStatus) &&
                Objects.equals(cuttingStatus, ticket.cuttingStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, region, forestUser, startDate, finishDate, forestry, cuttingType, ticketStatus, cuttingStatus);
    }
}
