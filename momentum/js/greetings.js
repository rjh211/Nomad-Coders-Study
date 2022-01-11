const loginForm = document.querySelector("#login-form"); //# : id일때 사용하는 접두사
const loginInput = loginForm.querySelector("#login-form input");
const greeting = document.querySelector("#greeting")

const HIDDEN_CLASSNAME = "hidden"
const USERNAME_KEY = "username"

const saveUserName = localStorage.getItem(USERNAME_KEY)     //LocalStorage(LOCAL DB)의 KEY VALUE 값을 가져옴
console.log(saveUserName)

function paintGreeting(saveUserName) {
    greeting.innerText = `Hello ${saveUserName}`;
    greeting.classList.remove(HIDDEN_CLASSNAME);
}


if (saveUserName === null){
    loginForm.classList.remove(HIDDEN_CLASSNAME);
    loginForm.addEventListener("submit",(event)=>{
        event.preventDefault(); //Event의 기본 행위 차단(여기서는 submit -> 새로고침)
        loginForm.classList.add(HIDDEN_CLASSNAME);
        const userName = loginInput.value;
        localStorage.setItem(USERNAME_KEY, userName);
        greeting.classList.remove(HIDDEN_CLASSNAME);
        paintGreeting(userName);
    });
} else {
    loginForm.classList.add(HIDDEN_CLASSNAME)
    paintGreeting(saveUserName)
}