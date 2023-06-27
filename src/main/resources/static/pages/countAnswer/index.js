onload = () => {
    fetchUserList()
}

const countAnswers = []
let questionLength = 0
const fetchUserList = () => {
    const path = new URLSearchParams(window.location.search)
    $.ajax({
        url: API_BASE_URL + '/answer/statistics',
        type: 'POST',
        data: { qnId: path.get('qnId') }, // 直接传递整数值
        success(res) {
            console.log(res)
            questionLength = res.data[0].length
            initAnswer(res.data[0])
            allAnswer(res.data[1])
            initAnswerForm(res.data[0])
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
            score: 0,
            total: 0
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
                    let columns = []
                    for (let k = 0; k < question.columns.length; k++) {
                        columns.push(0)
                    }
                    answer.colChoice.push(columns)
                }
                break
            case 5:
                for (let j = 0; j < question.content.length; j++) {
                    answer.content.push(question.content[j])
                    answer.conChoice.push(0)
                }
                break
            default:
                console.log(question.type)
        }
        countAnswers.push(answer)
    }
}

allAnswer = (answers) => {

    for (let i = 0; i < answers.length; i++) {
        let answer = eval("(" + answers[i] + ")");
        switch (answer.type) {
            case 1:
            case 2:
                for (let j = 0; j < answer.choice.length; j++) {
                    countAnswers[answer.qId].conChoice[answer.choice[j]]++;
                    countAnswers[answer.qId].total ++
                }
                break
            case 3:
                answer.total ++
                break
            case 4:
                for (let j = 0; j < answer.choice.length; j++) {
                    countAnswers[answer.qId].colChoice[answer.choice[j]][answer.columns[j]]++;
                }
                countAnswers[answer.qId].total ++
                break
            case 5:
                for (let j = 0; j < answer.choice.length; j++) {
                    countAnswers[answer.qId].conChoice[answer.choice[j]]++;
                    countAnswers[answer.qId].score += answer.score
                }
                countAnswers[answer.qId].total ++
                break
            default:
                console.log(answer.type)
        }
    }

    console.log(countAnswers)
}

