package cn.px.system.domain;

import cn.px.common.utils.StringUtils;

public enum WorkTypeEnum {

    治安隐患("1", "治安隐患"),
    安全生产隐患("2", "安全生产隐患"),
    消防安全隐患("3", "消防安全隐患"),
    矛盾纠纷排除("4", "矛盾纠纷排除"),
    违规经营("5", "违规经营"),
    车辆违规停放("6","车辆违规停放");
    private String workType;

    private String typeName;

    private WorkTypeEnum(String workType, String typeName)
    {
        this.workType = workType;
        this.typeName = typeName;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public static String getTypeByName(String name)
    {
        if (StringUtils.isEmpty(name))
        {
            return "";
        }
        for (WorkTypeEnum item : WorkTypeEnum.values())
        {
            if (name.equals(item.getTypeName()))
            {
                return item.getWorkType();
            }
        }
        return "";
    }

    public static String getNameByType(String type)
    {
        if (StringUtils.isEmpty(type))
        {
            return "";
        }
        for (WorkTypeEnum item : WorkTypeEnum.values())
        {
            if (type.equals(item.getWorkType()))
            {
                return item.getTypeName();
            }
        }
        return "";
    }
}
