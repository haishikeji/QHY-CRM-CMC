package cn.px.system.domain;

public enum LocalEnum {
    高新派出所("高新派出所",104),
    艾溪湖派出所("艾溪湖派出所",105),
    瑶湖派出所("瑶湖派出所",106),
    麻丘派出所("麻丘派出所",107),
    昌东派出所("昌东派出所",200),
    光伏产业园派出所("光伏产业园派出所",201),
    航空城派出所("航空城派出所",202),
    鲤鱼洲派出所("鲤鱼洲派出所",203);

    private String localPoliceStation;

    private Integer localId;

    private LocalEnum(String localPoliceStation, Integer localId)
    {
        this.localPoliceStation = localPoliceStation;
        this.localId = localId;
    }

    public String getLocalPoliceStation() {
        return localPoliceStation;
    }

    public void setLocalPoliceStation(String localPoliceStation) {
        this.localPoliceStation = localPoliceStation;
    }

    public Integer getLocalId() {
        return localId;
    }

    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    public static boolean checkLocal(int localId)
    {
        for (LocalEnum item : LocalEnum.values())
        {

            if (item.getLocalId().intValue() == localId)
            {
                return true;
            }

        }
        return false;
    }
}
