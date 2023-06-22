onload = () => {
    const queryString = window.location.href;
    console.log(queryString)
    const url = new URL(queryString);
    const link =  url.searchParams.get('link');

    if (link) {
        fetchQuestionList(link);
    }

}

window.addEventListener('DOMContentLoaded', function () {
    let containerHTML = localStorage.getItem('containerHTML');
    // 执行渲染操作，例如将数据插入到HTML元素中
    let targetElement = document.querySelector('body');
    targetElement.innerHTML = containerHTML
    targetElement.querySelector('.top').remove()
    $('.container').append(`
    <div class="btn-div">
      <button type="button" class="btn btn-primary">提 交</button>
    </div>`)
});

const fetchQuestionList = (link) => {
    $.ajax({
        url: API_BASE_URL + '/question/seeQuestion/'+ link,
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        success(res) {
            console.log(res)

        }
    })
}

const handleAddSingleChoice = (question) => {
    let ele = `
    <div class="question" id="question${question.qId - 1}" data-type="1" data-problemIndex="${question.qId - 1}">
      <div class="top">
        <span class="question-title" id="questionTitle">question.name</span>
        <span class="must-answer" id="mustAnswer">${question.req === 1 ? '必答题' : '非比答题'}</span>
      </div>
      <div class="bottom">
        <textarea class="form-control textarea" id="problemName" rows="4">${question.title}</textarea>
        <div class="option" id="option">
          <div class="option-item" id="optionItem0">
            <input type="text" class="form-control" id="chooseTerm">
          </div>
        </div>
        <div>
          <button type="button" class="btn btn-link btn-add-option" onclick="singleChoiceAddOption(${problem.length})">添加选项</button>
        </div>
        <div class="btn-group">
          <button type="button" id="cancelEdit" class="btn btn-default" onclick="cancelEdit(${problem.length})">取消编辑</button>
          <button type="button" id="editFinish" class="btn btn-default" onclick="singleChoiceEditFinish(${problem.length})">完成编辑</button>
        </div>
      </div>
      <div class="bottom2" style="display: none;">
        
      </div>
    </div>
  `
    return ele
}

const singleChoiceEditFinish = (question) => {

    // 获取要创建 div 元素的父容器
    let parentContainer = document.getElementById('option');

    // 定义循环的次数
    let count = question.content.length;

    // 使用 for 循环创建带有类名和 ID 的 div 元素
    for (let i = 0; i < count; i++) {
        // 创建 div 元素
        let div = document.createElement('div');

        // 设置 div 元素的类名和 ID
        div.className = 'option-item';
        div.id = 'optionItem' + i;

        // 创建输入框元素
        let input = document.createElement('input');
        input.type = 'text';
        input.className = 'form-control';
        input.id = 'chooseTerm' + i;

        // 将输入框元素添加到 div 元素中
        div.appendChild(input);

        // 将 div 元素添加到父容器中
        parentContainer.appendChild(div);
    }
}

const flash = () => {
    const element = document.querySelectorAll('.question-title');
    for (let t = 0; t < element.length; t++) {
        let innerText = element[t].innerText
        const splitResult = innerText.split('.',2);
        innerText =`${t+1}.` + splitResult[1];
        element[t].innerText = innerText;
    }
}