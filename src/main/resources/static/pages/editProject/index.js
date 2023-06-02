let project = {}

onload = () => {
  $('#headerUsername').text($util.getItem('userInfo').username)
  $('#headerDivB').text('编辑项目')
  project = $util.getPageParam('editProject')

  $('#projectName').val(project.projectName)
  $('#projectDescribe').val(project.projectDescription)
}

const handleSaveChange = () => {
  let params = {
    projectId: project.projectId,
    projectName: $('#projectName').val(),
    projectDescription: $('#projectDescribe').val()
  }
  if (!params.projectName) return alert('项目名称不能为空！')
  if (!params.projectDescription) return alert('项目描述不能为空！')
  $.ajax({
    url: API_BASE_URL + '/project',
    type: "POST",
    data: JSON.stringify(params),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      alert(res.message)
      location.href = "/pages/questionnaire/index.html"
    }
  })
}
