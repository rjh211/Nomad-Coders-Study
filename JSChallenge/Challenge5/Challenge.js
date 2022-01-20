const colors = [
    "#ef5777",
    "#575fcf",
    "#4bcffa",
    "#34e7e4",
    "#0be881",
    "#f53b57",
    "#3c40c6",
    "#0fbcf9",
    "#00d8d6",
    "#05c46b",
    "#ffc048",
    "#ffdd59",
    "#ff5e57",
    "#d2dae2",
    "#485460",
    "#ffa801",
    "#ffd32a",
    "#ff3f34"
  ];
  
  const backGroundButton = document.querySelector('body button');
  
  const GenerateRandomValueArray = (limit) => {
    const firstValue = Math.floor(Math.random() * limit);
    let secondValue = Math.floor(Math.random() * limit);
    while(firstValue === secondValue){
        secondValue = Math.floor(Math.random() * limit);
    }
    return [firstValue, secondValue];
  }

  backGroundButton.addEventListener("click",()=>{
      [firstValue, secondValue] = GenerateRandomValueArray(colors.length);
      document.body.style.background = `linear-gradient(${colors[firstValue]}, ${colors[secondValue]})`;
      console.log(firstValue, secondValue)
  })