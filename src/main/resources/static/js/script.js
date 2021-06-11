

const zipCode = document.getElementById("zipCode");

zipCode.addEventListener('change', () => {
    sendData();
});

function sendData() {

    const form = document.getElementById("myForm");

    const XHR = new XMLHttpRequest();

    // Bind the FormData object and the form element
    const FD = new FormData(form);

    // Define what happens on successful data submission
    XHR.addEventListener( "load", function(event) {
        const zipCodeErrorTh = document.getElementById("zipCodeErrorTh");
        if (zipCodeErrorTh != null) {
            zipCodeErrorTh.innerHTML = "";
        }
        const citiesError = document.getElementById("citiesError");
        if (citiesError != null) {
            citiesError.innerHTML = "";
        }
        let str = event.target.responseText;
        const zipCodeError = document.getElementById("zipCodeError");
        if (str.includes("error")) {
            zipCodeError.innerHTML = "Code postal non valide";
            const select = document.getElementById("cities");
            select.innerHTML = "";
            const option = document.createElement("option");
            option.value = "";
            option.innerHTML = "Rentre un code postal pour avoir une liste de ville";
            select.appendChild(option);
        } else {
            zipCodeError.innerHTML = "";
            str = str.replace("[", "");
            str = str.replace("]", "");
            str = str.replace(/ /g, "");
            str = str.replace(/"/g, "");
            const cities = str.split(',');
            const select = document.getElementById("cities");
            select.innerHTML = "";
            for (let i = 0; i < cities.length; i++) {
                const option = document.createElement("option");
                option.value = cities[i];
                option.innerHTML = cities[i];
                select.appendChild(option);
            }
        }
    } );

    // Set up our request
    XHR.open("POST", "http://localhost:8081/patatapp/ws/cities");

    // The data sent is what the user provided in the form
    XHR.send(FD);
}

document.addEventListener('keypress', preventSubmit);
function preventSubmit(e) {
    if(e.keyCode === 13) {
        e.preventDefault();
    }
}