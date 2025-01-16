package cn.px.web.controller.system;

import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import cn.px.common.constant.HttpStatus;
import cn.px.common.core.domain.entity.dto.CmccUserTaskDto;
import cn.px.common.core.domain.entity.dto.ImportErrorItem;
import cn.px.common.core.domain.entity.dto.UserTaskTemplate;
import cn.px.common.core.domain.model.LoginUser;
import cn.px.common.utils.DateUtils;
import cn.px.common.utils.StringUtils;
import cn.px.system.domain.*;
import cn.px.system.domain.*;
import cn.px.system.service.ICmccGroupInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.px.common.annotation.Log;
import cn.px.common.core.controller.BaseController;
import cn.px.common.core.domain.AjaxResult;
import cn.px.common.enums.BusinessType;
import cn.px.system.service.ICmccUserTaskService;
import cn.px.common.utils.poi.ExcelUtil;
import cn.px.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户任务Controller
 *
 * @author 品讯科技
 * @date 2024-08
 */
@RestController
@RequestMapping("/customer/cmccusertask")
public class CmccUserTaskController extends BaseController
{
    @Autowired
    private ICmccUserTaskService cmccUserTaskService;

    @Autowired
    private ICmccGroupInfoService cmccGroupInfoService;


    public boolean checkIsCounty()
    {
        LoginUser loginUser = getLoginUser();
        boolean b =  false;
        if (loginUser.getUser().getDept() != null) {
            b = AreaTypeEnum.东湖区.checkAreaId(loginUser.getUser().getDept().getDeptId());
            if (!b && loginUser.getUser().getDept().getParentId() != null) {
                b = AreaTypeEnum.东湖区.checkAreaId(loginUser.getUser().getDept().getParentId());
            }
        }
        return b;
    }


    @PostMapping("/appenterprisetask")
    public TableDataInfo appenterprisetask(@RequestBody CmccAppTaskQueryVo query) {

        LoginUser loginUser = getLoginUser();
        TableDataInfo rspData = new TableDataInfo();
        if (loginUser == null){
            rspData.setCode(HttpStatus.ERROR);
            rspData.setMsg("当前用户未登录");
            return rspData;
        }

        if (query.getGroupId() == null) {
            rspData.setCode(HttpStatus.ERROR);
            rspData.setMsg("园区不能为空");
            return rspData;
        }
        startPage();
        List<AppEnterpriseTask> finalEnterpriseTasks = cmccUserTaskService.filterEnterpriseTasks(loginUser.getUserId(), query);

        return getDataTable(finalEnterpriseTasks);
    }

    @GetMapping("/appweektasklist")
    public AjaxResult appweektasklist() {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return AjaxResult.error("当前用户未登录");
        }

