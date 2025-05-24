document.addEventListener("DOMContentLoaded", () => {
    if (document.querySelector("#formEditarFoto")) {
        busquedaInformacionUusario();

        const formEditarFoto = document.querySelector("#formEditarFoto");

        //Eventos
        formEditarFoto.addEventListener("submit", creacionRequest);

        //Funciones
        function busquedaInformacionUusario() {
            fetch("/candidatos/findById", {
                method: "POST",
                headers: {
                    "X-CSRF-TOKEN": document.querySelector("#csrf").value
                }
            }).then((response) => {
                return response.json();
            }).then((data) => {
                if (data.imgPerfil) {
                    const {imgPerfil} = data;
                    const imgPerfilHTML = document.querySelector("#img_perfil");
                    const direccionImagenes = "/uploads/" + imgPerfil;
                    imgPerfilHTML.setAttribute("src", direccionImagenes);
                }
            }).catch((e) => {
                console.log("Error en busqueda de usuario");
                console.log(e.message)
            });
        }

        function creacionRequest(e) {
            e.preventDefault();

            const token = document.querySelector("#csrf").value;
            const inputArchivo = document.querySelector("#imagen").files[0];
            const formData = new FormData();
            formData.append("imagen", inputArchivo);

            fetch("/candidatos/update/img-perfil", {
                method: "PUT",
                headers: {
                    "X-CSRF-TOKEN": token
                },
                body: formData
            }).then((response) => {
                return response.json();
            }).then((data) => {
                if (data.msg === "Foto de perfil actualizada"){
                    Swal.fire({
                        title: "Foto de Perfil Actualizada",
                        text: "Tu foto de perfil se actualizo correctamente",
                        icon: "success",
                        confirmButtonText: "Ok"
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.href = "/home";
                        }
                    })
                } else {
                    Swal.fire({
                        title: "Error en actualizacion de imagen de perfil",
                        text: data.error,
                        icon: "error",
                        timer: 4000
                    });
                }
            }).catch((e) => {
                console.log("Error en actuailizacion de img de perfil");
                console.log(e.message);
            })
        }
    }

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
            // console.log("Error en busqueda de usaurio")
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
        setTimeout(() => {
            divAlertas.innerHTML = ""
        }, 5000);
    }
});