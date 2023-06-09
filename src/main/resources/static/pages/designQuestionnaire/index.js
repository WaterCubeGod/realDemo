let questionnaireTitle
let questionnaireDescription
const problem = []
onload = () => {
  questionnaireTitle = $util.getPageParam('questionnaire').name
  questionnaireDescription = $util.getPageParam('questionnaire').description
  $('.questionnaire-title > span').text(questionnaireTitle)
  $('.questionnaire-description > span').text(questionnaireDescription)
}

/**
 * 添加问题
 *
 * @param {*} type 1：单选，2：多选，3：填空，4：矩阵，5：量表
 */
const onAddQuestion = (type) => {
  let ele
  switch (type) {
    case 1:
      ele = handleAddSingleChoice()
      break;
    case 2:
      ele = handleAddMultipleChoice()
      break;
    case 3:
      ele = handleAddFillBlanks()
      break;
    case 4:
      ele = handleAddMatrix()
      break;
    case 5:
      ele = handleAddGauge()
      break;
    default:
      break;
  }
  $('#problem').append(ele)
  problem.push({ problemName: '', mustAnswer: true, option: [{}] })

  $(".question").hover(() => {
    let problemIndex = $('.question:hover').attr('data-problemIndex')
    let ele = `
      <div class="operation">
      <div class="button" onclick="handleMoveUp(${problemIndex})">上移</div>
      <div class="button" onclick="handleMoveDown(${problemIndex})">下移</div>
        <div class="button" onclick="handleEdit(${problemIndex})">编辑</div>
        <div class="button" onclick="handleDelete(${problemIndex})">删除</div>
      </div>
    `
    $('.question:hover').append(ele)
    $(".question:hover").css('border', '1px solid #fdb553')
  }, () => {
    $('.question > .operation').remove()
    $(".question").css('border', '1px solid #ffffff')
  })
}

const onInput = (problemIndex, optionIndex, key) => {
  if (optionIndex || optionIndex === 0)
    problem[problemIndex].option[optionIndex][key] = $(`#question${problemIndex} #optionItem${optionIndex} #${key}`)[0].value
  else
    problem[problemIndex][key] = $(`#question${problemIndex} #${key}`)[0].value
}

const onMustAnswerClick = (problemIndex) => {
  problem[problemIndex].mustAnswer = !problem[problemIndex].mustAnswer
  if (problem[problemIndex].mustAnswer) $(`#question${problemIndex} #mustAnswer`).text('必答题')
  else $(`#question${problemIndex} #mustAnswer`).text('非必答题')
}

const cancelEdit = (problemIndex) => {
  $(`#question${problemIndex} .bottom`).css('display', 'none')
  $(`#question${problemIndex} .bottom2`).css('display', 'block')
}

const handleMoveUp = (problemIndex) => {
  if (problemIndex === 0) return
  $(`#question${problemIndex - 1}`).before($(`#question${problemIndex}`))

  let i = problem[problemIndex]
  problem[problemIndex] = problem[problemIndex - 1]
  problem[problemIndex - 1] = i
  moveCommon()
  flash()
}

