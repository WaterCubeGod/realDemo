const onLogin = () => {
  let params = {
    username: $('#username').val().trim(),
    password: $('#password').val().trim()
  }
  if (!params.username) return alert('请输入id！')
  if (!params.password) return alert('请输入密码！')
  $.ajax({
    url: API_BASE_URL + '/users/' + params.username + '/' + params.password,
    type: "GET",
    dataType: "json",
    contentType: "application/json",
    success(res) {
      if (res.data != null) {
        $util.setItem('userInfo', res.data)
        location.href = "/pages/questionnaire/index.html"
      } else {
        alert(res.message)
      }
    }
  })
}

