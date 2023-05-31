onload = () => {
  $('#headerUsername').text($util.getItem('userInfo').username)
  $('#headerDivB').text('创建用户')

  let user = $util.getPageParam('user')
  // console.log('--- 用户信息 ---');
  // console.log(user);
  if (user) {
    $('#username').val(user.username)
    $('#password').val(user.password)
    $('#type').val(user.type)
  }
}

const handleCreateUser = () => {

  if (!$('#username').val()) return alert('账号不能为空！')
  if (!$('#password').val()) return alert('密码不能为空！')

  let user = $util.getPageParam('user');
  console.log('--- user ---')
  console.log(user);
  if(!user) {
    user = {};
  }
 
  user.username = $('#username').val();
  user.password = $('#password').val();
  user.type = 0;

  // 修改
  if(user.id) {

    $.ajax({
      url: API_BASE_URL + '/admin/modifyUserInfo',
      type: 'POST',
      data: JSON.stringify(user),
      dataType: 'json',
      contentType: 'application/json',
      success(res) {
        if (res.code === "666") {
          location.href = '/pages/user/index.html'
        } else {
          alert(res.message)
        }
      }
    })

  } else {
    // 新建
    $.ajax({
      url: API_BASE_URL + '/users',
      type: 'PUT',
      data: JSON.stringify(user),
      dataType: 'json',
      contentType: 'application/json',
      success(res) {
        if (res.flag === true) {
          location.href = '/pages/user/index.html'
        } else {
          alert(res.message)
        }
      }
    })
  }

  // let params = {
  //   username: $('#username').val(),
  //   password: $('#password').val(),
  //   startTime: $('#startDate').val() && new Date($('#startDate').val()).getTime(),
  //   stopTime: $('#endDate').val() && new Date($('#endDate').val()).getTime()
  // }
  // if (!params.username) return alert('账号不能为空！')
  // if (!params.password) return alert('密码不能为空！')
  // if (!params.startTime) return alert('开始时间不能为空！')
  // if (!params.stopTime) return alert('结束时间不能为空！')
  // $.ajax({
  //   url: API_BASE_URL + '/admin/addUserInfo',
  //   type: 'POST',
  //   data: JSON.stringify(params),
  //   dataType: 'json',
  //   contentType: 'application/json',
  //   success(res) {
  //     if (res.code === "666") {
  //       location.href = '/pages/user/index.html'
  //     } else {
  //       alert(res.message)
  //     }
  //   }
  // })
}
