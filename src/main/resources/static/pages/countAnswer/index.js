onload = () => {
    fetchUserList()
}

const countAnswers = []
const total = 0

const fetchUserList = () => {
    let params = {
        username: $('#username').val()
    }
    $.ajax({
        url: API_BASE_URL + '/answer',
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(params),
        contentType: 'application/json',
        success(res) {
            initAnswer(res.data[0])
            allAnswer(res.data[1])
            initAnswerForm(res.data[1])
        }
    })
}

initAnswer = (questions) => {
    for (let i = 0; i < questions.length; i++) {
        let answer = {
            content: [],
            conChoice: [],
            columns: [],
            colChoice: [],
            score: 0
        }
        let question = eval("(" + questions[i] + ")");
        switch (question.type) {
            case 1:
            case 2:
                for (let j = 0; j < question.content.length; j++) {
                    answer.content.push(question.content[j])
                    answer.conChoice.push(0)
                }
                break
            case 3:
                break
            case 4:
                for (let j = 0; j < question.content.length; j++) {
                    answer.content.push(question.content[j])
                }
                for (let j = 0; j < question.columns.length; j++) {
                    answer.columns.push(question.columns[j])
                    answer.colChoice.push([j, 0])
                }
                break
            case 5:
                for (let j = 0; j < question.content.length; j++) {
                    answer.content.push(question.content[j])
                }
                break
            default:
                console.log(question.type)
        }
        countAnswers.push(answer)
    }
    countAnswers.push(total)
}

allAnswer = (answers) => {

    for (let i = 0; i < answers.length; i++) {
        let answer = eval("(" + answers[i] + ")");
        countAnswers.total += 1
        switch (answer.type) {
            case 1:
            case 2:
                for (let j = 0; j < answer.choice.length; j++) {
                    countAnswers[i].conChoice[answer.choice[j]]++;
                }
                break
            case 3:
                break
            case 4:
                for (let j = 0; j < answer.content.length; j++) {
                    countAnswers[i].colChoice[answer.columns[j]][j][1]++;
                }
                break
            case 5:
                for (let j = 0; j < answer.choice.length; j++) {
                    countAnswers[i].conChoice[answer.choice[j]]++;
                    countAnswers[i].score += answer.score
                }
                break
            default:
                console.log(answer.type)
        }
    }
}

initAnswerForm = (questions) => {
    for (let i = 0; i < questions; i++) {
        let question = eval("(" + questions[i] + ")");
        editTable(question)
    }
}

