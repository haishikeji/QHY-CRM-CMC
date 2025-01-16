package cn.px.system.domain;

import java.util.List;
import java.util.Date;

import cn.px.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.px.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 出租房信息对象 rental_housing_info
 *
 * @author 品讯科技
 * @date 2024-08
 */
public class RentalHousingInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 归属地 */
    @Excel(name = "归属地")
    private String belongingPlace;

    /** 小区 */
    @Excel(name = "小区")
    private String community;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String addressInfo;

    /** 房主姓名 */
    @Excel(name = "房主姓名")
    private String hostName;

    /** 房主身份证号 */
    @Excel(name = "房主身份证号")
    private String hostCardId;

    /** 房主电话 */
    @Excel(name = "房主电话")
    private String hostTel;

    /** 租赁起始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "租赁起始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rentalBeginDate;

    /** 租赁截至日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "租赁截至日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rentalEndDate;

    /** 租客姓名 */
    @Excel(name = "租客姓名")
    private String tenantName;

    /** 租客省份证号 */
    @Excel(name = "租客省份证号")
    private String tenantCardId;

    /** 租客手机号 */
    @Excel(name = "租客手机号")
    private String tenantTel;

    /** 记录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordDate;

    /** 派出所名称 */
    @Excel(name = "派出所名称")
    private String localPoliceStation;

    /** 派出所id */
    @Excel(name = "派出所id")
    private Long deptId;

    /** 上报类型：0:出租人;1:承租人 */
    @Excel(name = "上报类型：0:出租人;1:承租人")
    private String recordType;

    /** 租赁合同1 */
    @Excel(name = "租赁合同1")
    private String attachmentOne;

    /** 租赁合同2 */
    @Excel(name = "租赁合同2")
    private String attachmentTwo;

    /** 租客信息信息 */
    private List<TenantUserInfo> tenantUserInfoList;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setBelongingPlace(String belongingPlace)
    {
        this.belongingPlace = belongingPlace;
    }

    public String getBelongingPlace()
    {
        return belongingPlace;
    }
    public void setCommunity(String community)
    {
        this.community = community;
    }

    public String getCommunity()
    {
        return community;
    }
    public void setAddressInfo(String addressInfo)
    {
        this.addressInfo = addressInfo;
    }

    public String getAddressInfo()
    {
        return addressInfo;
    }
    public void setHostName(String hostName)
    {
        this.hostName = hostName;
    }

    public String getHostName()
    {
        return hostName;
    }
    public void setHostCardId(String hostCardId)
    {
        this.hostCardId = hostCardId;
    }

    public String getHostCardId()
    {
        return hostCardId;
    }
    public void setHostTel(String hostTel)
    {
        this.hostTel = hostTel;
    }

    public String getHostTel()
    {
        return hostTel;
    }
    public void setRentalBeginDate(Date rentalBeginDate)
    {
        this.rentalBeginDate = rentalBeginDate;
    }

    public Date getRentalBeginDate()
    {
        return rentalBeginDate;
    }
    public void setRentalEndDate(Date rentalEndDate)
    {
        this.rentalEndDate = rentalEndDate;
    }

    public Date getRentalEndDate()
    {
        return rentalEndDate;
    }
    public void setTenantName(String tenantName)
    {
        this.tenantName = tenantName;
    }

    public String getTenantName()
    {
        return tenantName;
    }
    public void setTenantCardId(String tenantCardId)
    {
        this.tenantCardId = tenantCardId;
    }

    public String getTenantCardId()
    {
        return tenantCardId;
    }
    public void setTenantTel(String tenantTel)
    {
        this.tenantTel = tenantTel;
    }

    public String getTenantTel()
    {
        return tenantTel;
    }
    public void setRecordDate(Date recordDate)
    {
        this.recordDate = recordDate;
    }

    public Date getRecordDate()
    {
        return recordDate;
    }
    public void setLocalPoliceStation(String localPoliceStation)
    {
        this.localPoliceStation = localPoliceStation;
    }

    public String getLocalPoliceStation()
    {
        return localPoliceStation;
    }
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }
    public void setRecordType(String recordType)
    {
        this.recordType = recordType;
    }

    public String getRecordType()
    {
        return recordType;
    }
    public void setAttachmentOne(String attachmentOne)
    {
        this.attachmentOne = attachmentOne;
    }

    public String getAttachmentOne()
    {
        return attachmentOne;
    }
    public void setAttachmentTwo(String attachmentTwo)
    {
        this.attachmentTwo = attachmentTwo;
    }

    public String getAttachmentTwo()
    {
        return attachmentTwo;
    }

    public List<TenantUserInfo> getTenantUserInfoList()
    {
        return tenantUserInfoList;
    }

    public void setTenantUserInfoList(List<TenantUserInfo> tenantUserInfoList)
    {
        this.tenantUserInfoList = tenantUserInfoList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("belongingPlace", getBelongingPlace())
            .append("community", getCommunity())
            .append("addressInfo", getAddressInfo())
            .append("hostName", getHostName())
            .append("hostCardId", getHostCardId())
            .append("hostTel", getHostTel())
            .append("rentalBeginDate", getRentalBeginDate())
            .append("rentalEndDate", getRentalEndDate())
            .append("tenantName", getTenantName())
            .append("tenantCardId", getTenantCardId())
            .append("tenantTel", getTenantTel())
            .append("recordDate", getRecordDate())
            .append("localPoliceStation", getLocalPoliceStation())
            .append("deptId", getDeptId())
            .append("recordType", getRecordType())
            .append("attachmentOne", getAttachmentOne())
            .append("attachmentTwo", getAttachmentTwo())
            .append("tenantUserInfoList", getTenantUserInfoList())
            .toString();
    }
}
