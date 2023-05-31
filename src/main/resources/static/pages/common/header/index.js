
const pathnames = ['/pages/questionnaire/index.html']

const handleHeaderLoad = () => {
  if (pathnames.includes(location.pathname)) {
    $('#handerFallback').remove()
    $('#headerDivB').remove()
  }  
}

const onMyProject = () => {
  if (location.pathname !== '/pages/questionnaire/index.html')
  location.href = "/pages/questionnaire/index.html"
}

const onToUser = () => {
  console.log(location.pathname, 'location.pathname');
  $.ajax({
    url: API_BASE_URL + '/users',
    type: "GET",
    dataType: "json",
    contentType: "application/json",
    success(res) {
      if (res.data != null) {
        console.log(res.data)
        if (location.pathname !== '/pages/user/index.html')
          location.href = "/pages/user/index.html?totalPage=" + res.data;
      } else {
        alert(res.message)
      }
    }
  })


}

const onSignOut = () => {
  $util.clearItem()
  location.href = "/pages/login/index.html"
}
