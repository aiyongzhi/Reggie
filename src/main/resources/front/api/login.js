function loginApi(data) {
    return $axios({
      'url': '/front/page/login/login.do',
      'method': 'post',
      data
    })
}

function sendMsgApi(data) {
    return $axios({
        'url': '/front/page/login/sendMsg.do',
        'method': 'post',
        data
    })
}

function loginoutApi() {
  return $axios({
    'url': '/user/loginout',
    'method': 'post',
  })
}

  