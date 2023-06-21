onload = () => {
    $('#headerUsername').text($util.getItem('userInfo').username)
    $('#headerDivB').text('创建调查问卷')

    $('#startTime').datetimepicker({
        language: 'zh-CN', // 显示中文
        format: 'yyyy-mm-dd', // 显示格式
        minView: "month", // 设置只显示到月份
        initialDate: new Date(), // 初始化当前日期
        autoclose: true, // 选中自动关闭
        todayBtn: true // 显示今日按钮
    })
    $('#endTime').datetimepicker({
        language: 'zh-CN', // 显示中文
        format: 'yyyy-mm-dd', // 显示格式
        minView: "month", // 设置只显示到月份
        initialDate: new Date(), // 初始化当前日期
        autoclose: true, // 选中自动关闭
        todayBtn: true // 显示今日按钮
    })
}

const handleCreateQuestionnaire = () => {
    const urlParams = new URLSearchParams(window.location.search)


    let params = {
        name: $('#surveyName').val(),
        description: $('#surveyDescription').val(),
        projectBelong: urlParams.get('id'),
        type: urlParams.get('type'),
        createTime: $('#startTime input').val(),
        finishTime: $('#endTime input').val(),
    }
    if (!params.name) return alert('问卷名称不能为空！')
    if (!params.description) return alert('问卷描述不能为空！')
    $.ajax({
            url: API_BASE_URL + '/questionnaire' + '/createQuestionnaire',
            type: "PUT",
            data: JSON.stringify(params),
            dataType: "json",
            contentType: "application/json",
            success: function (res) {
                $util.setPageParam("questionnaire",res.data)
                window.location.href = "http://localhost:8080/pages/designQuestionnaire/index.html"
                alert('创建成功！');
                // 执行其他操作，如重定向到其他页面或刷新数据列表等
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('创建失败！请修改问卷名称!')
            }
        }
    )
}
