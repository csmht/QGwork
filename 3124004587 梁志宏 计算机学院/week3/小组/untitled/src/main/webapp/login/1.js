const loginButton = document.getElementById('loginButton');
const registerButton = document.getElementById('registerButton');
const loginForm = document.getElementById('loginForm');
const registerForm = document.getElementById('registerForm');
const loginUsername = document.getElementById('loginUserName')
// const login = document.getElementById('login');
const regname = document.getElementById('username_err2')
// const loginPassword = document.getElementById('loginPassWord')
const newUser = document.getElementById('newUser');
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

registerForm.addEventListener('submit', async e => {
    const password = registerForm.querySelector('input[name="newPassWord"]').value;
    const confirmPassword = registerForm.querySelector('input[name="againPassWord"]').value;

    if (password!== confirmPassword) {
        alert('两次输入的密码不一致，请重新输入！');
        e.preventDefault();
    }else{
        e.preventDefault();
        const formData = new FormData(registerForm);

        //刻晴到此一游
        // formData.append("newUserName",document.getElementById("newUserName").value);
        //
        // formData.append("newPassWord",document.getElementById("newPassWord").value);
        //
        // formData.append("newmun",document.getElementById("newmun").value);


        // for (const [key, value] of formData.entries()) {
        //     console.log(`${key}: ${value}`);
        // }

        // const resp = await fetch('/newUser',{
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'application/json',
        //     },
        //     body: JSON.stringify({
        //         newUserName: document.querySelector('[name="newUserName"]').value,
        //         newPassWord: password,
        //     }),
        // });
        // console.log(await resp.json());
        // console.log(resp.statusText);
        // console.log(resp.body);
        // globalThis.resp = resp;
        const dataString = new URLSearchParams(formData).toString();
        const xhr = new XMLHttpRequest();
        xhr.open('POST', '/newUser', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send(dataString);
        xhr.onreadystatechange = function () {

            if (xhr.readyState === 4 && xhr.status === 200) {
                globalThis.xhr=xhr;

                if(xhr.responseText == 'true'){
                    alert("注册成功");
                    window.location.href = 'http://localhost:8080/login';
                }else if(xhr.responseText == 'flasttow'){
                    document.getElementById("username_err2").style.display='block';
                }else{alert("注册失败");}
            }
        };

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
    let form = document.getElementById('loginForm');
    form.submit();
}

