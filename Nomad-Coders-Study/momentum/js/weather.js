const API_KEY = "6f2fa8f26d42423cf1fa375baa3dcab2";

const getWeatherName = (weather)=>{
    if(weather === "Thunderstorm" || weather === "Additional"){
        return "Extream";
    } else if(weather === "Dizzle"){
        return "Rain";
    }
    return weather;
}

const onGeoOk = (position)=>{
    const lat =  position.coords.latitude;
    const lon = position.coords.longitude;
    const url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${API_KEY}&units=metric`
    fetch(url).then(response => response.json()).then(data=>{
        const weather = document.querySelector("#weather span:first-child")
        const city = document.querySelector("#weather span:last-child")

        document.body.style.background = `url(./images/${data.weather[0].main}/img${Math.random() < 0.5 ? 1 : 2}.jpg) no-repeat`;
        document.body.style.backgroundSize = `${window.innerWidth}px ${window.innerHeight}px`;
        document.body.style.backgroundPosition = 'auto'
        
        city.innerText = data.name;
        weather.innerText = data.weather[0].main;
    });
}
const onGeoError = ()=>{
    alert("Can't Find you. Now weather for you.")
}

navigator.geolocation.getCurrentPosition(onGeoOk, onGeoError);