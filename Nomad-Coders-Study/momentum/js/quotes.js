const quotes=[
    {
        qoute: "고통이 남기고 간 뒤를 보라! 고난이 지나면 반드시 기쁨이 스며든다.",
        author: "-괴테"
    },
    {
        qoute: "돈이란 바닷물과도 같다. 그것은 마시면 마실수록 목이 말라진다.",
        author: "-쇼펜하우어"
    },
    {
        qoute: "그대의 하루 하루를 그대의 마지막 날이라고 생각하라.",
        author: "-호라티우스"
    },
    {
        qoute: "최고에 도달하려면 최저에서 시작하라.",
        author: "-P.시루스"
    },
    {
        qoute: "겨울이 오면 봄이 멀지 않으리",
        author: "-셸리"
    },
    {
        qoute: "가난은 가난하다고 느끼는 곳에 존재한다 .",
        author: "- 에머슨"
    },
    {
        qoute: "우리는 두려움의 홍수에 버티기 위해서 끊임없이 용기의 둑을 쌓아야 한다.",
        author: "-마틴 루터 킹"
    },
    {
        qoute: "실패는 잊어라 그러나 그것이 준 교훈은 절대 잊으면 안된다.",
        author: "-하버트 개서"
    },
    {
        qoute: "삶을 사는 데는 단 두가지 방법이 있다. 하나는 기적이 전혀 없다고 여기는 것이고 또 다른 하나는 모든 것이 기적이라고 여기는방식이다.",
        author: "-알베르트 아인슈타인"
    },
    {
        qoute: "먼저핀꽃은 먼저진다  남보다 먼저 공을 세우려고 조급히 서둘것이 아니다.",
        author: "-채근담"
    }
]

let quote = document.querySelector("#quote span:first-child")
let author = document.querySelector("#quote span:last-child")

let todayQuote = (quotes[Math.floor(Math.random()*quotes.length)])

console.log(todayQuote);
console.log(todayQuote.qoute);

author.innerText = todayQuote.author;
quote.innerText = todayQuote.qoute;