const handleMoveDown = (problemIndex) => {
  if (problemIndex === problem.length - 1) return
  $(`#question${problemIndex + 1}`).after($(`#question${problemIndex}`))
  let i = problem[problemIndex]
  problem[problemIndex] = problem[problemIndex + 1]
  problem[problemIndex + 1] = i
  moveCommon()
  flash()
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

const moveCommon = () => {
  $('.question').map((index, item) => {
    item.setAttribute('id', `question${index}`)
    item.setAttribute('data-problemIndex', index)
    let type = +$(`#question${index}`).attr('data-type')
    let value;
    value = $(`#question${index} #problemName`).attr('oninput').replace(/\(\d+,/g, `(${index},`)
    $(`#question${index} #problemName`).attr('oninput', value)
    $(`#question${index} #mustAnswer`).attr('onclick', `onMustAnswerClick(${index})`)
    $(`#question${index} #cancelEdit`).attr('onclick', `cancelEdit(${index})`)
    switch (type) {
      case 1:
        $(`#question${index} #chooseTerm`).map(((chooseTermIndex, chooseTermItem) => {
          chooseTermItem.oninput = onInput.bind(this, index, chooseTermIndex, 'chooseTerm')
        }))
        $(`#question${index} .option-del`).map(((delIndex, delItem) => {
          delItem.oninput = onInput.bind(this, index, delIndex, 'chooseTerm')
        }))
        $(`#question${index} .btn-add-option`).attr('onclick', `singleChoiceAddOption(${index})`)
        $(`#question${index} #editFinish`).attr('onclick', `singleChoiceEditFinish(${index})`)
        break;
      case 2:
        $(`#question${index} #chooseTerm`).map(((chooseTermIndex, chooseTermItem) => {
          chooseTermItem.oninput = onInput.bind(this, index, chooseTermIndex, 'chooseTerm')
        }))
        $(`#question${index} .option-del`).map(((delIndex, delItem) => {
          delItem.oninput = onInput.bind(this, index, delIndex, 'chooseTerm')
        }))
        $(`#question${index} .btn-add-option`).attr('onclick', `multipleChoiceAddOption(${index})`)
        $(`#question${index} #editFinish`).attr('onclick', `multipleChoiceEditFinish(${index})`)
        break;
      case 3:
        $(`#question${index} #editFinish`).attr('onclick', `fillBlanksEditFinish(${index})`)
        break;
      case 4:
        $(`#question${index} #chooseTerm`).map(((chooseTermIndex, chooseTermItem) => {
          chooseTermItem.oninput = onInput.bind(this, index, chooseTermIndex, 'chooseTerm')
        }))
        $(`#question${index} .option-del`).map(((delIndex, delItem) => {
          delItem.oninput = onInput.bind(this, index, delIndex, 'chooseTerm')
        }))
        value = $(`#question${index} #leftTitle`).attr('oninput').replace(/\(\d+,/g, `(${index},`)
        $(`#question${index} #leftTitle`).attr('oninput', value)
        $(`#question${index} .btn-add-option`).attr('onclick', `matrixAddOption(${index})`)
        $(`#question${index} #editFinish`).attr('onclick', `matrixEditFinish(${index})`)
        break;
      case 5:
        $(`#question${index} #chooseTerm`).map(((chooseTermIndex, chooseTermItem) => {
          chooseTermItem.oninput = onInput.bind(this, index, chooseTermIndex, 'chooseTerm')
        }))
        $(`#question${index} #fraction`).map(((fractionIndex, fractionItem) => {
          fractionItem.oninput = onInput.bind(this, index, fractionIndex, 'chooseTerm')
        }))
        $(`#question${index} .option-del`).map(((delIndex, delItem) => {
          delItem.oninput = onInput.bind(this, index, delIndex, 'chooseTerm')
        }))
        $(`#question${index} .btn-add-option`).attr('onclick', `gaugeAddOption(${index})`)
        $(`#question${index} #editFinish`).attr('onclick', `gaugeEditFinish(${index})`)
        break;
      default:
        break;
    }
  })
}

const handleEdit = (problemIndex) => {
  $(`#question${problemIndex} .bottom`).css('display', 'block')
  $(`#question${problemIndex} .bottom2`).css('display', 'none')
}

const handleDelete = (problemIndex) => {
  $(`#question${problemIndex}`).remove()
  problem.splice(problemIndex, 1)
}

const handleAddSingleChoice = () => {
  let ele = `
    <div class="question" id="question${problem.length}" data-type="1" data-problemIndex="${problem.length}">
      <div class="top">
        <span class="question-title" id="questionTitle">请编辑问题？</span>
        <span class="must-answer" id="mustAnswer" onclick="onMustAnswerClick(${problem.length})">必答题</span>
      </div>
      <div class="bottom">
        <textarea class="form-control textarea" id="problemName" placeholder="单选题目" rows="4" oninput="onInput(${problem.length}, ${undefined}, 'problemName')"></textarea>
        <div class="option" id="option">
          <div class="option-item" id="optionItem0">
            <input type="text" class="form-control" id="chooseTerm" placeholder="选项【单选】" oninput="onInput(${problem.length}, 0, 'chooseTerm')" />
            <span class="option-del" onclick="singleChoiceDelOption(${problem.length}, 0)">删除</span>
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

const singleChoiceAddOption = (problemIndex) => {
  $(`#question${problemIndex} #option`).append(`
    <div class="option-item" id="optionItem${problem[problemIndex].option.length}">
      <input type="text" class="form-control" id="chooseTerm" placeholder="选项【单选】" oninput="onInput(${problemIndex}, ${problem[problemIndex].option.length}, 'chooseTerm')" />
      <span class="option-del" onclick="singleChoiceDelOption(${problemIndex}, ${problem[problemIndex].option.length})">删除</span>
    </div>
  `)
  problem[problemIndex].option.push({})
}

const singleChoiceDelOption = (problemIndex, optionIndex) => {
  $(`#question${problemIndex} .option-item`)[optionIndex].remove()
  problem[problemIndex].option.splice(optionIndex, 1)
  $(`#question${problemIndex} .option-del`).map((item, index) => {
    index.onclick = singleChoiceDelOption.bind(this, problemIndex, item)
  })
}

const singleChoiceEditFinish = (problemIndex) => {
  $(`#question${problemIndex} .bottom`).css('display', 'none')
  $(`#question${problemIndex} .bottom2`).css('display', 'inline')
  $(`#question${problemIndex} .bottom2`).html('')
  $(`#question${problemIndex} #questionTitle`).text(`${problemIndex + 1}.${problem[problemIndex].problemName}`)
  problem[problemIndex].option.map(item => {
    $(`#question${problemIndex} .bottom2`).append(`
      <div style="display: flex; align-items: center;">
        <label class="radio-inline">
          <input type="radio">${item.chooseTerm ? item.chooseTerm : ''}
        </label>
      </div>
    `)
  })
  flash()
}

const handleAddMultipleChoice = () => {
  let ele = `
    <div class="question" id="question${problem.length}" data-type="2" data-problemIndex="${problem.length}">
      <div class="top">
        <span class="question-title" id="questionTitle">请编辑问题？</span>
        <span class="must-answer" id="mustAnswer" onclick="onMustAnswerClick(${problem.length})">必答题</span>
      </div>
      <div class="bottom">
        <textarea class="form-control textarea" id="problemName" placeholder="多选题目" rows="4" oninput="onInput(${problem.length}, ${undefined}, 'problemName')"></textarea>
        <div class="option" id="option">
          <div class="option-item" id="optionItem0">
            <input type="text" class="form-control" id="chooseTerm" placeholder="选项【多选】" oninput="onInput(${problem.length}, 0, 'chooseTerm')" />
            <span class="option-del" onclick="multipleChoiceDelOption(${problem.length}, 0)">删除</span>
          </div>
        </div>
        <div>
          <button type="button" class="btn btn-link btn-add-option" onClick="multipleChoiceAddOption(${problem.length})">添加选项</button>
        </div>
        <div class="btn-group">
          <button type="button" id="cancelEdit" class="btn btn-default" onclick="cancelEdit(${problem.length})">取消编辑</button>
          <button type="button" id="editFinish" class="btn btn-default" onClick="multipleChoiceEditFinish(${problem.length})">完成编辑</button>
        </div>
      </div>
      <div class="bottom2" style="display: none;">
        
      </div>
    </div>
  `
  return ele
}

const multipleChoiceAddOption = (problemIndex) => {
  $(`#question${problemIndex} #option`).append(`
    <div class="option-item" id="optionItem${problem[problemIndex].option.length}">
      <input type="text" class="form-control" id="chooseTerm" placeholder="选项【多选】" oninput="onInput(${problemIndex}, ${problem[problemIndex].option.length}, 'chooseTerm')" />
      <span class="option-del" onclick="multipleChoiceDelOption(${problemIndex}, ${problem[problemIndex].option.length})">删除</span>
    </div>
  `)
  problem[problemIndex].option.push({})
}

const multipleChoiceDelOption = (problemIndex, optionIndex) => {
  $(`#question${problemIndex} .option-item`)[optionIndex].remove()
  problem[problemIndex].option.splice(optionIndex, 1)
  $(`#question${problemIndex} .option-del`).map((item, index) => {
    index.onclick = multipleChoiceDelOption.bind(this, problemIndex, item)
  })
}

const multipleChoiceEditFinish = (problemIndex) => {
  $(`#question${problemIndex} .bottom`).css('display', 'none')
  $(`#question${problemIndex} .bottom2`).css('display', 'inline')

  $(`#question${problemIndex} .bottom2`).html('')
  $(`#question${problemIndex} #questionTitle`).text(`${problemIndex + 1}.${problem[problemIndex].problemName}`)
  problem[problemIndex].option.map(item => {
    $(`#question${problemIndex} .bottom2`).append(`
      <div style="display: flex; align-items: center;">
        <label class="checkbox-inline">
          <input type="checkbox">${item.chooseTerm ? item.chooseTerm : ''}
        </label>
      </div>
    `)
  })
  flash()
}

const handleAddFillBlanks = () => {
  let ele = `
    <div class="question" id="question${problem.length}" data-type="3" data-problemIndex="${problem.length}">
      <div class="top">
        <span class="question-title" id="questionTitle">请编辑问题？</span>
        <span class="must-answer" id="mustAnswer" onclick="onMustAnswerClick(${problem.length})">必答题</span>
      </div>
      <div class="bottom">
        <textarea class="form-control textarea" id="problemName" placeholder="请输入题目" rows="4" oninput="onInput(${problem.length}, ${undefined}, 'problemName')"></textarea>
        <div class="btn-group">
          <button type="button" id="cancelEdit" class="btn btn-default" onclick="cancelEdit(${problem.length})">取消编辑</button>
          <button type="button" id="editFinish" class="btn btn-default" onClick="fillBlanksEditFinish(${problem.length})">完成编辑</button>
        </div>
      </div>
      <div class="bottom2" style="display: none;">
        
      </div>
    </div>
  `
  return ele
}

const fillBlanksEditFinish = (problemIndex) => {
  $(`#question${problemIndex} .bottom`).css('display', 'none')
  $(`#question${problemIndex} .bottom2`).css('display', 'inline')

  $(`#question${problemIndex} .bottom2`).html('')
  $(`#question${problemIndex} #questionTitle`).text(`${problemIndex + 1}.${problem[problemIndex].problemName}`)
  $(`#question${problemIndex} .bottom2`).html(`
    <div style="border: 1px solid #CCCCCC; width: 50%; height: 70px;"></div>
  `)
  flash()
}

const handleAddMatrix = () => {
  // language=HTML
  let ele = `
    <div class="question" id="question${problem.length}" data-type="4" data-problemIndex="${problem.length}">
      <div class="top">
        <span class="question-title" id="questionTitle">请编辑问题？</span>
        <span class="must-answer" id="mustAnswer" onclick="onMustAnswerClick(${problem.length})">必答题</span>
      </div>
      <div class="bottom">
        <textarea class="form-control textarea" id="problemName" placeholder="请编辑问题！" rows="4" oninput="onInput(${problem.length}, ${undefined}, 'problemName')"></textarea>
        <div style="margin-bottom: 10px;">左标题</div>
        <textarea class="form-control textarea" id="leftTitle" placeholder="例子：CCTV1,CCTV2,CCTV3" rows="4" oninput="onInput(${problem.length}, ${undefined}, 'leftTitle')"></textarea>
        <div class="option" id="option">
          <div class="option-item" id="optionItem0">
            <input type="text" class="form-control" id="chooseTerm" placeholder="选项" oninput="onInput(${problem.length}, 0, 'chooseTerm')" />
            <span class="option-del" onclick="matrixDelOption(${problem.length}, 0)">删除</span>
          </div>
        </div>
        <div>
          <button type="button" class="btn btn-link btn-add-option" onClick="matrixAddOption(${problem.length})">添加选项</button>
        </div>
        <div class="btn-group">
          <button type="button" id="cancelEdit" class="btn btn-default" onclick="cancelEdit(${problem.length})">取消编辑</button>
          <button type="button" id="editFinish" class="btn btn-default" onClick="matrixEditFinish(${problem.length})">完成编辑</button>
        </div>
      </div>
      <div class="bottom2" style="display: none; padding-left: 80px;"></div>
    </div>
  `
  return ele
}

const matrixAddOption = (problemIndex) => {
  $(`#question${problemIndex} #option`).append(`
    <div class="option-item" id="optionItem${problem[problemIndex].option.length}">
      <input type="text" class="form-control" id="chooseTerm" placeholder="选项" oninput="onInput(${problemIndex}, ${problem[problemIndex].option.length}, 'chooseTerm')" />
      <span class="option-del" onclick="matrixDelOption(${problemIndex}, ${problem[problemIndex].option.length})">删除</span>
    </div>
  `)
  problem[problemIndex].option.push({})
}

const matrixDelOption = (problemIndex, optionIndex) => {
  $(`#question${problemIndex} .option-item`)[optionIndex].remove()
  problem[problemIndex].option.splice(optionIndex, 1)
  $(`#question${problemIndex} .option-del`).map((item, index) => {
    index.onclick = matrixDelOption.bind(this, problemIndex, item)
  })
}

const matrixEditFinish = (problemIndex) => {
  $(`#question${problemIndex} .bottom`).css('display', 'none')
  $(`#question${problemIndex} .bottom2`).css('display', 'inline')
  $(`#question${problemIndex} #questionTitle`).text(`${problemIndex + 1}.${problem[problemIndex].problemName}`)
  $(`#question${problemIndex} .bottom2`).html('')
  let trs = problem[problemIndex].leftTitle ? problem[problemIndex].leftTitle.split(',') : []
  $(`#question${problemIndex} .bottom2`).append(`
    <table class="table">
      <thead>
        <tr>
          <th></th>
        </tr>
      </thead>
      <tbody>
        
      </tbody>
    </table>
  `)
  trs.map((item, index) => {
    $(`#question${problemIndex} .bottom2 tbody`).append(`
      <tr class="tr${index}">
        <td>${item}</td>
      </tr>
    `)
    problem[problemIndex].option.map(() => {
      $(`#question${problemIndex} .bottom2 tbody .tr${index}`).append(`
        <td>
          <input type="radio" name="radio${index}">
        </td>
      `)
    })
  })
  problem[problemIndex].option.map(item => {
    $(`#question${problemIndex} .bottom2 thead tr`).append(`
      <th>${item.chooseTerm}</th>
    `)
  })
  flash()
}

const handleAddGauge = () => {
  let ele = `
    <div class="question" id="question${problem.length}" data-type="5" data-problemIndex="${problem.length}">
      <div class="top">
        <span class="question-title" id="questionTitle">请编辑问题？</span>
        <span class="must-answer" id="mustAnswer" onclick="onMustAnswerClick(${problem.length})">必答题</span>
      </div>
      <div class="bottom">
        <textarea class="form-control textarea" id="problemName" placeholder="请编辑问题！" rows="4" oninput="onInput(${problem.length}, ${undefined}, 'problemName')"></textarea>
        <div class="option" id="option">
          <div style="display: flex; margin-bottom: 10px;">
            <div style="width: calc(50% + 90px)">选项文字</div>
            <div style="width: 140px;">分数</div>
            <div>操作</div>
          </div>
          <div class="option-item" id="optionItem0">
            <input type="text" class="form-control" id="chooseTerm" oninput="onInput(${problem.length}, 0, 'chooseTerm')" />
            <input type="text" class="form-control" id="fraction" oninput="onInput(${problem.length}, 0, 'fraction')" style="width: 50px;" />
            <span class="option-del" onclick="gaugeDelOption(${problem.length}, 0)">删除</span>
          </div>
        </div>
        <div>
          <button type="button" class="btn btn-link btn-add-option" onClick="gaugeAddOption(${problem.length})">添加选项</button>
        </div>
        <div class="btn-group">
          <button type="button" id="cancelEdit" class="btn btn-default" onclick="cancelEdit(${problem.length})">取消编辑</button>
          <button type="button" id="editFinish" class="btn btn-default" onClick="gaugeEditFinish(${problem.length})">完成编辑</button>
        </div>
      </div>
      <div class="bottom2" style="display: none; align-items: center; justify-content: space-between;"></div>
      <div class="bottom3" style="display: none; align-items: center; justify-content: space-between;"></div>
    </div>
  `
  return ele
}

const gaugeAddOption = (problemIndex) => {
  $(`#question${problemIndex} #option`).append(`
    <div class="option-item" id="optionItem${problem[problemIndex].option.length}">
      <input type="text" class="form-control" id="chooseTerm" oninput="onInput(${problemIndex}, ${problem[problemIndex].option.length}, 'chooseTerm')" />
      <input type="text" class="form-control" id="fraction" oninput="onInput(${problemIndex}, ${problem[problemIndex].option.length}, 'fraction')" style="width: 50px;" />
      <span class="option-del" onclick="gaugeDelOption(${problemIndex}, ${problem[problemIndex].option.length})">删除</span>
    </div>
  `)
  problem[problemIndex].option.push({})
}

const gaugeDelOption = (problemIndex, optionIndex) => {
  $(`#question${problemIndex} .option-item`)[optionIndex].remove()
  problem[problemIndex].option.splice(optionIndex, 1)
  $(`#question${problemIndex} .option-del`).map((item, index) => {
    index.onclick = gaugeDelOption.bind(this, problemIndex, item)
  })
}

const gaugeEditFinish = (problemIndex) => {
  $(`#question${problemIndex} .bottom`).css('display', 'none')
  $(`#question${problemIndex} .bottom2`).css('display', 'flex')
  $(`#question${problemIndex} .bottom3`).css('display', 'flex')
  $(`#question${problemIndex} #questionTitle`).text(`${problemIndex + 1}.${problem[problemIndex].problemName}`)
  // $(`#question${problemIndex} .bottom2`).html('')
  // $(`#question${problemIndex} .bottom3`).html('')

  // $(`#question${problemIndex} .bottom2`).append(`
  //   <div>${problem[problemIndex].option[0].chooseTerm}</div>
  // `)
  problem[problemIndex].option.map(item => {
    $(`#question${problemIndex} .bottom2`).append(`
      <div>
        <label class="radio-inline">
          <div>${item.chooseTerm}</div>
        </label>
      </div>
    `)
  })

  problem[problemIndex].option.map(item => {
    $(`#question${problemIndex} .bottom3`).append(`
      <div>
        <label class="radio-inline">
          <input type="radio" name="fraction" />${item.fraction}
        </label>
      </div>
    `)
  })
  // $(`#question${problemIndex} .bottom2`).append(`
  //   <div>${problem[problemIndex].option[problem[problemIndex].option.length - 1].chooseTerm}</div>
  // `)
  flash()
}

const handleModifyTitle = () => {
  $('#modifyTitleModal').modal('show')
  $('#questionnaireTitle').val(questionnaireTitle)
  $('#questionnaireDescription').val(questionnaireDescription)
}


const handleEditFinish = () => {
  const question = document.querySelectorAll('.question')
  const questionTitle = document.querySelectorAll('.question-title')
  const req = document.querySelectorAll('.must-answer')
  const  paramsArray = []
  for (let i = 0; i < question.length; i++) {

    let params = {
      qnId: $util.getPageParam("questionnaire").id,
      qId: parseInt(question[i].getAttribute('data-problemindex'))  + 1,
      title: questionTitle[i].textContent.split('.',2)[1],
      req: req[i].textContent === '必答题' ? 1:0,
      type: question[i].getAttribute('data-type'),
      content: [],
      columns: [],
      score: []
    }
    switch (params.type) {
      case '1':
        const option1 = question[i].querySelectorAll('.radio-inline');
        for (let j = 0; j < option1.length; j++) {
          params.content.push(option1[j].innerText)
        }
        // for (const t in Option) {
        //   console.log(t)
        //   params.content.push(t.innerText)
        // }

        break
      case '2':
        const option2 = question[i].querySelectorAll('.checkbox-inline');
        for (let j = 0; j < option2.length; j++) {
          params.content.push(option2[j].innerText)
        }
        break
      case '3':
        break
      case '4':
        const option3 = question[i].querySelectorAll('.table');
        //获取表头
        const theadRow = option3[0].getElementsByTagName("thead")[0].getElementsByTagName("tr")[0];
        //填入表头的每个值
        const thCells = theadRow.getElementsByTagName("th");
        for (let j = 1; j < thCells.length; j++) {
          params.columns.push(thCells[j].innerHTML);
        }
        // 获取表体值
        const tbody = option3[0].getElementsByTagName("tbody")[0];
        const tbodyRows = tbody.getElementsByTagName("tr");
        for (let j = 0; j < tbodyRows.length; j++) {
          let tdCells = tbodyRows[j].getElementsByTagName("td");
          let firstCell = tdCells[0].innerHTML; // 获取第一个单元格的值
          params.content.push(firstCell);
        }
        break
      case '5':
        const option5 = question[i].querySelectorAll('.radio-inline');
        for (let j = 0; j < option5.length/2; j++) {
          params.content.push(option5[j].innerText)
        }
        for (let j = option5.length/2; j < option5.length; j++) {
          params.score.push(option5[j].innerText)
        }
        break
    }
    paramsArray.push(params)
    console.log(params)
  }

  $.ajax({
    url: API_BASE_URL + '/question/addQuestion',
    type: "PUT",
    data: JSON.stringify(paramsArray),
    dataType: "json",
    contentType: "application/json",
    success(res) {
      console.log(res)
      alert('编辑成功!')
      location.href = '/pages/questionnaire/index.html'
    },
    error(res) {
      alert('编辑失败！')
    }
  })
}

const handleView = () => {
  let containerHTML = document.querySelector('.container').outerHTML
  localStorage.setItem('containerHTML', containerHTML) // 将数据存储到localStorage中
  location.href = '/pages/answerSheet/index.html?preview=1'

}


