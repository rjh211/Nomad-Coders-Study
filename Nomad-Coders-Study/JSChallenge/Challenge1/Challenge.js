const t = document.querySelector(".dynamicText h1");
t.innerText = "Hello!";

t.addEventListener("mouseenter",()=>{
    t.innerText = "The Mouse is here!";
    t.style.color = "tomato";
});
t.addEventListener("mouseleave",()=>{
    t.innerText = "The Mouse is gone!"
    t.style.color = "green";
});
window.addEventListener("resize",()=>{
    t.innerText = "You just resized!"
    t.style.color = "purple";
});
window.addEventListener("contextmenu",()=>{
    t.innerText = "That was a right click!"
    t.style.color = "orange";
});
