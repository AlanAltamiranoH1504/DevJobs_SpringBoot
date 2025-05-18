document.addEventListener("DOMContentLoaded", () => {
    const formIniciarSesion = document.querySelector("#formIniciarSesion");
    formIniciarSesion.addEventListener("submit", iniciarSesion);

    function iniciarSesion(e){
        e.preventDefault();

        const email = document.querySelector("#email").value;
        const password = document.querySelector("#password").value;
        const csrf_token = document.querySelector("#csrf").value;

        const requestBody = {
            email,
            password
        }

        fetch("/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "X-CSRF-TOKEN": csrf_token
            },
            body: JSON.stringify(requestBody)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            if (data.status == 200){
                window.location.href = "/home";
            } else{
                Swal.fire({
                    title: "¡Error en Inicio de Sesión!",
                    text: data.message,
                    icon: "error",
                    timer: 3000
                });
            }
        }).catch((e) => {
            console.log("Error en login");
            console.log(e.message);
        });
    }

    function alertas(alertas){
        const arrayAlertas = Object.values(alertas);
        const alertasDiv = document.querySelector("#alertas");

        arrayAlertas.forEach((alerta) => {
            const pAlerta = document.createElement("p");
            pAlerta.classList.add("alertas_errores");
            pAlerta.textContent = alerta;
            alertasDiv.appendChild(pAlerta);
        });
        setTimeout(() => {
            alertasDiv.innerHTML = ""
        }, 7000);
    }
})