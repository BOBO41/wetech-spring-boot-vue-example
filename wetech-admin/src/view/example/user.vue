<template>
  <Card>
    <Card style="margin: 10px 0;">
      <Row>
        <Col span="8">
          <div>用户名：<Input v-model.trim="queryParams.username" placeholder="请输入用户名" clearable style="width: auto"/></div>
        </Col>
        <Col span="6" offset="10" style="text-align: right;">
          <Button style="margin: 5px;" type="primary" icon="md-download" @click="exportExcel">导出为Csv文件</Button>
          <Button style="margin: 5px;" type="success" @click="userModel = true" icon="md-add">新增</Button>
          <Button style="margin: 5px;" type="info" @click="handleSearch" icon="md-search">查询</Button>
          <Modal title="新增/编辑用户" v-model="userModel" @on-cancel="handleCancel('user')" :mask-closable="false">
            <Form ref="user" :model="user" :label-width="80" :rules="userRule">
              <FormItem label="用户名" prop="username">
                <Input v-model.trim="user.username" placeholder="请输入用户名" clearable/>
              </FormItem>
              <FormItem label="密码" prop="password">
                <Input v-model.trim="user.password" placeholder="请输入密码" clearable/>
              </FormItem>
              <FormItem label="是否锁定" prop="locked">
                <i-switch v-model="user.locked"/>
              </FormItem>
            </Form>
            <div slot="footer">
              <Button type="text" size="large" @click="handleCancel('user')">取消</Button>
              <Button type="primary" size="large" @click="handleSubmit('user')" :loading="loading">确定</Button>
            </div>
          </Modal>
        </Col>
      </Row>
    </Card>
    <Table :loading="loading" v-model="tableData" :columns="columns"/>
    <Page style="margin: 10px 0;" show-total :total="total" @on-change="handlePageNum" @on-page-size-change="handlePageSize" show-elevator show-sizer/>
  </Card>
</template>

<script>
  import Table from "_c/tables";
  import {getUserPage, updateUser, createUser, deleteUser} from "@/api/data";

  export default {
    name: "tables_page",
    components: {
      Table
    },
    data() {
      return {
        columns: [
          {title: "编号", key: "id", sortable: true},
          {title: "用户名", key: "username", sortable: true},
          {title: "密码", key: "password", sortable: true},
          {title: "是否锁定", key: "locked", editable: true}, {
            title: '操作',
            key: 'action',
            align: 'center',
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {
                    type: 'primary',
                    size: 'small',
                    icon: 'md-create'
                  },

                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.user.id = params.row.id;
                      this.user.username = params.row.username;
                      this.user.password = params.row.password;
                      this.user.locked = params.row.locked;
                      this.userModel = true;
                    }
                  }
                }, '编辑'),
                h('Button', {
                  props: {
                    type: 'error',
                    size: 'small',
                    icon: 'md-trash',
                  },
                  on: {
                    click: () => {
                      this.handleDelete(params);
                    }
                  }
                }, '删除')
              ]);
            }
          }
        ],
        tableData: [],
        total: 0,
        queryParams: {
          username: "",
          pageSize: 10,
          pageNum: 1
        },
        user: {
          id: null,
          username: "",
          password: "",
          locked: false

        },
        userRule: {
          username: [
            {required: true, message: '请输入用户名.', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码.', trigger: 'blur'}
          ]
        },
        userModel: false,
        loading: false
      };
    },
    methods: {
      handlePageSize(pageSize) {
        this.loading = true;
        this.queryParams.pageSize = pageSize;
        getUserPage(this.queryParams).then(res => {
          this.loading = false;
          this.tableData = res.data.data;
          this.total = res.data.total;
        });
      },
      handlePageNum(pageNum) {
        this.loading = true;
        this.queryParams.pageNum = pageNum;
        getUserPage(this.queryParams).then(res => {
          this.loading = false;
          this.tableData = res.data.data;
          this.total = res.data.total;
        });
      },
      handleDelete(params) {
        deleteUser(params.row.id).then(res => {
          this.$Message.success(res.data.msg);
          this.loading = true;
          getUserPage(this.queryParams).then(
            res => {
              this.loading = false;
              this.tableData = res.data.data;
              this.total = res.data.total;
            }
          );
        });
      },
      exportExcel() {
        this.$refs.tables.exportCsv({
          filename: `user-${new Date().valueOf()}.csv`
        });
      },
      handleSearch() {
        this.loading = true;
        getUserPage(this.queryParams).then(
          res => {
            this.loading = false;
            this.tableData = res.data.data;
            this.total = res.data.total;
          }
        );
      },
      handleSubmit(name) {
        this.$refs[name].validate((valid) => {
          if (valid) {
            this.loading = true;
            if (this.user.id === null) {
              createUser(this.user).then(
                res => {
                  this.loading = false;
                  this.userModel = false;
                  this.$Message.success(res.data.msg);
                  this.$refs[name].resetFields();
                  this.loading = true;
                  getUserPage(this.queryParams).then(
                    res => {
                      this.loading = false;
                      this.tableData = res.data.data;
                      this.total = res.data.total;
                    }
                  );
                });
            } else {
              updateUser(this.user).then(
                res => {
                  this.loading = false;
                  this.userModel = false;
                  this.$Message.success(res.data.msg);
                  this.$refs[name].resetFields();
                  this.loading = true;
                  getUserPage(this.queryParams).then(
                    res => {
                      this.loading = false;
                      this.tableData = res.data.data;
                      this.total = res.data.total;
                    }
                  );
                });
            }

          }
        })
      },
      handleCancel(name) {
        this.userModel = false;
        this.loading = false;
        this.$refs[name].resetFields();
      }
    },
    mounted() {
      this.loading = true;
      getUserPage(this.queryParams).then(
        res => {
          this.loading = false;
          this.tableData = res.data.data;
          this.total = res.data.total;
        }
      );
    }
  };
</script>

<style>
</style>
