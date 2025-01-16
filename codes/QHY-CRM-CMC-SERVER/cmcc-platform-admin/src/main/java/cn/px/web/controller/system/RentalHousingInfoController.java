package cn.px.web.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import cn.px.common.utils.StringUtils;
import cn.px.system.domain.*;
import cn.px.system.domain.*;
import cn.px.system.service.CommunityConfigCacheService;
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
import cn.px.system.service.IRentalHousingInfoService;
import cn.px.common.utils.poi.ExcelUtil;
import cn.px.common.core.page.TableDataInfo;

/**
 * 出租房信息Controller
 *
 * @author 品讯科技
 * @date 2024-08
 */
@RestController
@RequestMapping("/consumer/rentalinfo")
public class RentalHousingInfoController extends BaseController
{
    @Autowired
    private IRentalHousingInfoService rentalHousingInfoService;

    @Autowired
    private CommunityConfigCacheService communityConfigCacheService;

    /**
     * 查询出租房信息列表
     */
    @PreAuthorize("@ss.hasPermi('consumer:rentalinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(RentalHousingInfo rentalHousingInfo)
    {
        startPage();
        List<RentalHousingInfo> list = rentalHousingInfoService.selectRentalHousingInfoList(rentalHousingInfo);
        return getDataTable(list);
    }

    @PostMapping("/appAdd")
    public AjaxResult appAdd(@RequestBody RentalHousingInfoVo rentalHousingInfoVo)
    {
        if (rentalHousingInfoVo.getBaseFormData() == null)
        {
            return error("表单不能为空");
        }
        RentalHousingInfoBaseVo base = rentalHousingInfoVo.getBaseFormData();
        RentalHousingInfo obj = new RentalHousingInfo();
        obj.setRecordDate(new Date());
        obj.setAddressInfo(base.getAddress_info());
        if (base.getUrls() != null && base.getUrls().size() >0)
        {
            obj.setAttachmentOne(base.getUrls().get(0));
            if (base.getUrls().size() >1)
            {
                obj.setAttachmentTwo(base.getUrls().get(1));
            }
        }

        obj.setBelongingPlace(base.getBelonging_place());
        obj.setCommunity(base.getCommunity());
        obj.setHostCardId(base.getHost_card_id());
        obj.setHostName(base.getHost_name());
        obj.setHostTel(base.getHost_tel());
        if (StringUtils.isNotBlank(rentalHousingInfoVo.getBaseFormData().getCommunity())){
            obj.setDeptId(communityConfigCacheService.getDeptIdByCommunity(base.getCommunity()));
        }
        obj.setRecordType(String.valueOf(base.getRecord_type()));
        obj.setRentalBeginDate(base.getRental_begin_date());
        obj.setRentalEndDate(base.getRental_end_date());
        obj.setTenantCardId(base.getTenant_card_id());
        obj.setTenantName(base.getTenant_name());
        obj.setTenantTel(base.getTenant_tel());

        List<TenantFormDataVo> tenantFormData = rentalHousingInfoVo.getTenantFormData();
        if (tenantFormData != null && tenantFormData.size() > 0)
        {
            List<TenantUserInfo> list = new ArrayList<>();
            long i = 0;
            for (TenantFormDataVo vo : tenantFormData)
            {
                TenantUserInfo tenantUserInfo = new TenantUserInfo();
                tenantUserInfo.setTenantCardId(vo.getTenant_card_id());
                tenantUserInfo.setTenantName(vo.getTenant_name());
                tenantUserInfo.setTenantTel(vo.getTenant_tel());
                tenantUserInfo.setOrderId(i);
                list.add(tenantUserInfo);
                i++;
            }
            obj.setTenantUserInfoList(list);
        }
        return toAjax(rentalHousingInfoService.insertRentalHousingInfo(obj));
    }

    /**
     * 导出出租房信息列表
     */
    @PreAuthorize("@ss.hasPermi('consumer:rentalinfo:export')")
    @Log(title = "出租房信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RentalHousingInfo rentalHousingInfo)
    {
        List<RentalHousingInfo> list = rentalHousingInfoService.selectRentalHousingInfoList(rentalHousingInfo);
        ExcelUtil<RentalHousingInfo> util = new ExcelUtil<RentalHousingInfo>(RentalHousingInfo.class);
        util.exportExcel(response, list, "出租房信息数据");
    }

    /**
     * 获取出租房信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('consumer:rentalinfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(rentalHousingInfoService.selectRentalHousingInfoById(id));
    }

    /**
     * 新增出租房信息
     */
    @PreAuthorize("@ss.hasPermi('consumer:rentalinfo:add')")
    @Log(title = "出租房信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RentalHousingInfo rentalHousingInfo)
    {
        rentalHousingInfo.setRecordDate(new Date());
        return toAjax(rentalHousingInfoService.insertRentalHousingInfo(rentalHousingInfo));
    }

    /**
     * 修改出租房信息
     */
    @PreAuthorize("@ss.hasPermi('consumer:rentalinfo:edit')")
    @Log(title = "出租房信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RentalHousingInfo rentalHousingInfo)
    {
        return toAjax(rentalHousingInfoService.updateRentalHousingInfo(rentalHousingInfo));
    }

    /**
     * 删除出租房信息
     */
    @PreAuthorize("@ss.hasPermi('consumer:rentalinfo:remove')")
    @Log(title = "出租房信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(rentalHousingInfoService.deleteRentalHousingInfoByIds(ids));
    }
}
