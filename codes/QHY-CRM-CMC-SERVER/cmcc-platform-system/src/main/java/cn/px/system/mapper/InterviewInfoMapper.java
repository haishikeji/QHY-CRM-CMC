package cn.px.system.mapper;

import java.util.List;
import java.util.Map;

import cn.px.system.domain.*;
import cn.px.system.domain.*;

/**
 * 访问记录Mapper接口
 *
 * @author 品讯科技
 * @date 2024-08
 */
public interface InterviewInfoMapper
{

    public List<InterviewCountResult> selectCountInterview(Map<String, Object> map);

    public List<InterviewRankResult> selectRankInterview(InterviewInfo interviewInfo);

    public AppCountInfo selectAppCountInfo(Map<String, Object> map);
    /**
     * 查询访问记录
     *
     * @param id 访问记录主键
     * @return 访问记录
     */
    public InterviewInfo selectInterviewInfoById(Long id);

    /**
     * 查询访问记录列表
     *
     * @param interviewInfo 访问记录
     * @return 访问记录集合
     */
    public List<InterviewInfo> selectInterviewInfoList(InterviewInfo interviewInfo);

    public List<InterviewInfo> selectInterviewInfoListAll(InterviewInfo interviewInfo);

    public int updateInterviewInfoRepeatFlag(InterviewInfo interviewInfo);

    /**
     * 新增访问记录
     *
     * @param interviewInfo 访问记录
     * @return 结果
     */
    public int insertInterviewInfo(InterviewInfo interviewInfo);

    /**
     * 修改访问记录
     *
     * @param interviewInfo 访问记录
     * @return 结果
     */
    public int updateInterviewInfo(InterviewInfo interviewInfo);

    /**
     * 删除访问记录
     *
     * @param id 访问记录主键
     * @return 结果
     */
    public int deleteInterviewInfoById(Long id);

    /**
     * 批量删除访问记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInterviewInfoByIds(Long[] ids);

    /**
     * 批量删除房东访问
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTenantInteviewRecordByInteviewIds(Long[] ids);

    /**
     * 批量新增房东访问
     *
     * @param tenantInteviewRecordList 房东访问列表
     * @return 结果
     */
    public int batchTenantInteviewRecord(List<TenantInteviewRecord> tenantInteviewRecordList);


    /**
     * 通过访问记录主键删除房东访问信息
     *
     * @param id 访问记录ID
     * @return 结果
     */
    public int deleteTenantInteviewRecordByInteviewId(Long id);
}
