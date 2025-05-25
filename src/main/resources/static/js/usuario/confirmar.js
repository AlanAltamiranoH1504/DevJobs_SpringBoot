document.addEventListener("DOMContentLoaded", () => {
    const pathParths = window.location.pathname.split("/");
    const token = pathParths[2];
    const requestBody = {
        token
    }

    const formCofirmarCuenta = document.querySelector("#formCofirmarCuenta");
    formCofirmarCuenta.addEventListener("submit", peticionConfirmarCuenta);

    function peticionConfirmarCuenta(e) {
        e.preventDefault();
        fetch(`/auth/confirmar-cuenta/${token}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "X-CSRF-TOKEN": document.querySelector("#csrf").value
            },
            body: JSON.stringify(requestBody)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            console.log(data)
        }).catch((e) => {
            console.log(e.message)
        })
    }
});