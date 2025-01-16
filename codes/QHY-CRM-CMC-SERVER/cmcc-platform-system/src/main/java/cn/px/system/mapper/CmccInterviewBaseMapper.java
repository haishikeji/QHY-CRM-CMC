package cn.px.system.mapper;

import java.util.List;
import java.util.Map;

import cn.px.system.domain.*;
import cn.px.system.domain.*;

/**
 * 用户走访Mapper接口
 *
 * @author 品讯科技
 * @date 2024-08
 */
public interface CmccInterviewBaseMapper
{
    /**
     * 查询用户走访
     *
     * @param id 用户走访主键
     * @return 用户走访
     */
    public CmccInterviewBase selectCmccInterviewBaseById(Long id);

    public Long selectLeastByEnterprise(Long id);

    public AppCountInfo selectAppCountInfo(Map<String, Object> map);

    /**
     * 查询用户走访列表
     *
     * @param cmccInterviewBase 用户走访
     * @return 用户走访集合
     */
    public List<CmccInterviewBase> selectCmccInterviewBaseList(CmccInterviewBase cmccInterviewBase);

    /**
     * 新增用户走访
     *
     * @param cmccInterviewBase 用户走访
     * @return 结果
     */
    public int insertCmccInterviewBase(CmccInterviewBase cmccInterviewBase);

    /**
     * 修改用户走访
     *
     * @param cmccInterviewBase 用户走访
     * @return 结果
     */
    public int updateCmccInterviewBase(CmccInterviewBase cmccInterviewBase);

    /**
     * 删除用户走访
     *
     * @param id 用户走访主键
     * @return 结果
     */
    public int deleteCmccInterviewBaseById(Long id);

    /**
     * 批量删除用户走访
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmccInterviewBaseByIds(Long[] ids);

    /**
     * 批量删除客户走访线路信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCmccInterviewNetByInterviewIds(Long[] ids);

    /**
     * 批量新增客户走访线路信息
     *
     * @param cmccInterviewNetList 客户走访线路信息列表
     * @return 结果
     */
    public int batchCmccInterviewNet(List<CmccInterviewNet> cmccInterviewNetList);


    /**
     * 通过用户走访主键删除客户走访线路信息信息
     *
     * @param id 用户走访ID
     * @return 结果
     */
    public int deleteCmccInterviewNetByInterviewId(Long id);

    /**
     * 获取所选择时间范围内所属部门员工走访次数排行榜
     *
     * @param queryInfo
     * @return
     */
    List<InterviewCountResult> selectCmccInterviewBaseByTimeAndDeptId(InterviewInfo queryInfo);

    /**
     * 根据userTasks查找时间范围内已经访问的企业信息
     *
     * @param cmccUserTasks 用户任务集
     * @return 结果
     */
    List<CmccInterviewBase> selectInterviewedAppEnterpriseTasksByDateRange(List<CmccUserTask> cmccUserTasks);

}
