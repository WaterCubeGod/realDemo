onload = () => {
    const queryString = window.location.href;
    console.log(queryString)
    const url = new URL(queryString);
    const link = url.searchParams.get('link');
    const preview = url.searchParams.get('preview')
    const id = $util.getPageParam('id')


    if (link) {
        fetchQuestionList(link);
    } else if (!(id === undefined) && preview !== '1'){
        $.ajax({
            url: API_BASE_URL + '/questionnaire/selectById?id=' + id,
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success(res) {
                $(".questionnaire-title").text(res.data.name)
                $(".questionnaire-description").text(res.data.description)
            }
        })
        previewQuestionList(id)
    }

}

window.addEventListener('DOMContentLoaded', function () {
    const queryString = window.location.href;
    const url = new URL(queryString);
    const preview = url.searchParams.get('preview');
    if (preview !== '1')
        return;
    let containerHTML = localStorage.getItem('containerHTML');
    // 执行渲染操作，例如将数据插入到HTML元素中
    let targetElement = document.querySelector('body');
    targetElement.innerHTML = containerHTML
    targetElement.querySelector('.top').remove()
    $('.container').append(`
        <div class="btn-div">
            <button type="button" class="btn btn-primary" onclick="handleSubmit()">提 交</button>
        </div>
        `)
});

const fetchQuestionList = (link) => {
    $.ajax({
        url: API_BASE_URL + '/question/seeQuestion/' + link,
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        success(res) {
            const questions = res.data[0];
            allEditFinish(questions);
        }
    })
}
let questionnaire;
const previewQuestionList = (id) => {
    let params = {
        qnId: id
    }
    $.ajax({
        url: API_BASE_URL + '/question/seeQuestion',
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(params),
        contentType: 'application/json',
        success(res) {
            console.log(res);
            const questions = res.data[0];
            questionnaire = eval("(" + res.data[1]  + ")");
            $('#questionnaire-title').text(questionnaire.name)
            $('#questionnaire-description').text(questionnaire.description)
            allEditFinish(questions);
        }
    })
}

const allEditFinish = (questions) => {
    for (let i = 0; i < questions.length; i++) {
        let question = eval("(" + questions[i] + ")");
        switch (question.type) {
            case 1:
                singleChoiceEditFinish(question)
                break
            case 2:
                multipleChoiceEditFinish(question)
                break
            case 3:
                fillBlanksEditFinish(question)
                break
            case 4:
                matrixEditFinish(question)
                break
            case 5:
                gaugeEditFinish(question)
                break
            default:
                console.log(question.type)
        }
    }
}

const singleChoiceEditFinish = (question) => {
    $('#problem').append(`
    <div class="question" id="question${question.qId - 1}" data-type="1" data-problemIndex="question${question.qId}">
      <div class="top">
        <span class="question-title" id="questionTitle">${question.qId}.${question.title}</span>
        <span class="must-answer" id="mustAnswer">${question.req === 1 ? '必答题' : '非必答题'}</span>
      </div>
      <div class="bottom"></div>
    </div>
  `)
    for (let i = 0; i < question.content.length; i++) {
        $(`#question${question.qId - 1} .bottom`).append(`
        <div style="display: flex; align-items: center; margin-bottom: 3px;">
           <label class="radio-inline">
             <input type="radio" name="chooseTerm${question.qId - 1}">${question.content[i]}
           </label>
        </div>
    `)
    }
}

const multipleChoiceEditFinish = (question) => {
    $('#problem').append(`
              <div class="question" id="question${question.qId - 1}" data-type="2" data-problemIndex="${question.qId}">
                <div class="top">
                  <span class="question-title" id="questionTitle">${question.qId}.${question.title}</span>
                  <span class="must-answer" id="mustAnswer">${question.req === 1 ? '必答题' : '非必答题'}</span>
                </div>
                <div class="bottom"></div>
              </div>
    `)
    for (let i = 0; i < question.content.length; i++) {
        $(`#question${question.qId - 1} .bottom`).append(`
        <div style="display: flex; align-items: center; margin-bottom: 3px;">
                    <label class="checkbox-inline">
                      <input type="checkbox" name="chooseTerm">${question.content[i]}
                    </label>
        </div>
    `)
    }
}

