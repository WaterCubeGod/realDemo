onload = () => {
    const queryString = window.location.href;
    console.log(queryString)
    const url = new URL(queryString);
    const link = url.searchParams.get('link');
    const id = $util.getPageParam('id')

    if (link) {
        fetchQuestionList(link);
    } else if (!(id === undefined)){
        previewQuestionList(id)
    }

}

window.addEventListener('DOMContentLoaded', function () {
    // let containerHTML = localStorage.getItem('containerHTML');
    // // 执行渲染操作，例如将数据插入到HTML元素中
    // let targetElement = document.querySelector('body');
    // targetElement.innerHTML = containerHTML
    // targetElement.querySelector('.top').remove()
    // $('.container').append(`
    //     <div class="btn-div">
    //         <button type="button" class="btn btn-primary">提 交</button>
    //     </div>
    //     `)
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
            let questionnaire = eval("(" + res.data[1]  + ")");
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
             <input type="radio" name="chooseTerm">${question.content[i]}
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
                <td><input type="radio" name="chooseTerm${i}" /></td>
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