function startTemplate() {
    // result.innerHTML = "" +
    //     "    <nav class=\"nav-container\">\n" +
    //     "        <ul class=\"nav-list\">\n" +
    //     "            <li><a th:href=\"@{/movie}\" class=\"nav-link\">Movies</a></li>\n" +
    //     "            <li><a th:href=\"@{/session}\" class=\"nav-link\">Session</a></li>\n" +
    //     "            <li sec:authorize=\"isFullyAuthenticated()\"><a th:href=\"@{/profile}\" class=\"nav-link\">Profile</a></li>\n" +
    //     "            <li sec:authorize=\"!isFullyAuthenticated()\" ><a th:href=\"@{/auth/signin}\" class=\"nav-link\">SignIn</a></li>\n" +
    //     "            <li sec:authorize=\"!isFullyAuthenticated()\" ><a th:href=\"@{/auth/signup}\" class=\"nav-link\">SignUp</a></li>\n" +
    //     "        </ul>\n" +
    //     "    </nav>\n";
}

function searchFunction() {
    let input = document.getElementById("search-box");
    let filter = input.value.toUpperCase();
    let arr = document.getElementsByClassName("movie-container");
    for(let i = 0; i < arr.length; i++) {
        let p = arr[i].getElementsByClassName("movie-title")[0];
        let txtValue = p.textContent || p.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            arr[i].style.display = "flex";
        } else {
            arr[i].style.display = "none";
        }
    }
}


document.getElementById("search-box").addEventListener('input', (e)=> {searchFunction()})

document.getElementById("data-search").addEventListener('input', (e)=>{console.log(e.currentTarget.value)})