initAnswerForm = (questions) => {
    for (let i = 0; i < questions.length; i++) {
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

    let questionJson = JSON.stringify(question)
    questionJson = htmlspecialchars(questionJson)

    $(`.container`).append(`
    <div class="title-item${question.qId}">
    <div class="title" exportbackup="1">
      <dl class="clearfix">
        <dt>第${question.qId}题：</dt>
        <dd>${question.title}<span>[${type}]</span></dd>
        <span onclick="countSame(${questionJson})">同类问题统计</span>
      </dl>
    </div>
    <div class="count unit">
      <div class="radiu-box" exportbackup="1">
        <table id="table${question.qId}">
          <thead>
          <tr style="background:#f5f5f5;font-weight:600;">
            <td class="arrge">选项</td>
            <td id="sort-10000" align="center" class="arrge" style="width:50px;">小计</td>
            <td align="left" style="width:360px;">比例</td>
          </tr>
          </thead>
          <tbody></tbody>
        </table>
      </div>
    </div>
    <div cat="4" id="divChart4" type="0" class="divResultCss clearfix center-align" style="margin-top:10px;" dignore="1">
      <ul class="fr">
        <li class="liSelect tabletoggleli" index="-1">
            <img class="vam" src="../../static/images/list.png" style="width: 1.3em; height: 1.3em;" alt="折线">
            <span class="vam">表格</span></li>
        <li index="1">
            <img class="vam" src="../../static/images/pie.png" onclick="editEcharts(1, ${questionJson})" style="width: 1.3em; height: 1.3em;" alt="折线">
            <span class="vam" onclick="editEcharts(1, ${questionJson})">饼状</span></li>
        <li index="6">
            <img class="vam" src="../../static/images/doughnut.png" onclick="editEcharts(2, ${questionJson})" style="width: 1.3em; height: 1.3em;" alt="折线">
            <span class="vam" onclick="editEcharts(2, ${questionJson})">圆环</span></li>
        <li index="2">
            <img class="vam" src="../../static/images/bar.png" onclick="editEcharts(3, ${questionJson})" style="width: 1.3em; height: 1.3em;" alt="折线">
            <span class="vam" onclick="editEcharts(3, ${questionJson})">柱状</span>
        </li>
        <li index="4">
            <img class="vam" src="../../static/images/bar2.png" onclick="editEcharts(4, ${questionJson})" style="width: 1.3em; height: 1.3em;" alt="折线">
            <span class="vam" onclick="editEcharts(4, ${questionJson})">条形</span>
        </li>
        <li index="3">
            <img class="vam" src="../../static/images/line.png" onclick="editEcharts(5, ${questionJson})" style="width: 1.3em; height: 1.3em;" alt="折线">
            <span class="vam" onclick="editEcharts(5, ${questionJson})">折线</span>
        </li>
      </ul>
    </div>
    <div style="clear:both;"></div>
  </div>
  `)
    if (question.type === 4) {
        $(`#table${question.qId}`).html(``)
        $(`#table${question.qId}`).append(`
        <thead>
            <tr><td>题目\选项</td></tr>
        </thead>
        <tbody></tbody>
        `)
        for (let i = 0; i < question.columns.length; i++) {
            $(`#table${question.qId} > thead > tr`).append(`
                <td>${question.columns[i]}</td>
            `)
        }
        for (let i = 0; i < question.content.length; i++) {
            $(`#table${question.qId} > tbody`).append(`
                <tr class="${i}"><td>${question.content[i]}</td></tr>
            `)
        }
        for (let i = 0; i < question.content.length; i++) {
            for (let j = 0; j < question.columns.length; j++) {
                $(`#table${question.qId} > tbody .${i}`).append(`
                    <td>${countAnswers[question.qId - 1].colChoice[i][j]}</td>
                `)
            }
        }
    } else {
        for (let i = 0; i < question.content.length; i++) {
            let percent = countAnswers[question.qId - 1].conChoice[i] * 100.0 / countAnswers[question.qId - 1].total
            $(`#table${question.qId} > tbody`).append(`
          <tr>
            <td val="1">${question.content[i]}</td>
            <td align="center">${countAnswers[question.qId - 1].conChoice[i]}</td>
            <td percent="100">
              <div class="bar">
                <div style="width: ${percent}%; display: block;" class="precent barcont"></div>
              </div>
              <div style="margin-top:3px;float:left;">
                ${percent}%
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
            <td align="center"><b>${countAnswers[question.qId - 1].total}</b></td>
            <td></td>
          </tr>
  `)
    $(`.title-item${question.qId}`).append(`
    <div id="editEcharts${question.qId}" style="width: 400px;height: 300px"></div>
  `)

}

const htmlspecialchars = (str) =>
{  //将json转成的字符串里面的双引号变单引号
    str = str.replace(/&/g, '&amp;');
    str = str.replace(/</g, '&lt;');
    str = str.replace(/>/g, '&gt;');
    str = str.replace(/"/g, '&quot;');
    str = str.replace(/'/g, '&#039;');
    return str;
}


editEcharts = (type, question) => {
    $(`.editEcharts${question.qId}`).html(``)
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
    console.log(document.getElementById('editEcharts' + question.qId))
// 绘制echarts图表
    let chart= echarts.getInstanceByDom(document.getElementById('editEcharts' + question.qId)); //有的话就获取已有echarts实例的DOM节点。
    if (chart != null) { // 如果不存在，就进行初始化。
        chart.dispose();
        chart = echarts.init(document.getElementById('editEcharts' + question.qId));
    } else {
        chart = echarts.init(document.getElementById('editEcharts' + question.qId));
    }

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
    let chart= echarts.getInstanceByDom(document.getElementById('editEcharts' + question.qId)); //有的话就获取已有echarts实例的DOM节点。
    if (chart != null) { // 如果不存在，就进行初始化。
        chart.dispose();
        chart = echarts.init(document.getElementById('editEcharts' + question.qId));
    } else {
        chart = echarts.init(document.getElementById('editEcharts' + question.qId));
    }

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
    let datas1 = []
    let series = []
    if (countAnswers[question.qId - 1].colChoice.length === 0) {
        series.push({type: 'bar', seriesLayoutBy: 'row'})
        let data1 = ['content']
        for (let j = 0; j < question.content.length; j++) {
            data1.push(question.content[j])
        }
        datas1.push(data1)
        let data2 = ['choice']
        for (let j = 0; j < countAnswers[question.qId - 1].conChoice.length; j++) {
            data2.push(countAnswers[question.qId - 1].conChoice[j])
            series.push({type: 'bar', xAxisIndex: 1, yAxisIndex: 1})
        }
        datas1.push(data2)
    } else {
        let data1 = ['content']
        for (let j = 0; j < question.columns.length; j++) {
            data1.push(question.columns[j])
        }
        datas1.push(data1)
        for (let i = 0; i < question.content.length; i++) {
            let data = [question.content[i]]
            for (let j = 0; j < question.columns.length; j++) {
                data.push(countAnswers[question.qId - 1].colChoice[i][j])
            }
            datas1.push(data)
            series.push({type: 'bar', seriesLayoutBy: 'row'})
        }
        for (let j = 0; j < question.content.length; j++) {
            series.push({type: 'bar', xAxisIndex: 1, yAxisIndex: 1})
        }
    }
    // 绘制echarts图表
    let chart= echarts.getInstanceByDom(document.getElementById('editEcharts' + question.qId)); //有的话就获取已有echarts实例的DOM节点。
    if (chart != null) { // 如果不存在，就进行初始化。
        chart.dispose();
        chart = echarts.init(document.getElementById('editEcharts' + question.qId));
    } else {
        chart = echarts.init(document.getElementById('editEcharts' + question.qId));
    }
    let option = {
        legend: {},
        tooltip: {},
        dataset: {
            source: datas1
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
    for (let i = 0; i < question.columns.length; i++) {
        yAxis.data.push(question.columns[i])
    }
    if (countAnswers[question.qId - 1].colChoice.length === 0) {
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
                serie.data.push(countAnswers[question.qId - 1].colChoice[i][j])
            }
            series.push(serie)
        }
    }
    // 绘制echarts图表
    let chart= echarts.getInstanceByDom(document.getElementById('editEcharts' + question.qId)); //有的话就获取已有echarts实例的DOM节点。
    if (chart != null) { // 如果不存在，就进行初始化。
        chart.dispose();
        chart = echarts.init(document.getElementById('editEcharts' + question.qId));
    } else {
        chart = echarts.init(document.getElementById('editEcharts' + question.qId));
    }
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
    let seriess = []

    for (let i = 0; i < question.columns.length; i++) {
        xData.push(question.columns[i])
    }
    if (countAnswers[question.qId - 1].colChoice.length === 0) {
        lData.push('choice')
        let serie = {
            name: 'choice',
            type: 'line',
            stack: 'Total',
            data: []
        }
        for (let i = 0; i < question.content.length; i++) {
            serie.data.push(countAnswers[question.qId - 1].conChoice[i])
        }
        seriess.push(serie)
    } else {
        let total = []
        for (let i = 0; i < question.columns.length; i++) {
            total.push(0)
        }
        for (let i = 0; i < question.content.length; i++) {
            lData.push(question.content[i])
            let serie = {
                name: 'choice',
                type: 'line',
                stack: 'Total',
                data: []
            }
            for (let j = 0; j < question.columns.length; j++) {
                serie.name = question.content[i]
                serie.data.push(countAnswers[question.qId - 1].colChoice[i][j])
                total[j] += countAnswers[question.qId - 1].colChoice[i][j]
            }
            seriess.push(serie)
            console.log(total)
            console.log(serie)
        }
    }
    // 绘制echarts图表
    let chart= echarts.getInstanceByDom(document.getElementById('editEcharts' + question.qId)); //有的话就获取已有echarts实例的DOM节点。
    if (chart != null) { // 如果不存在，就进行初始化。
        chart.dispose();
        chart = echarts.init(document.getElementById('editEcharts' + question.qId));
    } else {
        chart = echarts.init(document.getElementById('editEcharts' + question.qId));
    }
    let option = {
        title: {
            text: question.title
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
        series: seriess
    };
    chart.setOption(option)
}

countSame = (question) => {
    for (let i = 1; i <= questionLength; i++) {

    document.querySelector('.title-item' + i).remove()
    }
    const path = new URLSearchParams(window.location.search)
    let params = {
        qnId: parseInt(path.get('qnId')),
        qId: question.qId
    }
    console.log(params)
    $.ajax({
        url: API_BASE_URL + '/answer' + '/getAnswerInSameQuestion',
        type: 'POST',
        data: {
            qnId: parseInt(path.get('qnId')),
            qId: question.qId
        }, // 直接传递整数值
        success(res) {
            console.log(res.data[0])
            console.log("------------")
            console.log(res.data[1])
            initAnswer(res.data[0])
            allAnswer(res.data[1])
            initAnswerForm(res.data[0])
        }
    })
}