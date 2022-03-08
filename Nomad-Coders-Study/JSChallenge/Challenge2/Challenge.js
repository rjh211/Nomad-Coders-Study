const text = document.querySelector(".changeWindow h1");
const WIDTH = window.innerWidth;

text.innerText = "Hello!";
document.body.style.backgroundColor = "blue";
text.style.color = "white";

const SetBackgroundColor = color => document.body.style.backgroundColor = color;

window.addEventListener("resize",()=>{
  if(window.innerWidth >  WIDTH * 1.1) {
    SetBackgroundColor("red");
  } else if(window.innerWidth < WIDTH * 0.9) {
    SetBackgroundColor("green");
  } else {
    SetBackgroundColor("blue");
  }
});