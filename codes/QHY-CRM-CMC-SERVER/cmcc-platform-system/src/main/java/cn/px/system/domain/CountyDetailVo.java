package cn.px.system.domain;

/**
 * @author:terry
 * @create: 2023-12-28 06:41
 * @Description: 区县明细
 */
public class CountyDetailVo {

    private String countyId;

    private String countyName;

    private Integer returnPeople;

    private Integer contactPeople;

    private Integer noContactPeople;

    private Double conversionRate;

    private Integer sort;

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public Integer getReturnPeople() {
        return returnPeople;
    }

    public void setReturnPeople(Integer returnPeople) {
        this.returnPeople = returnPeople;
    }

    public Integer getContactPeople() {
        return contactPeople;
    }

    public void setContactPeople(Integer contactPeople) {
        this.contactPeople = contactPeople;
    }

    public Double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getNoContactPeople() {
        return noContactPeople;
    }

    public void setNoContactPeople(Integer noContactPeople) {
        this.noContactPeople = noContactPeople;
    }
}
