const loginForm = document.querySelector("#login-form") //# : id일때 사용하는 접두사
const loginInput = loginForm.querySelector("#login-form input")

loginForm.addEventListener("submit",(evnet)=>{
    evnet.preventDefault(); //Event의 기본 행위 차단(여기서는 submit -> 새로고침)
    const userName = loginInput.value;
    console.log(userName);
});
