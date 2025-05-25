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
            if (data.msg === "Usuario confirmado correctamente"){
                Swal.fire({
                    title: "Cuenta Confirmada Correctamente",
                    text: "Haz confirmado correctamente tu cuenta, puedes empezar a publicar tus vacantes",
                    icon: "success",
                    confirmButtonText: "Aceptar"
                }).then((result) => {
                    if (result.isConfirmed){
                        window.location.href = "/"
                    }
                });
            }
        }).catch((e) => {
            console.log(e.message)
        })
    }
});