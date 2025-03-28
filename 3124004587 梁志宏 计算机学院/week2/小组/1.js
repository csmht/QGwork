const loginButton = document.getElementById('loginButton');
const registerButton = document.getElementById('registerButton');
const loginForm = document.getElementById('loginForm');
const registerForm = document.getElementById('registerForm');


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