editTable = (question) => {
    let type;
    switch (question.type) {
        case 1:
            type = '单选题'
            break
        case 2:
            type = '多选题'
            break
        case 3:
            type = '填空题'
            break
        case 4:
            type = '矩阵题'
            break
        case 5:
            type = '量表题'
            break
    }

    $(`.divSumData`).append(`
    <div class="title-item">
    <div class="title" exportbackup="1">
      <dl class="clearfix">
        <dt>第${question.qId}题：</dt>
        <dd>${question.title}<span>[${type}]</span></dd>
      </dl>
    </div>
    <div class="count unit">
      <div class="radiu-box" exportbackup="1">
        <table class="table${question.qId}">
          <tbody>
          <tr style="background:#f5f5f5;font-weight:600;">
            <td class="arrge">选项</td>
            <td id="sort-10000" align="center" class="arrge" style="width:50px;">小计</td>
            <td align="left" style="width:360px;">比例</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div cat="4" id="divChart4" type="0" class="divResultCss clearfix center-align" style="margin-top:10px;" dignore="1">
      <ul class="fr">
        <li class="liSelect tabletoggleli" index="-1">
            <img class="vam" src="static/static/images/list.png" onclick="editEcharts(5, ${question})" alt="折线">
            <span class="vam">表格</span></li>
        <li index="1">
            <img class="vam" src="static/static/images/pie.png" onclick="editEcharts(5, ${question})" alt="折线">
            <span class="vam" onclick="editEcharts(1, ${question})">饼状</span></li>
        <li index="6">
            <img class="vam" src="static/static/images/doughnut.png" onclick="editEcharts(5, ${question})" alt="折线">
            <span class="vam" onclick="editEcharts(2, ${question})">圆环</span></li>
        <li index="2">
            <img class="vam" src="static/static/images/bar.png" onclick="editEcharts(5, ${question})" alt="折线">
            <span class="vam" onclick="editEcharts(3, ${question})">柱状</span>
        </li>
        <li index="4">
            <img class="vam" src="static/static/images/bar2.png" onclick="editEcharts(5, ${question})" alt="折线">
            <span class="vam" onclick="editEcharts(4, ${question})">条形</span>
        </li>
        <li index="3">
            <img class="vam" src="static/static/images/line.png" onclick="editEcharts(5, ${question})" alt="折线">
            <span class="vam" onclick="editEcharts(5, ${question})">折线</span>
        </li>
      </ul>
    </div>
    <div style="clear:both;"></div>
  </div>
  `)
    if (question.type === 4) {
        $(`#table${question.qId}`).html(``)
        $(`#table${question.qId}`).append(`
        <tbody>
            <tr><td>题目\选项</td></tr>
        </tbody>
        <thead></thead>
        `)
        for (let i = 0; i < question.columns.length; i++) {
            $(`#table${question.qId} > tbody > tr`).append(`
                <td>${question.columns[i]}</td>
            `)
        }
        for (let i = 0; i < question.content.length; i++) {
            $(`#table${question.qId} > thead`).append(`
                <tr class="${i}"><td>${question.columns[i]}</td></tr>
            `)
        }
        for (let i = 0; i < question.content.length; i++) {
            for (let j = 0; j < question.columns.length; j++) {
                $(`#table${question.qId} > thead .#{i}`).append(`
                    <td>${countAnswers[question.qId - 1].colChoice[j][1]}</td>
                `)
            }
        }
    } else {
        for (let i = 0; i < question.content; i++) {
            $(`#table${question.qId} > tbody`).append(`
          <tr>
            <td val="1">${question.content[i]}</td>
            <td align="center">${countAnswers[question.qId - 1].conChoice[i]}</td>
            <td percent="100">
              <div class="bar">
                <div style="width: ${countAnswers[question.qId - 1].conChoice[i] * 100.0 / countAnswers[question.qId - 1].score}%; display: block;" class="precent barcont"></div>
              </div>
              <div style="margin-top:3px;float:left;">
                ${countAnswers[question.qId - 1].conChoice[i] * 100.0 / countAnswers[question.qId - 1].score}%
              </div>
              <div style="clear:both;"></div>
            </td>
          </tr>
        `)
        }
    }

    $(`#table${question.qId} > tbody`).append(`
  <tr style="background:#f5f5f5;font-weight:600;" total="1">
            <td><b>本题有效填写人次</b></td>
            <td align="center"><b>${countAnswers[question.qId - 1].score}</b></td>
            <td></td>
          </tr>
  `)
    $(`#title-item`).append(`
    <div class="editEcharts${question.qId}"></div>
  `)

}


