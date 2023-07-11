<template>
  <div style="width: 100%">
    <el-container>
      <el-header style="height: 20px;">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>用户管理</el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: '/user' }">用户列表</el-breadcrumb-item>
          <el-breadcrumb-item>添加用户</el-breadcrumb-item>
        </el-breadcrumb>
      </el-header>
      <el-main>
        <el-form :rules="rules" ref="addUserForm" :model="addUserForm" label-width="80px"
          style="width: 50%; margin-left: 20%; margin-top: 30px;">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="addUserForm.username"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="addUserForm.password" type="password"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="password1">
            <el-input v-model="addUserForm.password1" type="password"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="phone">
            <el-input v-model="addUserForm.phone"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="addUserForm.email"></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-switch v-model="addUserForm.status"></el-switch>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">创建</el-button>
            <el-button @click="cancel">取消</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  export default {
    data() {
      var checkPassword = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.addUserForm.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
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
        addUserForm: {
          username: '',
          password: '',
          password1: '',
          phone: '',
          email: '',
          status: false
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
          password: [{
              required: true,
              message: '请输入密码',
              trigger: 'blur'
            },
            {
              min: 3,
              max: 30,
              message: '长度在 3 到 30 个字符',
              trigger: 'blur'
            }
          ],
          password1: [{
              required: true,
              message: '请确认密码',
              trigger: 'blur'
            },
            {
              validator: checkPassword,
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
      onSubmit() {
        this.$refs.addUserForm.validate(async valid => {
          if (!valid) return
          const {
            data: result
          } = await this.$http.post('/user/addUser', this.addUserForm)
          if (result.status != 200) return this.$message.error("添加用户失败")
          this.$router.push("/user")
        })
      },
      cancel() {
        this.$router.push("/user")
      }
    }
  }
</script>

<style>
</style>
