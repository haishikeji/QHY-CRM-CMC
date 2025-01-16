package cn.px.system.domain;

import java.util.Date;

import cn.px.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.px.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 返乡人员明细对象 people_return_detail
 *
 * @author 品讯科技
 * @date 2024-08
 */
public class PeopleReturnDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private String id;

    /** 省份名称 */
    @Excel(name = "省份名称")
    private String provinceName;

    /** 城市名称 */
    @Excel(name = "城市名称")
    private String cityName;

    /** 返乡时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "返乡时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date returnTime;

    /** 返乡人数 */
    @Excel(name = "返乡人数")
    private Long returnNumber;

    /** 返乡区县 */
    @Excel(name = "返乡区县")
    private String returnCounty;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setProvinceName(String provinceName)
    {
        this.provinceName = provinceName;
    }

    public String getProvinceName()
    {
        return provinceName;
    }
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public String getCityName()
    {
        return cityName;
    }
    public void setReturnTime(Date returnTime)
    {
        this.returnTime = returnTime;
    }

    public Date getReturnTime()
    {
        return returnTime;
    }
    public void setReturnNumber(Long returnNumber)
    {
        this.returnNumber = returnNumber;
    }

    public Long getReturnNumber()
    {
        return returnNumber;
    }
    public void setReturnCounty(String returnCounty)
    {
        this.returnCounty = returnCounty;
    }

    public String getReturnCounty()
    {
        return returnCounty;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("provinceName", getProvinceName())
            .append("cityName", getCityName())
            .append("returnTime", getReturnTime())
            .append("returnNumber", getReturnNumber())
            .append("returnCounty", getReturnCounty())
            .toString();
    }
}