        List<AppGroupTaskList> appGroupTaskLists = cmccUserTaskService.showAppGroupTaskList(loginUser);
        return AjaxResult.success(appGroupTaskLists);
    }

    /**
     * 查询用户任务列表
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccusertask:list')")
    @GetMapping("/list")
    public TableDataInfo list(CmccUserTask cmccUserTask)
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null){
            TableDataInfo rspData = new TableDataInfo();
            rspData.setCode(HttpStatus.ERROR);
            rspData.setMsg("未获取到登录信息");
            return rspData;
        }
        startPage();
        if (checkIsCounty() && cmccUserTask.getDeptId()== null) {
            cmccUserTask.setDeptId(loginUser.getDeptId());
        }
        List<CmccUserTask> list = cmccUserTaskService.selectCmccUserTaskList(cmccUserTask);
        return getDataTable(list);
    }

    /**
     * 导出用户任务列表
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccusertask:export')")
    @Log(title = "用户任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CmccUserTask cmccUserTask)
    {
        List<CmccUserTask> list = cmccUserTaskService.selectCmccUserTaskList(cmccUserTask);
        ExcelUtil<CmccUserTask> util = new ExcelUtil<CmccUserTask>(CmccUserTask.class);
        util.exportExcel(response, list, "用户任务数据");
    }


    /**
     * 统计导出用户任务列表
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccusertask:statisExport')")
    @Log(title = "统计导出用户任务", businessType = BusinessType.EXPORT)
    @PostMapping("/statisExport")
    public void statisExport(HttpServletResponse response, CmccUserTask cmccUserTask)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("deptId", cmccUserTask.getDeptId());
        map.put("groupId", cmccUserTask.getGroupId());
        map.put("userName", cmccUserTask.getUserName());
        map.put("groupType", cmccUserTask.getGroupType());

        List<StatisExportTask> list = cmccUserTaskService.selectStatisExportTask(map);
        ExcelUtil<StatisExportTask> util = new ExcelUtil<StatisExportTask>(StatisExportTask.class);
        util.exportExcel(response, list, "统计导出用户任务列表");
    }


    /**
     * 获取用户任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccusertask:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(cmccUserTaskService.selectCmccUserTaskById(id));
    }

    /**
     * 新增用户任务
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccusertask:add')")
    @Log(title = "用户任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CmccUserTask cmccUserTask)
    {

        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return AjaxResult.error("用户未登录");
        }

        if (cmccUserTask.getGroupId() == null){
            return AjaxResult.error("集团不能为空");
        }

        CmccGroupInfo cmccGroupInfo = cmccGroupInfoService.selectCmccGroupInfoById(cmccUserTask.getGroupId());
        if (cmccGroupInfo == null){
            return AjaxResult.error("集团不能为空");
        }


        updateTaskTime(cmccUserTask);
        cmccUserTask.setTaskStatus("0");
        cmccUserTask.setGroupType(cmccGroupInfo.getGroupType());
        cmccUserTask.setCreateUserId(loginUser.getUserId());
        cmccUserTask.setCreateUserName(loginUser.getUser().getNickName());
        return toAjax(cmccUserTaskService.insertCmccUserTask(cmccUserTask));
    }

    private void updateTaskTime(CmccUserTask cmccUserTask) {
        if (cmccUserTask.getTaskType() != null) {
            if (0 == cmccUserTask.getTaskType().intValue()) {
                cmccUserTask.setTaskBeginTime(DateUtils.getCurrentWeekFirstDayTime());
                cmccUserTask.setTaskEndTime(DateUtils.getCurrentWeekEndDayTime());
            } else if (1 == cmccUserTask.getTaskType().intValue()) {
                cmccUserTask.setTaskBeginTime(DateUtils.getCurrentDayTime());
                cmccUserTask.setTaskEndTime(DateUtils.getNextDayTime(1));
            } else if (2 == cmccUserTask.getTaskType().intValue()) {
                cmccUserTask.setTaskBeginTime(DateUtils.getCurrentDayTime());
                cmccUserTask.setTaskEndTime(DateUtils.getNextDayTime(3));
            }
        }
    }

    /**
     * 修改用户任务
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccusertask:edit')")
    @Log(title = "用户任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CmccUserTask cmccUserTask)
    {

        updateTaskTime(cmccUserTask);
        return toAjax(cmccUserTaskService.updateCmccUserTask(cmccUserTask));
    }

    /**
     * 删除用户任务
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccusertask:remove')")
    @Log(title = "用户任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cmccUserTaskService.deleteCmccUserTaskByIds(ids));
    }

    /**
     * 批量导入用户任务
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccusertask:add')")
    @Log(title = "用户任务", businessType = BusinessType.INSERT)
    @PostMapping("/addBatch")
    public AjaxResult addBatch(MultipartFile file, boolean updateSupport){
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return AjaxResult.error("用户未登录");
        }
        List<ImportErrorItem> importErrorItems = cmccUserTaskService.importUserTask(file, loginUser);
        return AjaxResult.success(importErrorItems);
    }

    /**
     * 模板下载
     */
    @PostMapping("/importTemplate")
    @PreAuthorize("@ss.hasPermi('customer:cmccusertask:export')")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<UserTaskTemplate> util = new ExcelUtil<>(UserTaskTemplate.class);
        util.importTemplateExcel(response, "user_task_template");
    }

    /**
     * 新增用户任务
     */
    @PreAuthorize("@ss.hasPermi('customer:cmccusertask:add')")
    @Log(title = "用户任务", businessType = BusinessType.INSERT)
    @PostMapping("/addBatchUserTasks")
    public AjaxResult addBatchUserTasks(@RequestBody CmccUserTaskDto cmccUserTaskDto)
    {

        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            return AjaxResult.error("用户未登录");
        }

        if (cmccUserTaskDto.getGroupIds() == null || cmccUserTaskDto.getGroupIds().size() == 0){
            return AjaxResult.error("集团不能为空");
        }
        if (cmccUserTaskDto.getUserId() == null){
            return AjaxResult.error("客户经理不能为空");
        }
        List<CmccUserTask> userTasks = new ArrayList<>();
        List<CmccGroupInfo> cmccGroupInfos = cmccGroupInfoService.selectCmccGroupInfoByIds(cmccUserTaskDto.getGroupIds());

        Map<Long, CmccGroupInfo> cmccGroupInfoMap =new HashMap<>();
        if (!StringUtils.isEmpty(cmccGroupInfos)){
            cmccGroupInfoMap = cmccGroupInfos.stream()
                    .collect(Collectors.toMap(CmccGroupInfo::getId, cmccGroupInfo -> cmccGroupInfo));
        }


        Map<Long, CmccGroupInfo> finalCmccGroupInfoMap = cmccGroupInfoMap;
        cmccUserTaskDto.getGroupIds().forEach(id -> {
            CmccUserTask cmccUserTask = new CmccUserTask();
            cmccUserTask.setDeptId(cmccUserTaskDto.getDeptId());
            cmccUserTask.setUserId(cmccUserTaskDto.getUserId());
            cmccUserTask.setUserName(cmccUserTaskDto.getUserName());
            cmccUserTask.setGroupId(id);
            cmccUserTask.setTaskStatus("0");
            cmccUserTask.setCreateUserId(loginUser.getUserId());
            cmccUserTask.setCreateUserName(loginUser.getUser().getNickName());
            cmccUserTask.setTaskType(cmccUserTaskDto.getTaskType());
            cmccUserTask.setCreateTime(new Date());
            updateTaskTime(cmccUserTask);

            // 从 Map 中获取对应的 CmccGroupInfo
            CmccGroupInfo cmccGroupInfo = finalCmccGroupInfoMap.get(id);
            if (cmccGroupInfo != null) {
                cmccUserTask.setDeptName(cmccGroupInfo.getDeptName());
                cmccUserTask.setGroupName(cmccGroupInfo.getGroupName());
                cmccUserTask.setGroupType(cmccGroupInfo.getGroupType());
            }

            userTasks.add(cmccUserTask);
        });
        cmccUserTaskService.insertBatchCmccUserTask(userTasks);
        return success();
    }


}
