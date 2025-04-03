const loginButton = document.getElementById('loginButton');
const registerButton = document.getElementById('registerButton');
const loginForm = document.getElementById('loginForm');
const registerForm = document.getElementById('registerForm');
const loginUsername = document.getElementById('loginUserName')
// const login = document.getElementById('login');
const regname = document.getElementById('username_err2')
// const loginPassword = document.getElementById('loginPassWord')
//
//
loginButton.addEventListener('click', () => {
    loginForm.classList.add('active');
    registerForm.classList.remove('active');
    loginButton.classList.add('active');
    registerButton.classList.remove('active');
});

registerButton.addEventListener('click', () => {
    registerForm.classList.add('active');
    loginForm.classList.remove('active');
    loginButton.classList.remove('active');
    registerButton.classList.add('active');
});

registerForm.addEventListener('submit', function (e) {
    const password = this.querySelector('input[name="newPassWord"]').value;
    const confirmPassword = this.querySelector('input[name="againPassWord"]').value;

    if (password!== confirmPassword) {
        alert('两次输入的密码不一致，请重新输入！');
        e.preventDefault();
    }
});


loginUsername.addEventListener('blur', function (){
        //哈哈哈
      let username = this.value;
      let xhttp = new XMLHttpRequest();
      xhttp.open('GET','http://localhost:8080/text?name='+username);

      xhttp.onreadystatechange = function() {
          if(xhttp.readyState == 4 && xhttp.status == 200){

              if(this.responseText == "true"){
                  document.getElementById("username_err").style.display='none'
              }else{
                  document.getElementById("username_err").style.display='block'
              }


          }
      }
    xhttp.send();
  })



function userlogin() {
    var form = document.getElementById('loginForm');
    form.submit();
}

