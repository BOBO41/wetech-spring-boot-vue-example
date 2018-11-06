<template>
  <div>
    <Card>
      <Button style="margin: 10px 0;" type="primary" @click="exportExcel">导出为Csv文件</Button>
      <tables ref="tables" editable searchable search-place="top" v-model="tableData" :columns="columns" @on-delete="handleDelete"/>
      <Page style="margin: 10px 0;" show-total :total="total" @on-change="handlePageNum" @on-page-size-change="handlePageSize" show-elevator show-sizer/>
    </Card>
  </div>
</template>

<script>
  import Tables from '_c/tables'
  import {getUserData} from '@/api/data'

  export default {
    name: 'tables_page',
    components: {
      Tables
    },
    data() {
      return {
        columns: [
          {title: '编号', key: 'id', sortable: true},
          {title: '用户名', key: 'username', sortable: true},
          {title: '密码', key: 'password', sortable: true},
          {title: '是否锁定', key: 'locked', editable: true},
          {
            title: 'Handle',
            key: 'handle',
            options: ['delete'],
            button: [
              (h, params, vm) => {
                return h('Poptip', {
                  props: {
                    confirm: true,
                    title: '你确定要删除吗?'
                  },
                  on: {
                    'on-ok': () => {
                      vm.$emit('on-delete', params)
                      vm.$emit('input', params.tableData.filter((item, index) => index !== params.row.initRowIndex))
                    }
                  }
                }, [
                  h('Button', '自定义删除')
                ])
              }
            ]
          }
        ],
        tableData: [],
        total: 0,
        pageSize: 10,
        pageNum: 1
      }
    },
    methods: {
      handlePageSize(pageSize) {
        let params = {pageSize: pageSize, pageNum: this.pageNum};
        getUserData(params).then(res => {
          this.tableData = res.data.data;
          this.total = res.data.total;
        })
      },
      handlePageNum(pageNum) {
        let params = {pageSize: this.pageSize, pageNum: pageNum};
        getUserData(params).then(res => {
          this.tableData = res.data.data;
          this.total = res.data.total;
        })
      },
      handleDelete(params) {
        console.log(params)
      },
      exportExcel() {
        this.$refs.tables.exportCsv({
          filename: `table-${(new Date()).valueOf()}.csv`
        })
      }
    },
    mounted() {
      getUserData({pageSize: this.pageSize, pageNum: this.pageNum}).then(res => {
        this.tableData = res.data.data;
        this.total = res.data.total;
      });
    }
  }
</script>

<style>

</style>
