<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="区县名称" prop="countyId">
        <el-select v-model="queryParams.countyId" placeholder="请选择区县名称" @change="getCenterCode(queryParams.countyId)" clearable >
          <el-option
            v-for="dict in dict.type.county_name"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="区县名称" prop="countyName">
        <el-input
          v-model="queryParams.countyName"
          placeholder="请输入区县名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="网格名称" prop="centerId">
        <el-select v-model="queryParams.centerId" placeholder="请选择网格名称" @change="getTownCode(queryParams.centerId)" clearable>
          <el-option
            v-for="dict in centerCodeList"
            :key="dict.code"
            :label="dict.name"
            :value="dict.code"
          />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="网格名称" prop="centerName">
        <el-input
          v-model="queryParams.centerName"
          placeholder="请输入网格名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="微格名称" prop="townId">
        <el-select v-model="queryParams.townId" placeholder="请选择微格名称" clearable>
          <el-option
            v-for="dict in townCodeList"
            :key="dict.code"
            :label="dict.name"
            :value="dict.code"
          />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="微格名称" prop="townName">
        <el-input
          v-model="queryParams.townName"
          placeholder="请输入微格名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="得分" prop="score">
        <el-input
          v-model="queryParams.score"
          placeholder="请输入得分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="排名" prop="rank">
        <el-input
          v-model="queryParams.rank"
          placeholder="请输入排名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="指标类型" prop="marketingType">
        <el-select v-model="queryParams.marketingType" placeholder="请选择指标类型" clearable>
          <el-option
            v-for="dict in dict.type.marketing_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="记录时间" prop="recordDate">
        <el-date-picker clearable
          v-model="queryParams.recordDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择记录时间">
        </el-date-picker>
      </el-form-item>
      <!-- <el-form-item label="指标名称" prop="marketingTypeName">
        <el-input
          v-model="queryParams.marketingTypeName"
          placeholder="请输入指标名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
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
          v-hasPermi="['springmarketing:centerrank:add']"
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
          v-hasPermi="['springmarketing:centerrank:edit']"
        >修改</el-button>
      </el-col> -->
      <el-col :span="1.5">
            <el-button
              type="info"
              plain
              icon="el-icon-upload2"
              size="mini"
              @click="handleImport"
            >导入</el-button>
          </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['springmarketing:centerrank:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['springmarketing:centerrank:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="centerrankList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键id" align="center" prop="id" /> -->
      <!-- <el-table-column label="区县名称" align="center" prop="countyId">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.county_name" :value="scope.row.countyId"/>
        </template>
      </el-table-column> -->
      <el-table-column label="区县名称" align="center" prop="countyName" />
      <!-- <el-table-column label="网格名称" align="center" prop="centerId">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.county_name" :value="scope.row.centerId"/>
        </template>
      </el-table-column> -->
      <el-table-column label="网格名称" align="center" prop="centerName" />
      <!-- <el-table-column label="微格名称" align="center" prop="townId">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.county_name" :value="scope.row.townId"/>
        </template>
      </el-table-column> -->
      <el-table-column label="微格名称" align="center" prop="townName" />
      <el-table-column label="记录时间" align="center" prop="recordDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recordDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="得分" align="center" prop="score" />
      <!-- <el-table-column label="排名" align="center" prop="rank" /> -->
      <el-table-column label="指标类型" align="center" prop="marketingType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.marketing_type" :value="scope.row.marketingType"/>
        </template>
      </el-table-column>
      <!-- <el-table-column label="指标名称" align="center" prop="marketingTypeName" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['springmarketing:centerrank:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['springmarketing:centerrank:remove']"
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

    <!-- 添加或修改网格龙虎榜对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="区县名称" prop="countyId">
          <el-select v-model="form.countyId" placeholder="请选择区县名称" @change="getCenterCode1(form.countyId)">
            <el-option
              v-for="dict in dict.type.county_name"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="区县名称" prop="countyName">
          <el-input v-model="form.countyName" placeholder="请输入区县名称" />
        </el-form-item> -->
        <el-form-item label="网格名称" prop="centerId">
          <el-select v-model="form.centerId" placeholder="请选择网格名称" @change="getTownCode1(form.centerId)">
            <el-option
            v-for="dict in centerCodeList1"
            :key="dict.code"
            :label="dict.name"
            :value="dict.code"
          />
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="网格名称" prop="centerName">
          <el-input v-model="form.centerName" placeholder="请输入网格名称" />
        </el-form-item> -->
        <el-form-item label="微格名称" prop="townId">
          <el-select v-model="form.townId" placeholder="请选择微格名称">
            <el-option
            v-for="dict in townCodeList1"
            :key="dict.code"
            :label="dict.name"
            :value="dict.code"
          />
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="微格名称" prop="townName">
          <el-input v-model="form.townName" placeholder="请输入微格名称" />
        </el-form-item> -->
        <el-form-item label="记录时间" prop="recordDate">
          <el-date-picker clearable
            v-model="form.recordDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择记录时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="指标类型" prop="marketingType">
          <el-select v-model="form.marketingType" placeholder="请选择指标类型">
            <el-option
              v-for="dict in dict.type.marketing_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="得分" prop="score">
          <el-input v-model="form.score" placeholder="请输入得分" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <!-- <div class="el-upload__tip" slot="tip">
            <el-checkbox v-model="upload.updateSupport" /> 是否更新已经存在的用户数据
          </div> -->
          <span>仅允许导入xls、xlsx格式文件。</span>
            <!-- <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplate">下载模板</el-link> -->
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCenterrank, getCenterrank, delCenterrank, addCenterrank, updateCenterrank } from "@/api/springmarketing/centerrank";
import {getAreaCodeList} from "@/api/springmarketing/gardinfo";
import { getToken } from "@/utils/auth";

export default {
  name: "Centerrank",
  dicts: ['county_name', 'marketing_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 网格龙虎榜表格数据
      centerrankList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        countyId: null,
        countyName: null,
        centerId: null,
        centerName: null,
        townId: null,
        townName: null,
        recordDate: null,
        score: null,
        rank: null,
        marketingType: null,
        marketingTypeName: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      
      centerCodeList:[],
      townCodeList:[],
      centerCodeList1:[],
      townCodeList1:[],

      // 导入参数
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
      //   // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/springmarketing/centerrank/importData"
      }
    };
  },
  created() {
    this.getList();
    // this.getAreaList("1","center");
  },
  methods: {

    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    },

    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "龙虎榜导入";
      this.upload.open = true;
    },
    getTownCode(parentCode) {
      this.queryParams.townId = null
      if (!parentCode || parentCode == null || parentCode == undefined) 
      {
        this.townCodeList =[]
        return 
      }
      this.getTownList(parentCode,"town")
    },

    getCenterCode(parentCode) {

      this.queryParams.centerId = null
      this.queryParams.townId = null
      if (!parentCode || parentCode == null || parentCode == undefined) 
      {
        this.centerCodeList = []
        this.townCodeList =[]
        return 
      }
      console.log("enter getCenterCode:"+ parentCode)
      this.getAreaList(parentCode,"center")
      // console.log("enter getCenterCode"+ JSON.stringify(this.centerCodeList))
    },
    getTownCode1(parentCode) {
      this.form.townId = null
      if (!parentCode || parentCode == null || parentCode == undefined) 
      {
        this.townCodeList1 =[]
        return 
      }
      this.getTownList1(parentCode,"town")
    },

    getCenterCode1(parentCode) {

      this.form.centerId = null
      this.form.townId = null
      if (!parentCode || parentCode == null || parentCode == undefined) 
      {
        this.centerCodeList1 = []
        this.townCodeList1 =[]
        return 
      }
      this.getAreaList1(parentCode,"center")
      // console.log("enter getCenterCode"+ JSON.stringify(this.centerCodeList))
    },

    /** 查询网格列表 */
    getAreaList (parentCode, codeType) {
      getAreaCodeList({"parentCode":parentCode,"type":codeType}).then( response => {
        this.centerCodeList = response
      });
    },

    /** 查询微格列表 */
    getTownList (parentCode, codeType) {
      getAreaCodeList({"parentCode":parentCode,"type":codeType}).then( response => {
        this.townCodeList = response
      });
    },

    /** 查询网格列表 */
    getAreaList1 (parentCode, codeType) {
      getAreaCodeList({"parentCode":parentCode,"type":codeType}).then( response => {
        this.centerCodeList1 = response
      });
    },

    /** 查询微格列表 */
    getTownList1 (parentCode, codeType) {
      getAreaCodeList({"parentCode":parentCode,"type":codeType}).then( response => {
        this.townCodeList1 = response
      });
    },

    /** 查询网格龙虎榜列表 */
    getList() {
      this.loading = true;
      listCenterrank(this.queryParams).then(response => {
        this.centerrankList = response.rows;
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
        countyId: null,
        countyName: null,
        centerId: null,
        centerName: null,
        townId: null,
        townName: null,
        recordDate: null,
        score: null,
        rank: null,
        marketingType: null,
        marketingTypeName: null
      };
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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加网格龙虎榜";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCenterrank(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改网格龙虎榜";
      });

      this.getAreaList1(row.countyId, "center");
      this.getTownList1(row.centerId, "town");
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCenterrank(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCenterrank(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除网格龙虎榜编号为"' + ids + '"的数据项？').then(function() {
        return delCenterrank(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('springmarketing/centerrank/export', {
        ...this.queryParams
      }, `centerrank_${new Date().getTime()}.xlsx`)
    }
  },
    
};
</script>
