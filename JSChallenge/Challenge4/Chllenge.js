const body = document.querySelector(".dDay");
const h1 = document.createElement("h1");
const h2 = document.createElement("h2");

h1.innerText = "Time Until Christmas Eve";
body.appendChild(h1);

const ChristmasEve = new Date(2022,11,25);

const calculateDday = () => {
    const dDaytoSecond = Math.floor((ChristmasEve - new Date()) / 1000);

    h2.innerText = `${Math.floor(dDaytoSecond / (3600*24))}d ${Math.floor(dDaytoSecond % (3600*24) / 3600)}h ${Math.floor(dDaytoSecond % 3600 / 60)}m ${Math.floor(dDaytoSecond % 60)}s`;
    body.appendChild(h2);
}

calculateDday();
setInterval(calculateDday, 1000);