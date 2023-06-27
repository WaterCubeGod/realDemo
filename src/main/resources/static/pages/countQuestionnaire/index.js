onload = () => {
  $('#headerUsername').text($util.getItem('userInfo').username)
  $('#headerDivB').text('项目问卷统计')
  $('#totalPage').text(totalPag)
  console.log($util.getPageParam('project'))
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
    url: API_BASE_URL + '/answer/count',
    type: 'POST',
    dataType: 'json',
    // data: JSON.stringify(params),
    data:JSON.stringify($util.getPageParam('project')),
    contentType: 'application/json',
    success(res) {
      $('#table #tbody').html('')
      res.data.map((item, index) => {
        $('#table #tbody').append(`
          <tr>
            <td>${item.qnName}</td>
            <td>${item.userName}</td>
            <td>${item.createTime}</td>
            <td>
              <button type="button" class="btn btn-link" onclick="showDetail(${item.aId},${item.qnId})" value="${item.qnId}" name="${item.aId}">明细</button>
            </td>
          </tr>
        `)
      })
    }
  })
}

const handleTableChange = (page) => {

  $.ajax({
    url: API_BASE_URL + '/answer/count',
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

const showDetail = (aId,qnId)=>{
  window.location.href = API_BASE_URL + '/pages/answerSheet/index.html?detail=1&qnId=' + qnId + '&aId=' + aId
}

