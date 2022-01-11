const toDoForm = document.getElementById("todo-form");
const toDoInput = toDoForm.querySelector("input"); // = document.querySelector("#todo-form input")
const toDoList = document.getElementById("todo-list");

const TODOS_KEY = "todos"

let toDos = [];

const saveToDos = ()=>{
    localStorage.setItem(TODOS_KEY, JSON.stringify(toDos));   //JSON to String
}

const handleToDoSubmit = (event) => {
    event.preventDefault();
    const newTodo = toDoInput.value;
    toDoInput.value = "";
    const newTodoObj = {
        text:newTodo,
        id : Date.now(),
    }
    toDos.push(newTodoObj);
    paintToDo(newTodoObj);
    saveToDos();
};

const deleteToDo = (event)=>{
    const li = event.target.parentElement;
    li.remove();
    toDos = toDos.filter((item)=>{
        return item.id !== parseInt(li.id);
    });
    saveToDos();
};

const paintToDo = (newTodo)=>{
    const li = document.createElement("li");
    const span = document.createElement("span");
    const button = document.createElement("button");

    li.id = newTodo.id;

    li.appendChild(span);
    li.appendChild(button);
    span.innerText = newTodo.text;
    button.innerText = "delete";
    button.addEventListener("click", deleteToDo);
    toDoList.appendChild(li);
};

toDoForm.addEventListener("submit",  handleToDoSubmit);

const savedToDos = localStorage.getItem(TODOS_KEY);

if(savedToDos !== null){
    const parsedToDos = JSON.parse(savedToDos);
    toDos = parsedToDos;
    parsedToDos.forEach(element=>{
        paintToDo(element);
    })
} else {

}
