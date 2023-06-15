onload = () => {
  $('#headerUsername').text($util.getItem('userInfo').username)
  handleHeaderLoad()
  fetchProjectList()
}

let projectList = []

const fetchProjectList = () => {
  let params = {
    projectName: $('#projectName').val()
  }
  $.ajax({
    url: API_BASE_URL + '/project/' + $util.getItem('userInfo').id,
    type: "POST",
    dataType: "json",
    data: JSON.stringify(params),
    contentType: "application/json",
    success(res) {
      projectList = res.data
      $('#content').html('')

      res.data.map(item => {
        $('#content').append(`
          <div class="list">
            <div class="list-header">
              <div>${item.projectName}</div>
              <div>
                <button type="button" class="btn btn-link" onclick="onCreateQuestionnaire()">创建问卷</button>
                <button type="button" class="btn btn-link" onclick="onSeeProject(${item.projectId})">查看</button>
                <button type="button" class="btn btn-link" onclick="onEditProject(${item.projectId})">编辑</button>
                <button type="button" class="btn btn-link" onclick="onDelProject(${item.projectId})">删除</button>
              </div>
            </div>
            <div class="list-footer">
              <div>暂无调查问卷或问卷已过期</div>
            </div>
          </div>
        `)
      })
    }
  })
}

const onCreatePrject = () => {

  location.href = "/pages/createProject/index.html"
}

const onCreateQuestionnaire = () => {
  location.href = "/pages/createQuestionnaire/index.html"
}

const onSeeProject = (id) => {
  let project = projectList.filter(item => item.projectId === id)[0]
  $util.setPageParam('seeProject', project)
  location.href = "/pages/seeProject/index.html"
}

const onEditProject = (id) => {
  console.log()
  let project = projectList.filter(item => item.projectId === id)[0]
  $util.setPageParam('editProject', project)
  location.href = "/pages/editProject/index.html"
}

const onDelProject = (pid) => {
  let state = confirm("确认删除该项目吗？")

  if (state) {
    let params = {
      projectId:pid
    }
    //alert(JSON.stringify(params))
    $.ajax({
      url: API_BASE_URL + '/project',
      type: "DELETE",
      data: JSON.stringify(params),
      dataType: "json",
      contentType: "application/json",
      success(res) {
        alert(res.message)
        fetchProjectList()
      }
    })
  }
  
}
