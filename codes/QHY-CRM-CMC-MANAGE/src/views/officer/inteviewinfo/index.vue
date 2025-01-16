<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="走访人" prop="officerName">
        <el-input
          v-model="queryParams.officerName"
          placeholder="请输入走访人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="被走访人" prop="interviewName">
        <el-input
          v-model="queryParams.interviewName"
          placeholder="请输入被走访人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="interviewPhone">
        <el-input
          v-model="queryParams.interviewPhone"
          placeholder="请输入手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="部门" prop="localPoliceStation">
        <!-- <el-input
          v-model="queryParams.localPoliceStation"
          placeholder="请输入派出所"
          clearable
          @keyup.enter.native="handleQuery"
        /> -->
        <el-select v-model="queryParams.localPoliceStationId" placeholder="请选择部门" @change="getStationCode(queryParams.localPoliceStationId)" clearable>
          <el-option
            v-for="dict in stationCodeList"
            :key="dict.code"
            :label="dict.name"
            :value="dict.code"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="走访类别" prop="belongingPlaceId">
        <!-- <el-input
          v-model="queryParams.belongingPlace"
          placeholder="请输入归属地"
          clearable
          @keyup.enter.native="handleQuery"
        /> -->
        <el-select v-model="queryParams.belongingPlaceId" placeholder="请选择走访类别" clearable>
          <el-option
            v-for="dict in belongPlaceList"
            :key="dict.code"
            :label="dict.name"
            :value="dict.code"
          />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="走访地址" prop="interviewAddr">
        <el-input
          v-model="queryParams.interviewAddr"
          placeholder="请输入走访地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="矛盾纠纷" prop="contradictionFlag">
        <el-select v-model="queryParams.contradictionFlag" placeholder="请选择是否矛盾" clearable>
          <el-option
            v-for="dict in dict.type.contradiction_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="开始时间" prop="interviewBeginTime">
        <el-date-picker clearable
          v-model="queryParams.interviewBeginTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择走访开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="interviewEndTime">
        <el-date-picker clearable
          v-model="queryParams.interviewEndTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择走访结束时间">
        </el-date-picker>
      </el-form-item> -->
      <el-form-item label="群众满意" prop="satisfyFlag">
        <el-select v-model="queryParams.satisfyFlag" placeholder="请选择群众满意" clearable>
          <el-option
            v-for="dict in dict.type.satisfy_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="租户" prop="tenantFlag">
        <el-select v-model="queryParams.tenantFlag" placeholder="请选择租户" clearable>
          <el-option
            v-for="dict in dict.type.tenant_flag"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="走访日期">
            <el-date-picker
              v-model="dateRange"
              style="width: 240px"
              value-format="yyyy-MM-dd HH:mm:ss"
              type="datetimerange"
              range-separator="-"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            ></el-date-picker>
          </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['officer:inteviewinfo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['officer:inteviewinfo:edit1']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['officer:inteviewinfo:remove']"
        >删除</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['officer:inteviewinfo:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExportpro"
          v-hasPermi="['officer:inteviewinfo:export']"
        >导出（标准）</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExportextend"
          v-hasPermi="['officer:inteviewinfo:export']"
        >导出（拓展）</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="inteviewinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键id" align="center" prop="id" /> -->
      <el-table-column label="走访人" align="center" prop="officerName" />
      <el-table-column label="被走访人" align="center" prop="interviewName" />
      <el-table-column label="手机号" align="center" prop="interviewPhone" />
      <el-table-column label="部门" align="center" prop="localPoliceStation" />
      <el-table-column label="走访类别" align="center" prop="belongingPlace" />
      <el-table-column label="走访地址" align="center" prop="interviewAddr"> 
        <template #default="scope" >
          <el-tooltip :content="scope.row.interviewAddr" effect="light" placement="top-start" :disabled="!isShowTooltip" >
            <div class="hide" @mouseover="onMouseOver($event,scope.row.interviewAddr)">
              <span :ref="scope.row.interviewAddr">{{ scope.row.interviewAddr || '-' }}</span>
            </div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="定位地址" align="center" prop="mapAddress">
        <template #default="scope" >
          <el-tooltip :content="scope.row.mapAddress" effect="light" placement="top-start" :disabled="!isShowTooltip" >
            <div class="hide" @mouseover="onMouseOver($event,scope.row.mapAddress)">
              <span :ref="scope.row.mapAddress">{{ scope.row.mapAddress || '-' }}</span>
            </div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="矛盾纠纷" align="center" prop="contradictionFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.contradiction_type" :value="scope.row.contradictionFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="矛盾内容" align="center" prop="contradictionText"> 
        <template #default="scope" >
          <el-tooltip :content="scope.row.contradictionText" effect="light" placement="top-start" :disabled="!isShowTooltip" >
            <div class="hide" @mouseover="onMouseOver($event,scope.row.contradictionText)">
              <span :ref="scope.row.contradictionText">{{ scope.row.contradictionText || '-' }}</span>
            </div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="开始时间" align="center" prop="interviewBeginTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.interviewBeginTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="interviewEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.interviewEndTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="附件1" align="center" prop="attachmentOne" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.attachmentOne" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="附件2" align="center" prop="attachmentTwo" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.attachmentTwo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="群众不满意" align="center" prop="satisfyFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.satisfy_type" :value="scope.row.satisfyFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="不满意反馈" align="center" prop="satisfyText">
        <template #default="scope" >
          <el-tooltip :content="scope.row.satisfyText" effect="light" placement="top-start" :disabled="!isShowTooltip" >
            <div class="hide" @mouseover="onMouseOver($event,scope.row.satisfyText)">
              <span :ref="scope.row.satisfyText">{{ scope.row.satisfyText || '-' }}</span>
            </div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="走访情况" align="center" prop="interviewText" class="hide">
        <template #default="scope" >
          <el-tooltip :content="scope.row.interviewText" effect="light" placement="top-start" :disabled="!isShowTooltip" >
            <div class="hide" @mouseover="onMouseOver($event,scope.row.interviewText)">
              <span :ref="scope.row.interviewText">{{ scope.row.interviewText || '-' }}</span>
            </div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="租户" align="center" prop="tenantFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.tenant_flag" :value="scope.row.tenantFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="走访次数" align="center" prop="repeatFlag" />
      <el-table-column label="房东姓名" align="center" prop="tenantName" />
      <el-table-column label="房东身份证" align="center" prop="tenantTel" />
      <el-table-column label="房东联系方式" align="center" prop="tenantWechat" />
      <el-table-column v-if="showOperate" label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['officer:inteviewinfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['officer:inteviewinfo:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改访问记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="走访人" prop="officerName">
          <el-input v-model="form.officerName" placeholder="请输入走访人" />
        </el-form-item>
        <el-form-item label="被走访人" prop="interviewName">
          <el-input v-model="form.interviewName" placeholder="请输入被走访人" />
        </el-form-item>
        <el-form-item label="被走访人电话" prop="interviewPhone">
          <el-input v-model="form.interviewPhone" placeholder="请输入被走访人电话" />
        </el-form-item>
        <el-form-item label="部门" prop="localPoliceStation">
          <el-input v-model="form.localPoliceStation" placeholder="请输入部门" />
        </el-form-item>
        <el-form-item label="走访类别" prop="belongingPlace">
          <el-input v-model="form.belongingPlace" placeholder="请输入走访类别" />
        </el-form-item>
        <el-form-item label="走访地址" prop="interviewAddr">
          <el-input v-model="form.interviewAddr" placeholder="请输入走访地址" />
        </el-form-item>
        <el-form-item label="是否矛盾" prop="contradictionFlag">
          <el-radio-group v-model="form.contradictionFlag">
            <el-radio
              v-for="dict in dict.type.contradiction_type"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="矛盾内容" prop="contradictionText">
          <el-input v-model="form.contradictionText" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="走访开始时间" prop="interviewBeginTime">
          <el-date-picker clearable
            v-model="form.interviewBeginTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择走访开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="走访结束时间" prop="interviewEndTime">
          <el-date-picker clearable
            v-model="form.interviewEndTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择走访结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="附件1" prop="attachmentOne">
          <image-upload v-model="form.attachmentOne"/>
        </el-form-item>
        <el-form-item label="附件2" prop="attachmentTwo">
          <image-upload v-model="form.attachmentTwo"/>
        </el-form-item>
        <el-form-item label="是否满意" prop="satisfyFlag">
          <el-radio-group v-model="form.satisfyFlag">
            <el-radio
              v-for="dict in dict.type.satisfy_type"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="满意内容" prop="satisfyText">
          <el-input v-model="form.satisfyText" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="走访情况" prop="interviewText">
          <el-input v-model="form.interviewText" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="租户" prop="tenantFlag">
          <el-select v-model="form.tenantFlag" placeholder="请选择租户">
            <el-option
              v-for="dict in dict.type.tenant_flag"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-divider content-position="center">房东访问信息</el-divider>
        <!-- <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddTenantInteviewRecord">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteTenantInteviewRecord">删除</el-button>
          </el-col>
        </el-row> -->
        <el-table :data="tenantInteviewRecordList" :row-class-name="rowTenantInteviewRecordIndex" @selection-change="handleTenantInteviewRecordSelectionChange" ref="tenantInteviewRecord">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="房东名称" prop="tenantName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.tenantName" placeholder="请输入房东名称" />
            </template>
          </el-table-column>
          <el-table-column label="房东身份证号" prop="tenantTel" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.tenantTel" placeholder="请输入房东身份证号" />
            </template>
          </el-table-column>
          <el-table-column label="房东联系方式" prop="tenantWechat" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.tenantWechat" placeholder="请输入房东联系方式" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { appcheckadmin, listInteviewinfo, getInteviewinfo, delInteviewinfo, addInteviewinfo, updateInteviewinfo } from "@/api/officer/inteviewinfo";
import {getBelongPlaceCodeList} from "@/api/officer/belongingplaceinfo";

export default {
  name: "Inteviewinfo",
  dicts: ['tenant_flag', 'contradiction_type', 'satisfy_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      dateRange: [],
      // 子表选中数据
      checkedTenantInteviewRecord: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 访问记录表格数据
      inteviewinfoList: [],
      // 房东访问表格数据
      tenantInteviewRecordList: [],
      // 派出所
      stationCodeList:[],
      // 归属地
      belongPlaceList:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //
      showOperate: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        officerName: null,
        interviewName: null,
        interviewPhone: null,
        localPoliceStationId: null,
        localPoliceStation: null,
        belongingPlace: null,
        belongingPlaceId: null,
        interviewAddr: null,
        contradictionFlag: null,
        contradictionText: null,
        interviewBeginTime: null,
        interviewEndTime: null,
        attachmentOne: null,
        attachmentTwo: null,
        satisfyFlag: null,
        satisfyText: null,
        interviewText: null,
        tenantFlag: null
      },
      isShowTooltip: false,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        interviewName: [
          { required: true, message: "被走访人不能为空", trigger: "blur" }
        ],
        interviewPhone: [
          { required: true, message: "被走访人电话不能为空", trigger: "blur" }
        ],
        localPoliceStation: [
          { required: true, message: "派出所不能为空", trigger: "blur" }
        ],
        belongingPlace: [
          { required: true, message: "归属地不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.checkadmin();
    this.getStationList("0","station");
  },
  methods: {

    addDateRange(dateRange) {
      console.log(dateRange)
      // let search = params;
      // search.params = typeof (search.params) === 'object' && search.params !== null && !Array.isArray(search.params) ? search.params : {};
      dateRange = Array.isArray(dateRange) ? dateRange : [];
      // if (typeof (propName) === 'undefined') {
      //   search.params['beginTime'] = dateRange[0];
      //   search.params['endTime'] = dateRange[1];
      // } else {
      //   search.params['begin' + propName] = dateRange[0];
      //   search.params['end' + propName] = dateRange[1];
      // }
      // return search;
      if (dateRange[0] !== undefined) 
      {
        this.queryParams['compareBeginTime'] = dateRange[0];
      }
      if (dateRange[1] !== undefined) 
      {
        this.queryParams['compareEndTime'] = dateRange[1];
      }
      if (dateRange.length == 0) 
      {
        this.queryParams['compareBeginTime'] = null;
        this.queryParams['compareEndTime'] = null;
      }
    },
    checkadmin()
    {
      appcheckadmin().then(response => {
        this.showOperate = response.data
      });
    },
    /** 查询访问记录列表 */
    getList() {
      this.loading = true;
      // console.log(JSON.stringify(this.queryParams))
      this.addDateRange(this.dateRange)
      // console.log(JSON.stringify(this.queryParams))
      listInteviewinfo(this.queryParams).then(response => {
        this.inteviewinfoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        officerId: null,
        officerName: null,
        interviewName: null,
        interviewPhone: null,
        localPoliceStation: null,
        belongingPlace: null,
        interviewAddr: null,
        contradictionFlag: null,
        contradictionText: null,
        interviewBeginTime: null,
        interviewEndTime: null,
        attachmentOne: null,
        attachmentTwo: null,
        satisfyFlag: null,
        satisfyText: null,
        interviewText: null,
        tenantFlag: null
      };
      this.tenantInteviewRecordList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    getStationCode(parentCode) {
      // this.queryParams.belongingPlace = null
      this.queryParams.belongingPlaceId = null
      if (!parentCode || parentCode == null || parentCode == undefined) 
      {
        this.belongPlaceList =[]
        return 
      }
      this.getBelongPlaceList(parentCode,"belong")
    },

    /** 查询派出所列表 */
    getStationList (parentCode, codeType) {
      getBelongPlaceCodeList({"parentCode":parentCode,"type":codeType}).then( response => {
        this.stationCodeList = response
      });
    },
    /** 查询归宿地列表 */
    getBelongPlaceList (parentCode, codeType) {
      getBelongPlaceCodeList({"parentCode":parentCode,"type":codeType}).then( response => {
        this.belongPlaceList = response
      });
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加访问记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInteviewinfo(id).then(response => {
        this.form = response.data;
        this.tenantInteviewRecordList = response.data.tenantInteviewRecordList;
        this.open = true;
        this.title = "修改访问记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.tenantInteviewRecordList = this.tenantInteviewRecordList;
          if (this.form.id != null) {
            updateInteviewinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInteviewinfo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除访问记录编号为"' + ids + '"的数据项？').then(function() {
        return delInteviewinfo(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 房东访问序号 */
    rowTenantInteviewRecordIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 房东访问添加按钮操作 */
    handleAddTenantInteviewRecord() {
      let obj = {};
      obj.tenantName = "";
      obj.tenantTel = "";
      obj.tenantWechat = "";
      this.tenantInteviewRecordList.push(obj);
    },
    /** 房东访问删除按钮操作 */
    handleDeleteTenantInteviewRecord() {
      if (this.checkedTenantInteviewRecord.length == 0) {
        this.$modal.msgError("请先选择要删除的房东访问数据");
      } else {
        const tenantInteviewRecordList = this.tenantInteviewRecordList;
        const checkedTenantInteviewRecord = this.checkedTenantInteviewRecord;
        this.tenantInteviewRecordList = tenantInteviewRecordList.filter(function(item) {
          return checkedTenantInteviewRecord.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleTenantInteviewRecordSelectionChange(selection) {
      this.checkedTenantInteviewRecord = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('officer/inteviewinfo/export', {
        ...this.queryParams
      }, `inteviewinfo_${new Date().getTime()}.xlsx`)
    },
    /** 导出按钮操作 */
    handleExportpro() {
      this.download('officer/inteviewinfo/exportpro', {
        ...this.queryParams
      }, `inteviewinfo_${new Date().getTime()}.xlsx`)
    },
    /** 导出按钮操作 */
    handleExportextend() {
      this.download('officer/inteviewinfo/exportExtend', {
        ...this.queryParams
      }, `inteviewinfo_${new Date().getTime()}.xlsx`)
    },
    onMouseOver (obj, str) { 
      // 内容超出，显示文字提示内容
      /*currentWidth 为文本在页面中所占的宽度，创建标签，加入到页面，获取currentWidth ,最后在移除*/
      let TemporaryTag = document.createElement('span');
      TemporaryTag.innerText = str;
      TemporaryTag.className = 'getTextWidth';
      document.querySelector('body').appendChild(TemporaryTag);
      let currentWidth = document.querySelector('.getTextWidth').offsetWidth;
      document.querySelector('.getTextWidth').remove();
 
      /*cellWidth为表格容器的宽度*/
      const cellWidth = obj.target.offsetWidth
 
      /*当文本宽度小于||等于容器宽度3倍时，代表文本显示未超过3行*/
      currentWidth <= (3*cellWidth) ? this.isShowTooltip = false : this.isShowTooltip = true

    }
  }
};
</script>
<style scoped>
.hide {
    display: -webkit-box;
    text-overflow: ellipsis;
    overflow: hidden;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    white-space: pre-line;
  }
</style>
