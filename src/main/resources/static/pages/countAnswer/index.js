onload = () => {
  fetchUserList()
}

const  countAnswers = []

const fetchUserList = () => {
  let params = {
    username: $('#username').val()
  }
  $.ajax({
    url: API_BASE_URL + '/users',
    type: 'POST',
    dataType: 'json',
    data: JSON.stringify(params),
    contentType: 'application/json',
    success(res) {
      initAnswer(res.data[0])
      allAnswer(res.data[1])
      editEcharts()
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
          answer.conChoice.push(j)
        }
        for (let j = 0; j < question.columns.length; j++) {
          answer.columns.push(question.columns[j])
          answer.conChoice.push(0)
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
}

allAnswer = (answers) => {
  for (let i = 0; i < answers.length; i++) {
    let answer = eval("(" + answers[i] + ")");
    switch (answer.type) {
      case 1:
      case 2:
        for(let j = 0; j < answer.choice.length; j++){
          countAnswers[i].conChoice[answer.choice[j]] ++;
        }
        break
      case 3:
        break
      case 4:
        for(let j = 0; j < answer.choice.length; j++){
          countAnswers[i].colChoice[answer.columns] ++;
        }
        break
      case 5:
        for(let j = 0; j < answer.choice.length; j++){
          countAnswers[i].conChoice[answer.choice] ++;
          countAnswers[i].score += answer.score
        }
        break
      default:
        console.log(answer.type)
    }
  }
}

initAnswerForm = () => {

}

editEcharts = () => {

}


