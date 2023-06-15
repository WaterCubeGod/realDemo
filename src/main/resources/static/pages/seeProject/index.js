let project = {}
onload = () => {
  $('#headerDivB').text('项目详情')

  project = $util.getPageParam('seeProject')

  $('#projectName').text(project.projectName)
  $('#createTime').text(project.createTime)
  // $('#personInCharge').text(project.userId)
  $('#personInCharge').text("张三")
  $('#projectDescription').text(project.projectDescription)
}

const fetchProjectInfo = (id) => {
  let params = {
    id
  }
  $.ajax({
    url: API_BASE_URL + '/project/' + project.userId + "/" + project.projectName,
    type: "POST",
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
}