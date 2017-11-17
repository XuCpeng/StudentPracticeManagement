package cn.medemede.j2ee.model;

import javax.persistence.*;

@Entity
@Table(name = "region", schema = "j2ee", catalog = "")
public class Region {
    private int regionId;
    private String regionCode;
    private String regionName;
    private double parentId;
    private double regionLevel;
    private double regionOrder;
    private String regionNameEn;
    private String regionShortnameEn;

    @Id
    @Column(name = "REGION_ID")
    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    @Basic
    @Column(name = "REGION_CODE")
    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    @Basic
    @Column(name = "REGION_NAME")
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Basic
    @Column(name = "PARENT_ID")
    public double getParentId() {
        return parentId;
    }

    public void setParentId(double parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "REGION_LEVEL")
    public double getRegionLevel() {
        return regionLevel;
    }

    public void setRegionLevel(double regionLevel) {
        this.regionLevel = regionLevel;
    }

    @Basic
    @Column(name = "REGION_ORDER")
    public double getRegionOrder() {
        return regionOrder;
    }

    public void setRegionOrder(double regionOrder) {
        this.regionOrder = regionOrder;
    }

    @Basic
    @Column(name = "REGION_NAME_EN")
    public String getRegionNameEn() {
        return regionNameEn;
    }

    public void setRegionNameEn(String regionNameEn) {
        this.regionNameEn = regionNameEn;
    }

    @Basic
    @Column(name = "REGION_SHORTNAME_EN")
    public String getRegionShortnameEn() {
        return regionShortnameEn;
    }

    public void setRegionShortnameEn(String regionShortnameEn) {
        this.regionShortnameEn = regionShortnameEn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region that = (Region) o;

        if (regionId != that.regionId) return false;
        if (Double.compare(that.parentId, parentId) != 0) return false;
        if (Double.compare(that.regionLevel, regionLevel) != 0) return false;
        if (Double.compare(that.regionOrder, regionOrder) != 0) return false;
        if (regionCode != null ? !regionCode.equals(that.regionCode) : that.regionCode != null) return false;
        if (regionName != null ? !regionName.equals(that.regionName) : that.regionName != null) return false;
        if (regionNameEn != null ? !regionNameEn.equals(that.regionNameEn) : that.regionNameEn != null) return false;
        if (regionShortnameEn != null ? !regionShortnameEn.equals(that.regionShortnameEn) : that.regionShortnameEn != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = regionId;
        result = 31 * result + (regionCode != null ? regionCode.hashCode() : 0);
        result = 31 * result + (regionName != null ? regionName.hashCode() : 0);
        temp = Double.doubleToLongBits(parentId);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(regionLevel);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(regionOrder);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (regionNameEn != null ? regionNameEn.hashCode() : 0);
        result = 31 * result + (regionShortnameEn != null ? regionShortnameEn.hashCode() : 0);
        return result;
    }
}
