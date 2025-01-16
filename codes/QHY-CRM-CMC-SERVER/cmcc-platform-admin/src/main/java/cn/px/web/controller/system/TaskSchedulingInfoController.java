package cn.px.web.controller.system;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.px.common.core.domain.model.LoginUser;
import cn.px.system.domain.AppTaskSchedulingInfo;
import cn.px.system.service.EnterpriseUserInfoCacheService;
import cn.px.system.service.UnionEnterpriseConfigCacheService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.px.common.annotation.Log;
import cn.px.common.core.controller.BaseController;
import cn.px.common.core.domain.AjaxResult;
import cn.px.common.enums.BusinessType;
import cn.px.system.domain.TaskSchedulingInfo;
import cn.px.system.service.ITaskSchedulingInfoService;
import cn.px.common.utils.poi.ExcelUtil;
import cn.px.common.core.page.TableDataInfo;

/**
 * 值班配置Controller
 *
 * @author 品讯科技
 * @date 2024-08
 */
@RestController
@RequestMapping("/inspection/schedulinginfo")
public class TaskSchedulingInfoController extends BaseController
{
    @Autowired
    private ITaskSchedulingInfoService taskSchedulingInfoService;

    @Autowired
    private EnterpriseUserInfoCacheService enterpriseUserInfoCacheService;

    @Autowired
    private UnionEnterpriseConfigCacheService unionEnterpriseConfigCacheService;
    /**
     * 查询值班配置列表
     */
    @PreAuthorize("@ss.hasPermi('inspection:schedulinginfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(TaskSchedulingInfo taskSchedulingInfo)
    {
        startPage();
        List<TaskSchedulingInfo> list = taskSchedulingInfoService.selectTaskSchedulingInfoList(taskSchedulingInfo);
        return getDataTable(list);
    }

    /**
     * 导出值班配置列表
     */
    @PreAuthorize("@ss.hasPermi('inspection:schedulinginfo:export')")
    @Log(title = "值班配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TaskSchedulingInfo taskSchedulingInfo)
    {
        List<TaskSchedulingInfo> list = taskSchedulingInfoService.selectTaskSchedulingInfoList(taskSchedulingInfo);
        ExcelUtil<TaskSchedulingInfo> util = new ExcelUtil<TaskSchedulingInfo>(TaskSchedulingInfo.class);
        util.exportExcel(response, list, "值班配置数据");
    }

    /**
     * 获取值班配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('inspection:schedulinginfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(taskSchedulingInfoService.selectTaskSchedulingInfoById(id));
    }

    @GetMapping(value = "/applist")
    public AjaxResult getApplist(@RequestParam String date)
    {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null)
        {
            return AjaxResult.error("获取登录用户失败");
        }
        List <AppTaskSchedulingInfo> returnList = new ArrayList<>();
        TaskSchedulingInfo queryInfo = new TaskSchedulingInfo();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.parse(date, formatter);
        String firstDayOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).format(formatter);
        String lastDayOfWeek = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)).format(formatter);

        queryInfo.setBeginTaskDate(firstDayOfWeek);
        queryInfo.setEndTaskDate(lastDayOfWeek);
        Long appUnionConfigByDeptId = unionEnterpriseConfigCacheService.getAppUnionUserIdByDeptId(loginUser.getDeptId());
        if (appUnionConfigByDeptId == null)
        {
            return AjaxResult.success(returnList);
        }
        queryInfo.setUnionUserId(appUnionConfigByDeptId);
        List<TaskSchedulingInfo> taskSchedulingInfos = taskSchedulingInfoService.selectTaskSchedulingInfoList(queryInfo);
        if (taskSchedulingInfos == null)
        {
            return AjaxResult.success(returnList);
        }
        for (TaskSchedulingInfo info : taskSchedulingInfos)
        {
            AppTaskSchedulingInfo app = new AppTaskSchedulingInfo();
            app.setTime(info.getTaskDate());
            app.setName1(info.getUserName());
            app.setName2(info.getUnionUserName());
            app.setArea2(info.getDeptName());
            app.setType("未巡视");
            app.setPhone1(enterpriseUserInfoCacheService.getPhoneByUserId(info.getUserId()));
            app.setPhone2(unionEnterpriseConfigCacheService.getPhoneByUserId(info.getUnionUserId()));
            returnList.add(app);
        }
        return AjaxResult.success(returnList);
    }

    /**
     * 新增值班配置
     */
    @PreAuthorize("@ss.hasPermi('inspection:schedulinginfo:add')")
    @Log(title = "值班配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TaskSchedulingInfo taskSchedulingInfo)
    {
        return toAjax(taskSchedulingInfoService.insertTaskSchedulingInfo(taskSchedulingInfo));
    }

    /**
     * 修改值班配置
     */
    @PreAuthorize("@ss.hasPermi('inspection:schedulinginfo:edit')")
    @Log(title = "值班配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TaskSchedulingInfo taskSchedulingInfo)
    {
        return toAjax(taskSchedulingInfoService.updateTaskSchedulingInfo(taskSchedulingInfo));
    }

    /**
     * 删除值班配置
     */
    @PreAuthorize("@ss.hasPermi('inspection:schedulinginfo:remove')")
    @Log(title = "值班配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(taskSchedulingInfoService.deleteTaskSchedulingInfoByIds(ids));
    }
}
