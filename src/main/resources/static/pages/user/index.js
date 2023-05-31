onload = () => {
  $('#headerUsername').text($util.getItem('userInfo').username)
  $('#headerDivB').text('用户管理')
  $('#totalPage').text(totalPag)
  fetchUserList()
}

let pageNum = 1
let pageSize = 10
let totalPag = location.search.slice(location.search.indexOf("=") + 1)
let userList = []

const fetchUserList = () => {
  let params = {
    pageNum,
    pageSize,
    totalPag,
    userName: $('#username').val()
  }
  $.ajax({
    url: API_BASE_URL + '/users/' + (params.pageNum - 1) * pageSize,
    type: 'GET',
    dataType: 'json',
    contentType: 'application/json',
    success(res) {
      $('#table #tbody').html('')
      userList = res.data
      res.data.map((item, index) => {
        let TYPE = '用户'
        if(item.type === 1) {
          TYPE = '管理员'
        }
        $('#table #tbody').append(`
          <tr>
            <td>${index + 1}</td>
            <td>${item.username}</td>
            <td>${item.password}</td>
            <td>${item.id}</td>
            <td>${TYPE}</td>
            <td>
              <button type="button" class="btn btn-link">重置密码</button>
              <button type="button" class="btn btn-link" onclick="handleEdit('${item.id}')">编辑</button>
              <button type="button" class="btn btn-link btn-red">关闭</button>
              <button type="button" class="btn btn-link btn-red" onclick="deleteUser('${item.id}','${item.password}')">删除</button>
            </td>
          </tr>
        `)
      })
    }
  })
}
const deleteUser = (id,password) => {
  let params = {
    id: id,
    password: password
  }
  $.ajax({
    url: API_BASE_URL + '/users/' + parseInt(params.id) + '/' + params.password,
    type: 'DELETE',
    dataType: 'json',
    contentType: 'application/json',
    success(res) {
      alert(res.message)
      fetchUserList()
    }
  })
}
const handleTableChange = (page) => {

  $.ajax({
    url: API_BASE_URL + '/users',
    type: "GET",
    dataType: "json",
    contentType: "application/json",
    success(res) {
      totalPag = res.data

      if (res.data != null) {
        if (page === 1) {
          if (pageNum === 1) return
          pageNum--
        } else if (page === 2) {
          if(pageNum < res.data){
            pageNum++
          }
        } else if (page === 3) {
          if(pageNum < res.data){
            pageNum = +$('#goNum').val()
          } else {
            alert("超过最大页数")
          }
        }
      } else {
        alert("最大页数")
      }
    }
  })

  $('#currentPage').text(pageNum)
  $('#totalPage').text(totalPag)
  fetchUserList()
}

const handleCreateUser = () => {
  $util.setPageParam('user', undefined)
  location.href = '/pages/createUser/index.html'
}

const handleEdit = (id) => {
  let user = userList.filter(item => item.id === id)[0]
  $util.setPageParam('user', user)
  location.href = '/pages/createUser/index.html'
}
