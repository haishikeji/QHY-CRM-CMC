package cn.px.system.domain;

/**
 * @author:terry
 * @create: 2023-12-26 09:29
 * @Description: 返乡排行记录
 */
public class ReturnRankRecord {

    public ReturnRankRecord (int rank, String name, int total)
    {
        this.rank = rank;
        this.name = name;
        this.total = total;
    }

    public ReturnRankRecord(){}
    private Integer rank;

    private String name;

    private Integer total;

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
