package cn.px.system;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * @author:terry
 * @create: 2024-05-08 02:00
 * @Description: 前端列表
 */
public class AppInspectionTaskInfoList {

    private Long id;

    private String name;

    private String area1;

    private String area2;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    private List<String> urls1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea1() {
        return area1;
    }

    public void setArea1(String area1) {
        this.area1 = area1;
    }

    public String getArea2() {
        return area2;
    }

    public void setArea2(String area2) {
        this.area2 = area2;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<String> getUrls1() {
        return urls1;
    }

    public void setUrls1(List<String> urls1) {
        this.urls1 = urls1;
    }
}
