let project = {}
onload = () => {
  $('#headerDivB').text('项目详情')

  project = $util.getPageParam('seeProject')

  $('#projectName').text(project.projectName)
  $('#createTime').text(project.createTime)
  $('#personInCharge').text(project.userId)
  // $('#personInCharge').text("张三")
  $('#projectDescription').text(project.projectDescription)
  fetchProjectInfo(project.projectId)
}

const fetchProjectInfo = (id) => {
  let params = {
    id
  }
  $.ajax({
    url: API_BASE_URL + '/project/' + project.userId + "/" + project.projectName,
    type: "PUT",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      let info = res.data
      console.log(info, 'res')
      $('#projectName').text(info.projectName)
      $('#createTime').text(info.createTime)
      $('#projectDescription').text(info.projectDescription)
    }
  })
  $.ajax({
    url: API_BASE_URL + '/questionnaire/selectAll',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      let info = res.data
      console.log(info, 'res')
      res.data.map((item, index) => {
        $('#table #tbody').append(`
          <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.createTime}</td>
            <td>
              <button type="button" class="btn btn-link" onclick="preview(${item.id})">预览</button>
              <button type="button" class="btn btn-link" onclick="publish(${item.id})">发布</button>
              <button type="button" class="btn btn-link btn-red">说明</button>
              <button type="button" class="btn btn-link btn-red" >统计</button>
            </td>
          </tr>
        `)
      })
    }
  })
}

const preview = (id) => {
  $util.setPageParam("id",id);
  location.href = '/pages/answerSheet/index.html';
}

const publish = (id) => {
  $.ajax({
    url: API_BASE_URL + '/questionnaire/submit?id=' + id,
    type: "GET",
    dataType: "json",
    contentType: "application/json",
    success(res) {
      let link = res.data
      alert("发布成功,链接为:" + link)
    }
  })
}