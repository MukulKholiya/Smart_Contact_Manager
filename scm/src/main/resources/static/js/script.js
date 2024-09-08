// console.log("Hello");

let curr_theme = getTheme();
changeTheme(curr_theme)

function changeTheme(curr_theme){
    document.querySelector('html').classList.add(curr_theme);
    const btn = document.querySelector('#theme_change_button');
    btn.addEventListener("click",()=>{
        document.querySelector("html").classList.remove(curr_theme);
        if(curr_theme==="dark"){
            curr_theme = "light";
        }
        else{
            curr_theme = "dark";
        }
        setTheme(curr_theme);
        document.querySelector('html').classList.add(curr_theme);
    });   
}

// set theme to local storage
function setTheme(theme){
    localStorage.setItem("theme",theme);
}

// get theme from local storage
function getTheme(){
    let th = localStorage.getItem("theme");
    if(th==null) return "light";
    return th;
}