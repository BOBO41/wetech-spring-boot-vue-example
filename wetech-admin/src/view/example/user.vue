<template>
  <Card>
    <Card style="margin: 10px 0;">
      <Row>
        <Col span="8">
          <div>用户名：<Input v-model.trim="queryParams.username" placeholder="请输入用户名" clearable style="width: auto"/></div>
        </Col>
        <Col span="6" offset="10" style="text-align: right;">
          <Button style="margin: 5px;" type="primary" icon="md-download" @click="exportExcel">导出为Csv文件</Button>
          <Button style="margin: 5px;" type="success" @click="createModel = true" icon="md-add">新增</Button>
          <Button style="margin: 5px;" type="info" @click="handleSearch" icon="md-search">查询</Button>
          <Modal title="Title" v-model="createModel" :loading="loading" :mask-closable="false">
            <p>Content of dialog</p>
            <p>Content of dialog</p>
            <p>Content of dialog</p>
          </Modal>
        </Col>
      </Row>
    </Card>
    <tables ref="tables" editable v-model="tableData" :columns="columns" @on-delete="handleDelete"/>
    <Page style="margin: 10px 0;" show-total :total="total" @on-change="handlePageNum" @on-page-size-change="handlePageSize" show-elevator show-sizer/>
  </Card>
</template>

<script>
  import Tables from "_c/tables";
  import {getUserPage} from "@/api/data";

  export default {
    name: "tables_page",
    components: {
      Tables
    },
    data() {
      return {
        columns: [
          {title: "编号", key: "id", sortable: true},
          {title: "用户名", key: "username", sortable: true},
          {title: "密码", key: "password", sortable: true},
          {title: "是否锁定", key: "locked", editable: true},
          {
            title: "Handle",
            key: "handle",
            options: ["delete"],
            button: [
              (h, params, vm) => {
                return h(
                  "Poptip",
                  {
                    props: {
                      confirm: true,
                      title: "你确定要删除吗?"
                    },
                    on: {
                      "on-ok": () => {
                        vm.$emit("on-delete", params);
                        vm.$emit(
                          "input",
                          params.tableData.filter(
                            (item, index) => index !== params.row.initRowIndex
                          )
                        );
                      }
                    }
                  },
                  [h("Button", "自定义删除")]
                );
              }
            ]
          }
        ],
        tableData: [],
        total: 0,
        queryParams: {
          username: "",
          pageSize: 10,
          pageNum: 1
        },
        createModel: false,
        loading: true
      };
    },
    methods: {
      handlePageSize(pageSize) {
        this.queryParams.pageSize = pageSize;
        getUserPage(this.queryParams).then(res => {
          this.tableData = res.data.data;
          this.total = res.data.total;
        });
      },
      handlePageNum(pageNum) {
        this.queryParams.pageNum = pageNum;
        getUserPage(this.queryParams).then(res => {
          this.tableData = res.data.data;
          this.total = res.data.total;
        });
      },
      handleDelete(params) {
        console.log(params);
      },
      exportExcel() {
        this.$refs.tables.exportCsv({
          filename: `user-${new Date().valueOf()}.csv`
        });
      },
      handleSearch() {
        getUserPage(this.queryParams).then(
          res => {
            this.tableData = res.data.data;
            this.total = res.data.total;
          }
        );
      }
    },

    mounted() {
      getUserPage(this.queryParams).then(
        res => {
          this.tableData = res.data.data;
          this.total = res.data.total;
        }
      );
    }
  };
</script>

<style>
</style>
