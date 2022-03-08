const images = [
    "1.jpg",
    "2.jpg",
    "3.jpg"
]
const randomNumber = Math.floor(Math.random()*images.length)
console.log(randomNumber)
const chosenImage = images[randomNumber]

const bgImage = document.createElement("img");

bgImage.src = `images/${chosenImage}`

console.log(bgImage)

document.body.appendChild(bgImage);
