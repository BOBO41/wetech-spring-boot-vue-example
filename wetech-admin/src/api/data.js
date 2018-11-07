import axios from '@/libs/api.request'

export const deleteUser = (userId) => {
  return axios.request({
    url: '/user/' + userId,
    method: 'delete'
  })
}

export const createUser = (data) => {
  return axios.request({
    url: '/user',
    method: 'post',
    data: data
  })
}

export const updateUser = (data) => {
  return axios.request({
    url: '/user',
    method: 'put',
    data: data
  })
}

export const getUserPage = (params) => {
  return axios.request({
    url: '/users',
    method: 'get',
    params: params
  })
}

export const getTableData = () => {
  return axios.request({
    url: 'get_table_data',
    method: 'get'
  })
}

export const getDragList = () => {
  return axios.request({
    url: 'get_drag_list',
    method: 'get'
  })
}

export const errorReq = () => {
  return axios.request({
    url: 'error_url',
    method: 'post'
  })
}

export const saveErrorLogger = info => {
  return axios.request({
    url: 'save_error_logger',
    data: info,
    method: 'post'
  })
}
