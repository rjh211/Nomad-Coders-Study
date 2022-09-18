const quotes=[
    {
        qoute: "qoute1",
        author: "a"
    },
    {
        qoute: "qoute2",
        author: "b"
    },
    {
        qoute: "qoute3",
        author: "c"
    },
    {
        qoute: "qoute4",
        author: "d"
    },
    {
        qoute: "qoute5",
        author: "e"
    },
    {
        qoute: "qoute6",
        author: "f"
    },
    {
        qoute: "qoute7",
        author: "g"
    },
    {
        qoute: "qoute8",
        author: "h"
    },
    {
        qoute: "qoute9",
        author: "i"
    },
    {
        qoute: "qoute10",
        author: "j"
    }
]

let quote = document.querySelector("#quote span:first-child")
let author = document.querySelector("#quote span:last-child")

let todayQuote = (quotes[Math.floor(Math.random()*quotes.length)])

console.log(todayQuote);
console.log(todayQuote.qoute);

author.innerText = todayQuote.author;
quote.innerText = todayQuote.qoute;