editEcharts = (type, question) => {
    alert('点击成功')
    switch (type) {
        case 1:
            pieCharts(question)
            break
        case 2:
            doughnutChart(question)
            break
        case 3:
            barChart(question)
            break
        case 4:
            barChart2(question)
            break
        case 5:
            lineChart(question)
            break
    }
}
//饼状图
pieCharts = (question) => {
    let datas = []
    for (let i = 0; i < question.content.length; i++) {
        let data = {
            value: countAnswers[question.qId - 1].conChoice[i],
            name: question.content[i]
        }
        datas.push(data)
    }
    // 绘制echarts图表
    let chart = echarts.init(document.getElementById('editEcharts' + question.qId));

    let option = {
        title: {
            text: question.title,
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left'
        },
        series: [
            {
                name: question.title,
                type: 'pie',
                radius: '50%',
                data: datas,
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    // 渲染图表
    chart.setOption(option);
}
//圆环图
doughnutChart = (question) => {
    let datas = []
    for (let i = 0; i < question.content.length; i++) {
        let data = {
            value: countAnswers[question.qId - 1].conChoice[i],
            name: question.content[i]
        }
        datas.push(data)
    }
    // 绘制echarts图表
    let chart = echarts.init(document.getElementById('editEcharts' + question.qId));

    let option = {
        title: {
            text: question.title,
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            top: '5%',
            left: 'center'
        },
        series: [
            {
                name: question.title,
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                itemStyle: {
                    borderRadius: 10,
                    borderColor: '#fff',
                    borderWidth: 2
                },
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: 40,
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: datas
            }
        ]
    };
    // 渲染图表
    chart.setOption(option);
}
//柱状图
barChart = (question) => {
    let datas = []
    let series = []
    if (question.columns.length === 0) {
        series.push({type: 'bar', seriesLayoutBy: 'row'})
        let data1 = ['content']
        for (let j = 0; j < question.content; j++) {
            data1.push(question.content[j])
        }
        datas.push(data1)
        let data2 = ['choice']
        for (let j = 0; j < countAnswers[question.qId - 1].conChoice.length; j++) {
            data2.push(countAnswers[question.qId - 1].conChoice[j])
            series.push({type: 'bar', xAxisIndex: 1, yAxisIndex: 1})
        }
        datas.push(data2)
    } else {
        let data1 = ['content']
        for (let j = 0; j < question.content.length; j++) {
            data1.push(question.content[j])
        }
        datas.push(data1)
        for (let i = 0; i < question.content.length; i++) {
            let data = [question.content[i]]
            for (let j = 0; j < question.columns.length; j++) {
                data.push(countAnswers[question.qId - 1].colChoice[i][j][1])
            }
            datas.push(data)
            series.push({type: 'bar', seriesLayoutBy: 'row'})
        }
        for (let j = 0; j < question.content.length; j++) {
            series.push({type: 'bar', xAxisIndex: 1, yAxisIndex: 1})
        }
    }

    // 绘制echarts图表
    let chart = echarts.init(document.getElementById('editEcharts' + question.qId));
    let option = {
        legend: {},
        tooltip: {},
        dataset: {
            source: datas
        },
        xAxis: [
            {type: 'category', gridIndex: 0},
            {type: 'category', gridIndex: 1}
        ],
        yAxis: [{gridIndex: 0}, {gridIndex: 1}],
        grid: [{bottom: '55%'}, {top: '55%'}],
        series: series
    };
    chart.setOption(option)
}
//条形图
barChart2 = (question) => {
    let yAxis = {
        type: 'category',
        data: []
    }
    let series = []
    for (let i = 0; i < question.content.length; i++) {
        yAxis.data.push(question.content[i])
    }

    if (question.columns.length === 0) {
        let serie = {
            name: 'choice',
            type: 'bar',
            data: []
        }
        for (let i = 0; i < question.content.length; i++) {
            serie.data.push(countAnswers[question.qId - 1].conChoice[i])
        }
        series.push(serie)
    } else {
        for (let i = 0; i < question.content.length; i++) {
            let serie = {
                name: question.content[i],
                type: 'bar',
                data: []
            }
            for (let j = 0; j < question.columns.length; j++) {
                serie.data.push(countAnswers[question.qId - 1].colChoice[i][j][1])
            }
            series.push(serie)
        }
    }

    // 绘制echarts图表
    let chart = echarts.init(document.getElementById('editEcharts' + question.qId));
    let option = {
        title: {
            text: question.title
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {},
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        yAxis: yAxis,
        series: series
    };
    // 渲染图表
    chart.setOption(option);
}
//折线图
lineChart = (question) => {
    let lData = []
    let xData = []
    let series = []

    for (let i = 0; i < question.content.length; i++) {
        xData.push(question.content[i])
    }
    if (question.columns.length === 0) {
        lData.push('choice')
        let serie = {
            name: 'choice',
            type: 'line',
            stack: 'Total',
            data: []
        }
        for (let i = 0; i < question.content.length; i++) {
            data.push(countAnswers[question.qId - 1].conChoice[i])
        }
        series.push(serie)
    } else {
        for (let i = 0; i < question.columns.length; i++) {
            lData.push(question.columns[i])
            let serie = {
                name: 'choice',
                type: 'line',
                stack: 'Total',
                data: []
            }
            for (let j = 0; j < question.content.length; j++) {
                serie.data.push(countAnswers[question.qId - 1].colChoice[j][i][1])
            }
            series.push(serie)
        }
    }

    // 绘制echarts图表
    let chart = echarts.init(document.getElementById('editEcharts' + question.qId));
    let option = {
        title: {
            text: 'Stacked Line'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: lData
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        toolbox: {
            feature: {
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: xData
        },
        yAxis: {
            type: 'value'
        },
        series: series
    };
    chart.setOption(option)
}