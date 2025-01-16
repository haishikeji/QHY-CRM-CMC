package cn.px.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import cn.px.system.domain.EnterpriseUserInfo;
import cn.px.system.service.IEnterpriseUserInfoService;
import cn.px.common.utils.poi.ExcelUtil;
import cn.px.common.core.page.TableDataInfo;

/**
 * 联防员信息Controller
 *
 * @author 品讯科技
 * @date 2024-08
 */
@RestController
@RequestMapping("/inspection/enterpriseuser")
public class EnterpriseUserInfoController extends BaseController
{
    @Autowired
    private IEnterpriseUserInfoService enterpriseUserInfoService;

    /**
     * 查询联防员信息列表
     */
    @PreAuthorize("@ss.hasPermi('inspection:enterpriseuser:list')")
    @GetMapping("/list")
    public TableDataInfo list(EnterpriseUserInfo enterpriseUserInfo)
    {
        startPage();
        List<EnterpriseUserInfo> list = enterpriseUserInfoService.selectEnterpriseUserInfoList(enterpriseUserInfo);
        return getDataTable(list);
    }

    /**
     * 导出联防员信息列表
     */
    @PreAuthorize("@ss.hasPermi('inspection:enterpriseuser:export')")
    @Log(title = "联防员信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EnterpriseUserInfo enterpriseUserInfo)
    {
        List<EnterpriseUserInfo> list = enterpriseUserInfoService.selectEnterpriseUserInfoList(enterpriseUserInfo);
        ExcelUtil<EnterpriseUserInfo> util = new ExcelUtil<EnterpriseUserInfo>(EnterpriseUserInfo.class);
        util.exportExcel(response, list, "联防员信息数据");
    }

    /**
     * 获取联防员信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('inspection:enterpriseuser:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(enterpriseUserInfoService.selectEnterpriseUserInfoById(id));
    }

    /**
     * 新增联防员信息
     */
    @PreAuthorize("@ss.hasPermi('inspection:enterpriseuser:add')")
    @Log(title = "联防员信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EnterpriseUserInfo enterpriseUserInfo)
    {
        return toAjax(enterpriseUserInfoService.insertEnterpriseUserInfo(enterpriseUserInfo));
    }

    /**
     * 修改联防员信息
     */
    @PreAuthorize("@ss.hasPermi('inspection:enterpriseuser:edit')")
    @Log(title = "联防员信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EnterpriseUserInfo enterpriseUserInfo)
    {
        return toAjax(enterpriseUserInfoService.updateEnterpriseUserInfo(enterpriseUserInfo));
    }

    /**
     * 删除联防员信息
     */
    @PreAuthorize("@ss.hasPermi('inspection:enterpriseuser:remove')")
    @Log(title = "联防员信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(enterpriseUserInfoService.deleteEnterpriseUserInfoByIds(ids));
    }
}
