const clockTitle = document.querySelector("h2");

const ChristmasEve = new Date(2022,11,25);

const calculateDday = () => {
    const dDaytoSecond = Math.floor((ChristmasEve - new Date()) / 1000);

    clockTitle.innerText = `${Math.floor(dDaytoSecond / (3600*24))}d ${Math.floor(dDaytoSecond % (3600*24) / 3600)}h ${Math.floor(dDaytoSecond % 3600 / 60)}m ${Math.floor(dDaytoSecond % 60)}s`;
}

calculateDday();
setInterval(calculateDday, 1000);
