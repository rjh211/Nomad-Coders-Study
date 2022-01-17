const title = document.querySelector(".Casino h1");
const subTitle = document.querySelector(".Casino h2");
const result = document.querySelector(".Casino h3");

const limitValueInput = document.createElement("input");
const targetValueInput = document.createElement("input");
const play = document.createElement("button");
const resultString = document.createElement("h3");

title.innerText = "Random Number Game";
subTitle.innerText = "Gnerate a number between 0 and ";
result.innerText = "Guess the number : ";
play.innerText = "Play!";

subTitle.appendChild(limitValueInput);
result.appendChild(targetValueInput);
result.appendChild(play);
document.body.appendChild(resultString);

play.addEventListener("click",(event)=>{
  event.preventDefault();
  const limitValue = Number(limitValueInput.value);
  const targetValue = Number(targetValueInput.value);
  if(targetValue === 0){
    alert("Input Target Value");
    return;
  } else if (targetValue < 0 || targetValue > limitValue) {
    alert(`Input Value Must Between 0 and ${limitValue}`);
    return;
  }
  const randomValue = Math.floor(Math.random() * (limitValue + 1));
  resultString.innerText = `You chose: ${targetValue}, the machine chose: ${randomValue}\n
  ${targetValue === randomValue ? "You Won!" : "You lost!"}`;
})