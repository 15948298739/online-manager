<template>
  <div>
    <!-- 导航 -->
    <el-header style="height: 20px;">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>用户管理</el-breadcrumb-item>
        <el-breadcrumb-item>用户列表</el-breadcrumb-item>
      </el-breadcrumb>
    </el-header>
    <el-main>
      <!-- 定义第一行数据 -->

      <!-- 输入框 -->
      <div>
        <el-row :gutter="20">
          <el-col :span="8">
            <el-input placeholder="请输入内容" v-model="queryInfo.query" clearable @clear="getUserList">
              <el-button slot="append" icon="el-icon-search" @click="getUserList"></el-button>
            </el-input>
          </el-col>
          <el-col :span="4">
            <el-button type="primary" @click="addUser">添加用户</el-button>
          </el-col>
        </el-row>
      </div>
      <!-- 表格 -->
      <el-table height="70vh" v-loading="loading" element-loading-text="拼命加载中" element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(0, 0, 0, 0.8)" :data="userList" stripe style="width: 100%">
        <div slot="empty"></div>
        <el-table-column type="index" label="序号" width="120px">
        </el-table-column>
        <el-table-column prop="icon" label="头像">
          <div class="demo-type">
            <el-avatar :size="60" src="https://empty" @error="errorHandler">
              <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png" />
            </el-avatar>
          </div>
        </el-table-column>
        <el-table-column prop="username" label="用户名">
        </el-table-column>
        <el-table-column prop="phone" label="电话">
        </el-table-column>
        <el-table-column prop="email" label="邮箱">
        </el-table-column>
        <el-table-column prop="identity" label="身份">
        </el-table-column>
        <el-table-column prop="status" label="状态">
          <template slot-scope="scope">
            <el-switch @change="updateStatus(scope.row)" v-model="scope.row.status" active-color="#13ce66"
              inactive-color="#ff4949">
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column prop="email" label="邮箱">
        </el-table-column>
        <el-table-column fixed="right" label="操作">
          <template slot-scope="scope">
            <el-button size="mini" type="warning" icon="el-icon-edit" @click="toUpdateUser(scope.row)"></el-button>
            <el-button size="mini" type="danger" icon="el-icon-delete" @click="deleteUser(scope.row.id)"></el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-dialog :destroy-on-close=true title="修改信息" :visible.sync="updateUserDialogVisible" width="50%">
        <el-form :model="userModel" :rules="rules" ref="updateUserRef" label-width="100px" class="demo rules">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="userModel.username"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="phone">
            <el-input v-model="userModel.phone"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="userModel.email"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="updateUser(0)">取 消</el-button>
          <el-button type="primary" @click="updateUser(1)">确 定</el-button>
        </div>
      </el-dialog>
      <div class="block">
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
          :current-page="queryInfo.pageNum" :page-sizes="[10, 20, 50]" :page-size="queryInfo.pageSize"
          layout="total, sizes, prev, pager, next, jumper" :total="total">
        </el-pagination>
      </div>
    </el-main>
  </div>
</template>

<script>
export default {
  data() {
    var checkPhone = (rule, value, callback) => {
      let reg =
        /^(?:(?:\+|00)86)?1(?:(?:3[\d])|(?:4[5-79])|(?:5[0-35-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\d])|(?:9[189]))\d{8}$/
      if (!reg.test(value)) {
        callback(new Error('电话格式不正确，请重新输入'));
      } else {
        callback();
      }
    };
    var checkEmail = (rule, value, callback) => {
      let reg = /^[a-zA-Z0-9]+([-_.][A-Za-zd]+)*@([a-zA-Z0-9]+[-.])+[A-Za-zd]{2,5}$/
      if (!reg.test(value)) {
        callback(new Error('邮箱格式不正确，请重新输入'));
      } else {
        callback();
      }
    };
    return {
      queryInfo: {
        query: '',
        pageNum: 1,
        pageSize: 5
      },
      loading: true,
      userList: [],
      total: 0,
      updateUserDialogVisible: false,
      userModel: {
        id: '',
        username: '',
        password: '',
        phone: '',
        email: '',
        status: false,
        identity: ''
      },
      rules: {
        username: [{
          required: true,
          message: '请输入用户名',
          trigger: 'blur'
        },
        {
          min: 3,
          max: 30,
          message: '长度在 3 到 30 个字符',
          trigger: 'blur'
        }
        ],
        phone: [{
          required: true,
          message: '请输入电话',
          trigger: 'blur'
        },
        {
          validator: checkPhone,
          trigger: 'blur'
        }
        ],
        email: [{
          required: true,
          message: '请输入邮箱',
          trigger: 'blur'
        },
        {
          validator: checkEmail,
          trigger: 'blur'
        }
        ]
      }
    }
  },
  methods: {
    errorHandler() {
      return true
    },
    async getUserList() {
      const {
        data: result
      } = await this.$http.get('/user/list', {
        params: this.queryInfo
      })
      if (result.status != 200) return this.$message.error("获取用户列表失败")
      this.total = result.data.total
      this.userList = result.data.rows
      this.loading = false
    },
    handleSizeChange(pageSize) {
      this.queryInfo.pageSize = pageSize
      this.getUserList()
    },
    handleCurrentChange(pageNum) {
      this.queryInfo.pageNum = pageNum
      this.getUserList()
    },
    async updateStatus(user) {
      const {
        data: result
      } = await this.$http.post('/user/updateStatus', user)
      if (result.status != 200) this.$message.error("状态更新失败")
    },
    addUser() {
      this.$router.push('/addUser')
    },
    toUpdateUser(user) {
      this.updateUserDialogVisible = true;
      this.userModel.id = user.id
      this.userModel.username = user.username
      this.userModel.password = user.password
      this.userModel.phone = user.phone
      this.userModel.email = user.email
      this.userModel.status = user.status
    },
    updateUser(flag) {
      this.updateUserDialogVisible = false;
      if (flag == 0) return this.$message("取消修改")
      this.$refs.updateUserRef.validate(async valid => {
        if (!valid) return this.$message.error("校验未通过")
        const {
          data: result
        } = await this.$http.put('/user/updateUser', this.userModel)
        if (result.status != 200) return this.$message.error("更新失败")
        this.$message.success("更新成功")
        this.getUserList()
      })
    },
    deleteUser(id) {
      this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        beforeClose: (action, instance, done) => {
          if (action === 'confirm') {
            instance.confirmButtonLoading = true
            instance.confirmButtonText = '执行中...'
            setTimeout(async () => {
              done()
              setTimeout(() => {
                instance.confirmButtonLoading = false
              }, 300)
              const {
                data: result
              } = await this.$http.delete(`/user/${id}`)
              if (result.status != 200) return this.$message.error("删除失败")
            }, 1000)
          } else {
            done()
          }
        }
      }).then(() => {
        this.$message({
          type: 'success',
          message: '删除成功!',
        })
        this.getUserList()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  },
  // 当页面加载完成后，自动调用
  mounted() {
    // 获取用户列表函数
    this.getUserList()
  }
}
</script>

<style long="less" scoped>
body {
  margin: 0;
}
</style>
