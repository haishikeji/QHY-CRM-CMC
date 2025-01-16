package cn.px.system.service;

import java.util.List;
import cn.px.system.domain.CmccGroupInfo;

/**
 * 楼宇信息维护Service接口
 *
 * @author 品讯科技
 * @date 2024-08
 */
public interface ICmccGroupInfoService
{
    /**
     * 查询楼宇信息维护
     *
     * @param id 楼宇信息维护主键
     * @return 楼宇信息维护
     */
    public CmccGroupInfo selectCmccGroupInfoById(Long id);

    /**
     * 批量查询楼宇信息维护
     *
     * @param ids 楼宇信息维护主键集合
     * @return 楼宇信息维护
     */
    public List<CmccGroupInfo> selectCmccGroupInfoByIds( List<Long> ids);
    /**
     * 查询楼宇信息维护列表
     *
     * @param cmccGroupInfo 楼宇信息维护
     * @return 楼宇信息维护集合
     */
    public List<CmccGroupInfo> selectCmccGroupInfoList(CmccGroupInfo cmccGroupInfo);

    /**
     * 新增楼宇信息维护
     *
     * @param cmccGroupInfo 楼宇信息维护
     * @return 结果
     */
    public int insertCmccGroupInfo(CmccGroupInfo cmccGroupInfo);

    /**
     * 修改楼宇信息维护
     *
     * @param cmccGroupInfo 楼宇信息维护
     * @return 结果
     */
    public int updateCmccGroupInfo(CmccGroupInfo cmccGroupInfo);

    /**
     * 批量删除楼宇信息维护
     *
     * @param ids 需要删除的楼宇信息维护主键集合
     * @return 结果
     */
    public int deleteCmccGroupInfoByIds(Long[] ids);

    /**
     * 删除楼宇信息维护信息
     *
     * @param id 楼宇信息维护主键
     * @return 结果
     */
    public int deleteCmccGroupInfoById(Long id);
}
