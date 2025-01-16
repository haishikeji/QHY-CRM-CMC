package cn.px.system.domain;

import java.util.List;
import java.util.Map;

/**
 * @author:terry
 * @create: 2023-12-27 11:00
 * @Description: 区县统计数据
 */
public class CountyHomeData {

    private List<CountyDetailVo> countyList;

    private  List<CenterRankListVo> centerRankInfos;

    private Map<String, List<CountyBusinessRankVo>> businessMap;

    private CountyBusinessTableVo countyBusinessTableVo;

    private String recordDate;
    public List<CountyDetailVo> getCountyList() {
        return countyList;
    }

    public void setCountyList(List<CountyDetailVo> countyList) {
        this.countyList = countyList;
    }

    public Map<String, List<CountyBusinessRankVo>> getBusinessMap() {
        return businessMap;
    }

    public void setBusinessMap(Map<String, List<CountyBusinessRankVo>> businessMap) {
        this.businessMap = businessMap;
    }

    public List<CenterRankListVo> getCenterRankInfos() {
        return centerRankInfos;
    }

    public void setCenterRankInfos(List<CenterRankListVo> centerRankInfos) {
        this.centerRankInfos = centerRankInfos;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public CountyBusinessTableVo getCountyBusinessTableVo() {
        return countyBusinessTableVo;
    }

    public void setCountyBusinessTableVo(CountyBusinessTableVo countyBusinessTableVo) {
        this.countyBusinessTableVo = countyBusinessTableVo;
    }
}
