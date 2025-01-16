package cn.px.system.mapper;

import java.util.List;
import cn.px.system.domain.EnterpriseUserInfo;

/**
 * 联防员信息Mapper接口
 *
 * @author 品讯科技
 * @date 2024-08
 */
public interface EnterpriseUserInfoMapper
{
    /**
     * 查询联防员信息
     *
     * @param id 联防员信息主键
     * @return 联防员信息
     */
    public EnterpriseUserInfo selectEnterpriseUserInfoById(Long id);

    /**
     * 查询联防员信息列表
     *
     * @param enterpriseUserInfo 联防员信息
     * @return 联防员信息集合
     */
    public List<EnterpriseUserInfo> selectEnterpriseUserInfoList(EnterpriseUserInfo enterpriseUserInfo);

    /**
     * 新增联防员信息
     *
     * @param enterpriseUserInfo 联防员信息
     * @return 结果
     */
    public int insertEnterpriseUserInfo(EnterpriseUserInfo enterpriseUserInfo);

    /**
     * 修改联防员信息
     *
     * @param enterpriseUserInfo 联防员信息
     * @return 结果
     */
    public int updateEnterpriseUserInfo(EnterpriseUserInfo enterpriseUserInfo);

    /**
     * 删除联防员信息
     *
     * @param id 联防员信息主键
     * @return 结果
     */
    public int deleteEnterpriseUserInfoById(Long id);

    /**
     * 批量删除联防员信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEnterpriseUserInfoByIds(Long[] ids);
}
