package cn.px.system.service.impl;

import java.util.List;

import cn.px.system.domain.*;
import cn.px.system.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Map;

import cn.px.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import cn.px.system.mapper.InterviewInfoMapper;
import cn.px.system.service.IInterviewInfoService;

/**
 * 访问记录Service业务层处理
 *
 * @author 品讯科技
 * @date 2024-08
 */
@Service
public class InterviewInfoServiceImpl implements IInterviewInfoService
{
    @Autowired
    private InterviewInfoMapper interviewInfoMapper;

    @Override
    public List<InterviewCountResult> selectCountInterview(Map<String, Object> map)
    {
        return interviewInfoMapper.selectCountInterview(map);
    }

    @Override
    public AppCountInfo selectAppCountInfo(Map<String, Object> map)
    {
        return interviewInfoMapper.selectAppCountInfo(map);
    }

    /**
     * 查询访问记录
     *
     * @param id 访问记录主键
     * @return 访问记录
     */
    @Override
    public InterviewInfo selectInterviewInfoById(Long id)
    {
        return interviewInfoMapper.selectInterviewInfoById(id);
    }

    /**
     * 查询访问记录列表
     *
     * @param interviewInfo 访问记录
     * @return 访问记录
     */
    @Override
    public List<InterviewInfo> selectInterviewInfoList(InterviewInfo interviewInfo)
    {
        return interviewInfoMapper.selectInterviewInfoListAll(interviewInfo);
    }

    @Override
    public List<InterviewRankResult> selectRankInterview(InterviewInfo interviewInfo)
    {
        return interviewInfoMapper.selectRankInterview(interviewInfo);
    }

    @Override
    public int updateInterviewInfoRepeatFlag(InterviewInfo interviewInfo)
    {
        return interviewInfoMapper.updateInterviewInfoRepeatFlag(interviewInfo);
    }

    /**
     * 新增访问记录
     *
     * @param interviewInfo 访问记录
     * @return 结果
     */
    @Transactional
    @Override
    public int insertInterviewInfo(InterviewInfo interviewInfo)
    {
        int rows = interviewInfoMapper.insertInterviewInfo(interviewInfo);
        insertTenantInteviewRecord(interviewInfo);
        return rows;
    }

    /**
     * 修改访问记录
     *
     * @param interviewInfo 访问记录
     * @return 结果
     */
    @Transactional
    @Override
    public int updateInterviewInfo(InterviewInfo interviewInfo)
    {
        interviewInfoMapper.deleteTenantInteviewRecordByInteviewId(interviewInfo.getId());
        insertTenantInteviewRecord(interviewInfo);
        return interviewInfoMapper.updateInterviewInfo(interviewInfo);
    }

    /**
     * 批量删除访问记录
     *
     * @param ids 需要删除的访问记录主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteInterviewInfoByIds(Long[] ids)
    {
        interviewInfoMapper.deleteTenantInteviewRecordByInteviewIds(ids);
        return interviewInfoMapper.deleteInterviewInfoByIds(ids);
    }

    /**
     * 删除访问记录信息
     *
     * @param id 访问记录主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteInterviewInfoById(Long id)
    {
        interviewInfoMapper.deleteTenantInteviewRecordByInteviewId(id);
        return interviewInfoMapper.deleteInterviewInfoById(id);
    }

    /**
     * 新增房东访问信息
     *
     * @param interviewInfo 访问记录对象
     */
    public void insertTenantInteviewRecord(InterviewInfo interviewInfo)
    {
        List<TenantInteviewRecord> tenantInteviewRecordList = interviewInfo.getTenantInteviewRecordList();
        Long id = interviewInfo.getId();
        if (StringUtils.isNotNull(tenantInteviewRecordList))
        {
            List<TenantInteviewRecord> list = new ArrayList<TenantInteviewRecord>();
            for (TenantInteviewRecord tenantInteviewRecord : tenantInteviewRecordList)
            {
                tenantInteviewRecord.setInteviewId(id);
                list.add(tenantInteviewRecord);
            }
            if (list.size() > 0)
            {
                interviewInfoMapper.batchTenantInteviewRecord(list);
            }
        }
    }
}
