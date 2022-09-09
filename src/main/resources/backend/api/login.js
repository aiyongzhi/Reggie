function loginApi(data) {
  return $axios({
    'url': '/backend/page/login/login.do',
    'method': 'post',
    data
  })
}

function logoutApi(){
  return $axios({
    'url': '/backend/logout.do',
    'method': 'post',
  })
}
