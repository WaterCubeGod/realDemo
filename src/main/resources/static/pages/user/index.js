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
    count: (pageNum - 1) * pageSize,
    username: $('#username').val()
  }
  $.ajax({
    url: API_BASE_URL + '/users',
    type: 'POST',
    dataType: 'json',
    data: JSON.stringify(params),
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
              <button type="button" class="btn btn-link" onclick="handleEdit(${item.id})">编辑</button>
              <button type="button" class="btn btn-link btn-red">关闭</button>
              <button type="button" class="btn btn-link btn-red" onclick="deleteUser(${item.id},'${item.password}')">删除</button>
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
        $('#currentPage').text(pageNum)
        $('#totalPage').text(totalPag)
        fetchUserList()
      } else {
        alert("最大页数")
      }
    }
  })
}

const handleCreateUser = () => {
  $util.setPageParam('user', undefined)
  $util.setPageParam('code', 0);
  location.href = '/pages/createUser/index.html'
}

const handleBulkImport = () => {
  $('#fileInput').click(); // 触发文件选择对话框
}

// 当文件选择发生变化时执行以下代码
$('#fileInput').change(function() {
  let file = this.files[0]; // 获取用户选择的文件对象

  // 创建FormData对象并将文件对象添加到其中
  let formData = new FormData();
  formData.append('multiFile', file);

  // 发送Ajax请求
  $.ajax({
    url: API_BASE_URL + '/users/all',
    type: 'PUT',
    data: formData,
    processData: false,
    contentType: false,
    success: function(response) {
      // 请求成功的处理逻辑
      if(response.flag === true){
        alert("导入成功")
      }else {
        alert("导入失败,请检查你的表是否符合规范")
      }
    },
    error: function(xhr, status, error) {
      // 请求出错的处理逻辑
      alert(error)
    }
  });
});

const handleEdit = (id) => {
  let user = userList.filter(item => item.id === id)[0]
  $util.setPageParam('user', user)
  $util.setPageParam('code', 1);
  location.href = '/pages/createUser/index.html'
}

const exportExcel = () => {
  // 发起Ajax请求获取后台数据
  $.ajax({
    url: API_BASE_URL + '/users/export',
    type: "POST",
    dataType: "json",
    contentType: "application/json",
    success: function(response) {
      // 导出Excel
      let data = response.data;

      // 创建一个新的Workbook（工作簿）
      let wb = XLSX.utils.book_new();

      // 创建一个新的Worksheet（工作表）
      let ws = XLSX.utils.json_to_sheet(data);

      // 将工作表添加到工作簿
      XLSX.utils.book_append_sheet(wb, ws, "Sheet1");

      // 将工作簿转换为Excel文件的二进制数据
      let wbout = XLSX.write(wb, { bookType: "xlsx", type: "binary" });

      // 下载Excel文件
      saveAs(new Blob([s2ab(wbout)], { type: "application/octet-stream" }), "export.xlsx");
    },
    error: function(error) {
      console.log("Error:", error);
    }
  });
}

// 字符串转ArrayBuffer
function s2ab(s) {
  let buf = new ArrayBuffer(s.length);
  let view = new Uint8Array(buf);
  for (let i = 0; i < s.length; i++) {
    view[i] = s.charCodeAt(i) & 0xff;
  }
  return buf;
}
