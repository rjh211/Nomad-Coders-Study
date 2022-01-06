const test = document.querySelector("div.hello:first-child h1")
console.dir(test)

test.innerText = "Hello"
test.style.color = "red"

function MouseClick(){
    console.log("title was clicked!")
}

test.onclick = ()=>console.log("title was clicked!");
test.addEventListener("mouseenter", ()=>test.innerText = "Mouse is Enter!")
test.addEventListener("mouseleave", ()=>test.innerText = "Mouse is gone!")
console.dir(test);

window.addEventListener("resize", ()=>{
    document.body.style.backgroundColor = "tomato";
});
window.addEventListener("copy", ()=>{
    alert("copier!");
});
window.addEventListener("offline", ()=>{
    alert("SOS no WIFI")
});
window.addEventListener("online", ()=>{
    alert("ALL GOOD");
});
