const clock = document.querySelector("h2#clock");
clock.style.left = String(window.innerWidth/2)+'px'


const getClock = () => {
    const date = new Date();
    clock.innerText = `${date.getHours()} : ${date.getMinutes()} : ${date.getSeconds()}`
}

getClock();
setInterval(getClock, 1000);