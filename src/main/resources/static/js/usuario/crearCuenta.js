document.addEventListener("DOMContentLoaded", () => {
    const formCrearCuenta = document.querySelector("#formCrearCuenta");

    //Eventos
    formCrearCuenta.addEventListener("submit", registroUsuario);

    //Funciones
    function registroUsuario(e) {
        e.preventDefault();

        const nombre = document.querySelector("#nombre").value;
        const email = document.querySelector("#email").value;
        const password = document.querySelector("#password").value;
        const password2 = document.querySelector("#password2").value;
        const selectTipo = document.querySelector("#tipo").value;
        const token = document.querySelector("#csrf").value;

        if (password !== password2){
            Swal.fire({
                title: "Error",
                text: "Las passwords no coincide",
                icon: "warning",
                timer: 3000
            });
            return;
        }

        const requestBody = {
            nombre,
            email,
            password,
            cv: "Sin cv"
        }
        peticion(requestBody, selectTipo, token);
    }

    function peticion(requestBody, tipo, token){
        fetch("/candidatos/save", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "X-CSRF-TOKEN": token
            },
            body: JSON.stringify(requestBody)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            if (data.msg){
                Swal.fire({
                    title: "¡Usuario Registrado!",
                    text: "Puedes comenzar a publicar o postularte a vacantes",
                    icon: "success",
                    timer: 3000
                });
            }else{
                Swal.fire({
                    title: "¡Error en Registro!",
                    text: "Revisas los errores en el registro",
                    icon: "error",
                    timer: 3000
                });
                alertas(data);
            }
        }).catch((error) => {
            console.log("Error en creacion de usuario");
            console.log(error.message)
        })
    }

    function alertas(alertas){
        const alertasArray = Object.values(alertas);
        const divAlertas =  document.querySelector("#alertas");
        divAlertas.innerHTML = "";

        alertasArray.forEach((alerta) => {
            const parrafoAlerta = document.createElement("p");
            parrafoAlerta.classList.add("alertas_errores");
            parrafoAlerta.textContent = alerta;
            divAlertas.appendChild(parrafoAlerta);
        });
        setTimeout(() => {
            divAlertas.innerHTML = "";
        }, 6000);
    }
});