const fillBlanksEditFinish = (question) => {
    $('#problem').append(`
              <div class="question" id="question${question.qId - 1}" data-type="3" data-problemIndex="${question.qId}">
                <div class="top">
                  <span class="question-title" id="questionTitle">${question.qId}.${question.title}</span>
                  <span class="must-answer" id="mustAnswer">${question.req === 1 ? '必答题' : '非必答题'}</span>
                </div>
                <div class="bottom">
                  <textarea class="form-control" placeholder="请输入" rows="4" style="width: 70%;"></textarea>
              </div>
            `)
}

const matrixEditFinish = (question) => {
    $('#problem').append(`
              <div class="question" id="question${question.qId - 1}" data-type="4" data-problemIndex="${question.qId}">
                <div class="top">
                  <span class="question-title" id="questionTitle">${question.qId}.${question.title}</span>
                  <span class="must-answer" id="mustAnswer">${question.req === 1 ? '必答题' : '非必答题'}</span>
                </div>
                <div class="bottom">
                 <table class="table">
                    <thead>
                      <tr>
                        <th></th>
                      </tr>
                    </thead>
                    <tbody>
                    </tbody>
                  </table>
                 </div>
              </div>
            `)
    for (let i = 0; i < question.columns.length; i++) {
        $(`#question${question.qId - 1} .table > thead > tr`).append(`
            <th>${question.columns[i]}</th>
        `)
    }
    for (let i = 0; i < question.content.length; i++) {
        $(`#question${question.qId - 1} .table > tbody`).append(`
            <tr id="tr${i}">
                <td>${question.content[i]}</td>
            </tr>
        `)
    }
    for (let i = 0; i < question.content.length; i++) {
        for (let j = 0; j < question.columns.length; j++) {
            $(`#question${question.qId - 1} .table > tbody  #tr${i}`).append(`
                <td><input type="radio" name="chooseTerm${question.qId-1}${i}" /></td>
        `)
        }
    }
}

const gaugeEditFinish = (question) => {
    $('#problem').append(`
              <div class="question" id="question${question.qId - 1}" data-type="5" data-problemIndex="${question.qId}">
                <div class="top">
                  <span class="question-title" id="questionTitle">${question.qId}.${question.title}</span>
                  <span class="must-answer" id="mustAnswer">${question.req === 1 ? '必答题' : '非必答题'}</span>
                </div>
                <div class="bottom">
                 <table class="table">
                    <thead>
                      <tr>
                        <th></th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <th></th>
                      </tr>
                    </tbody>
                  </table>
                 </div>
              </div>
            `)
    for (let i = 0; i < question.content.length; i++) {
        $(`#question${question.qId - 1} .table > thead > tr`).append(`
            <th>${question.content[i]}</th>
        `)
    }
    for (let i = 0; i < question.columns.length; i++) {
        $(`#question${question.qId - 1} .table > tbody > tr`).append(`
            <th><input type="radio" name="fraction" />${question.score[i]}</th>
        `)
    }
}

const handleSubmit = () =>{
    const questions = document.querySelectorAll('.question');
    let paramsArray = []
    for (let i = 0; i < questions.length; i++) {
        let params = {
            id:0,
            qnId:questionnaire.id,
            qnName:questionnaire.name,
            qId: i,
            userId: 1,
            userName:'TestName',
            createTime: "t",
            content: "",
            choice:[],
            columns:[],
            score: 0,
            type:parseInt(questions[i].getAttribute('data-type'))
        }
        switch (questions[i].getAttribute('data-type')) {
            case '1':
            case '2':
                const choiceBox = questions[i].querySelectorAll('input')
                for (let j = 0; j < choiceBox.length; j++) {
                    if (choiceBox[j].checked){
                        params.choice.push(j)
                    }
                }
                break;
            case '3':
                params.content = questions[i].querySelector('.form-control').value
                break;
            case '4':
                const row = questions[i].querySelector('tbody').querySelectorAll('tr')
                for (let j = 0; j < row.length; j++) {
                    const column = row[j].querySelectorAll('input')
                    for (let k = 0; k < column.length; k++) {
                        if (column[k].checked){
                            params.choice.push(j)
                            params.columns.push(k)
                        }
                    }
                }
                break;
            case '5':
                const th = questions[i].querySelector('tbody').querySelectorAll('th')
                for (let j = 1; j < th.length; j++) {
                    const choice = th[j].querySelector('input')
                    if (choice.checked) {
                        params.choice.push(j - 1)
                        params.score = th[j].innerText
                    }
                }
                break;
        }
        paramsArray.push(params)
    }
    $.ajax({
        url: API_BASE_URL + '/answer/add',
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(paramsArray),
        contentType: 'application/json',
        success(res) {
            alert("提交成功")
        }
    })
}