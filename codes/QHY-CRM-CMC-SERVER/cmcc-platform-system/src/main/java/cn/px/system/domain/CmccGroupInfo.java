package cn.px.system.domain;

import cn.px.common.annotation.Excel;
import cn.px.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 楼宇信息维护对象 cmcc_group_info
 *
 * @author 品讯科技
 * @date 2024-08
 */
public class CmccGroupInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String groupName;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptName;

    /** 类型 */
    @Excel(name = "类型")
    private String groupType;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String groupAddr;


    private String deptIdStr;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public String getGroupName()
    {
        return groupName;
    }
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }
    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public String getDeptName()
    {
        return deptName;
    }
    public void setGroupType(String groupType)
    {
        this.groupType = groupType;
    }

    public String getGroupType()
    {
        return groupType;
    }

    public String getGroupAddr() {
        return groupAddr;
    }

    public void setGroupAddr(String groupAddr) {
        this.groupAddr = groupAddr;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("groupName", getGroupName())
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .append("groupType", getGroupType())
            .toString();
    }
}
