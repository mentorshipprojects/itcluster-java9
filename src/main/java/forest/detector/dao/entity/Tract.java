package forest.detector.dao.entity;

import java.util.Objects;

public class Tract {
    private int id;
    private int ticketID;
    private String quarter;
    private String division;
    private String range;
    private float area;
    private String forestType;
    private float generalAllowedExtent;
    private float allowedExtent;
    private String cuttingStatus;
    private String contributor;
    private String mapId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public String getForestType() {
        return forestType;
    }

    public void setForestType(String forestType) {
        this.forestType = forestType;
    }

    public float getGeneralAllowedExtent() {
        return generalAllowedExtent;
    }

    public void setGeneralAllowedExtent(float generalAllowedExtent) {
        this.generalAllowedExtent = generalAllowedExtent;
    }

    public float getAllowedExtent() {
        return allowedExtent;
    }

    public void setAllowedExtent(float allowedExtent) {
        this.allowedExtent = allowedExtent;
    }

    public String getCuttingStatus() {
        return cuttingStatus;
    }

    public void setCuttingStatus(String cuttingStatus) {
        this.cuttingStatus = cuttingStatus;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    @Override
    public String toString() {
        return "Tract{" +
                "id=" + id +
                ", ticketID=" + ticketID +
                ", quarter='" + quarter + '\'' +
                ", division='" + division + '\'' +
                ", range='" + range + '\'' +
                ", area=" + area +
                ", forestType='" + forestType + '\'' +
                ", generalAllowedExtent=" + generalAllowedExtent +
                ", allowedExtent=" + allowedExtent +
                ", cuttingStatus='" + cuttingStatus + '\'' +
                ", contributor='" + contributor + '\'' +
                ", mapId='" + mapId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tract tract = (Tract) o;
        return id == tract.id &&
                ticketID == tract.ticketID &&
                Float.compare(tract.area, area) == 0 &&
                Float.compare(tract.generalAllowedExtent, generalAllowedExtent) == 0 &&
                Float.compare(tract.allowedExtent, allowedExtent) == 0 &&
                Objects.equals(quarter, tract.quarter) &&
                Objects.equals(division, tract.division) &&
                Objects.equals(range, tract.range) &&
                Objects.equals(forestType, tract.forestType) &&
                Objects.equals(cuttingStatus, tract.cuttingStatus) &&
                Objects.equals(contributor, tract.contributor) &&
                Objects.equals(mapId, tract.mapId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ticketID, quarter, division, range, area, forestType, generalAllowedExtent, allowedExtent, cuttingStatus, contributor, mapId);
    }
}
