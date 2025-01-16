package cn.px.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * @author:terry
 * @create: 2024-05-12 06:11
 * @Description: app出租房提交
 */
public class RentalHousingInfoBaseVo {

    private Integer record_type;

    private String belonging_place;

    private String community;

    private String address_info;

    private String host_name;

    private String host_card_id;

    private String host_tel;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rental_begin_date;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date rental_end_date;

    private String tenant_name;

    private String tenant_card_id;

    private String tenant_tel;

    private List<String> urls;

    public Integer getRecord_type() {
        return record_type;
    }

    public void setRecord_type(Integer record_type) {
        this.record_type = record_type;
    }

    public String getBelonging_place() {
        return belonging_place;
    }

    public void setBelonging_place(String belonging_place) {
        this.belonging_place = belonging_place;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getAddress_info() {
        return address_info;
    }

    public void setAddress_info(String address_info) {
        this.address_info = address_info;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public String getHost_card_id() {
        return host_card_id;
    }

    public void setHost_card_id(String host_card_id) {
        this.host_card_id = host_card_id;
    }

    public String getHost_tel() {
        return host_tel;
    }

    public void setHost_tel(String host_tel) {
        this.host_tel = host_tel;
    }

    public Date getRental_begin_date() {
        return rental_begin_date;
    }

    public void setRental_begin_date(Date rental_begin_date) {
        this.rental_begin_date = rental_begin_date;
    }

    public Date getRental_end_date() {
        return rental_end_date;
    }

    public void setRental_end_date(Date rental_end_date) {
        this.rental_end_date = rental_end_date;
    }

    public String getTenant_name() {
        return tenant_name;
    }

    public void setTenant_name(String tenant_name) {
        this.tenant_name = tenant_name;
    }

    public String getTenant_card_id() {
        return tenant_card_id;
    }

    public void setTenant_card_id(String tenant_card_id) {
        this.tenant_card_id = tenant_card_id;
    }

    public String getTenant_tel() {
        return tenant_tel;
    }

    public void setTenant_tel(String tenant_tel) {
        this.tenant_tel = tenant_tel;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
