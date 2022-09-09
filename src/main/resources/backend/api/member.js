function getMemberList (params) {
  return $axios({
    url: '/backend/page/member/queryEmployeeByNameForPage.do',
    method: 'get',
    params
  })
}

// 修改---启用禁用接口
function enableOrDisableEmployee (params) {
  return $axios({
    url: '/backend/page/member/updateEmployeeById.do',
    method: 'put',
    data: { ...params }
  })
}

// 新增---添加员工
function addEmployee (params) {
  return $axios({
    url: '/backend/page/member/addEmployee.do',
    method: 'post',
    data: { ...params }
  })
}

// 修改---添加员工
function editEmployee (params) {
  return $axios({
    url: '/backend/page/member/updateEmployeeById.do',
    method: 'put',
    data: { ...params }
  })
}

// 修改页面反查详情接口
function queryEmployeeById (id) {
  return $axios({
    url: `/backend/page/member/queryEmployeeById.do?id=`+id,
    method: 'get'
  })
}