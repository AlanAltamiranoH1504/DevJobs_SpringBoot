document.addEventListener("DOMContentLoaded", () => {
    llenadoFormulario();


    function llenadoFormulario() {
        const token = document.querySelector("#csrf").value;

        fetch("/candidatos/findById", {
            method: "POST",
            headers: {
                "X-CSRF-TOKEN": token
            }
        }).then((response) => {
            return response.json();
        }).then((data) => {
            document.querySelector("#nombre").value = data.nombre;
            document.querySelector("#email").value = data.email;
            document.querySelector("#descripcion").value = data.descripcion !== null ? data.descripcion : "";
        }).catch((e) => {
            console.log("Error en busqueda de usaurio")
        })
    }

    //Selectores
    const formEditarPerfil = document.querySelector("#formEditarPerfil");

    //Eventos
    formEditarPerfil.addEventListener("submit", actualizarPerfilPeticion);

    //Funciones
    function actualizarPerfilPeticion(e) {
        e.preventDefault();
        const inputNombre = document.querySelector("#nombre").value;
        const inputEmail = document.querySelector("#email").value;
        const inputDescripcion = document.querySelector("#descripcion").value;
        const password = document.querySelector("#password").value;
        const password2 = document.querySelector("#password2").value;
        const token = document.querySelector("#csrf").value;

        if (password2 !== password) {
            Swal.fire({
                title: "Error",
                text: "Las contraseñas no coinciden para la actualización",
                icon: "error",
                timer: 3000
            });
            return;
        }

        const requestBody = {
            nombre: inputNombre,
            email: inputEmail,
            descripcion: inputDescripcion,
            password,
            cv: "Sin cv"
        }
        fetch("/candidatos/update", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "X-CSRF-TOKEN": token
            },
            body: JSON.stringify(requestBody)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            if (data.msg === "Candidato actualizado correctamente") {
                Swal.fire({
                    title: "Perfil Actualizado Correctamente",
                    text: "Tu perfil ha sido actualizado correctamente",
                    icon: "success",
                    timer: 3000
                });
                setTimeout(() => {
                    window.location.href = "/home"
                }, 3000)
            } else {
                mostrarAlertas(data);
            }
        }).catch((e) => {
            console.log("Error en actualizacion de usaurio");
            console.log(e.message);
        })
    }

    function mostrarAlertas(alertas) {
        const arrayAlertas = Object.values(alertas);
        const divAlertas = document.querySelector("#divAlertas");
        arrayAlertas.forEach((error) => {
            const parrafoError = document.createElement("p");
            parrafoError.textContent = error;
            parrafoError.classList.add("alertas_errores")
            divAlertas.appendChild(parrafoError);
        });
        setTimeout(() => { divAlertas.innerHTML = ""}, 5000);
    }
});