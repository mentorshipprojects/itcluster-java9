package forest.detector.dao.entity;

import java.util.Objects;

public class Stat {
    private float area;
    private long generalAllowedExtent;
    private String statName;

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public long getGeneralAllowedExtent() {
        return generalAllowedExtent;
    }

    public void setGeneralAllowedExtent(long generalAllowedExtent) {
        this.generalAllowedExtent = generalAllowedExtent;
    }

    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "area=" + area +
                ", generalAllowedExtent=" + generalAllowedExtent +
                ", statName='" + statName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stat stat = (Stat) o;
        return Float.compare(stat.area, area) == 0 &&
                generalAllowedExtent == stat.generalAllowedExtent &&
                Objects.equals(statName, stat.statName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(area, generalAllowedExtent, statName);
    }
}
