document.addEventListener("DOMContentLoaded", () => {
    //Selectores
    const formOlvidePassword = document.querySelector("#formOlvidePassword");
    if (document.querySelector("#formNuevaPassword")){

        const formNuevaPassword = document.querySelector("#formNuevaPassword");
        formNuevaPassword.addEventListener("submit", actualizarPassword);

        function actualizarPassword(e) {
            e.preventDefault();
            const pathParths = window.location.pathname.split("/");
            const token = pathParths[2];
            const password = document.querySelector("#password").value;

            const requestBody = {
                password,
                token
            }
            fetch("/auth/confirmar-nueva-password", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "X-CSRF-TOKEN": document.querySelector("#csrf").value
                },
                body: JSON.stringify(requestBody)
            }).then((response) => {
                return response.json();
            }).then((data) => {
                Swal.fire({
                    title: "¡Password Actualizada!",
                    text: "Password actualizada, inicia sesion y registra nuevas vacantes",
                    icon: "success",
                    confirmButtonText: "Aceptar"
                }).then((result) => {
                    if (result.isConfirmed){
                        window.location.href = "/dev-jobs"
                    }
                });
            }).catch((e) => {
                console.log(e.message);
            });
        }
    }

    //Eventos
    formOlvidePassword.addEventListener("submit", enviarEmailRecuperacionPassword);

    //Funciones
    function enviarEmailRecuperacionPassword(e){
        e.preventDefault();

        const inputEmail = document.querySelector("#email").value;
        if (inputEmail.trim() === "" ||  inputEmail == null){
            Swal.fire({
                title: "Error en recuperacion",
                text: "Debes indicar tu email para la recuperación de password",
                icon: "error",
                timer: 3000
            });
        }

        const requestBody = {
            destinatario: inputEmail
        }

        fetch("/auth/olvide-password", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "X-CSRF-TOKEN": document.querySelector("#csrf").value
            },
            body: JSON.stringify(requestBody)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            if (data.msg) {
                Swal.fire({
                    title: "E-Mail enviado correctamente",
                    text: "Verifica tu correo para la recuperacion de password",
                    icon: "success",
                    confirmButtonText: "Aceptar"
                }).then((result) => {
                    if (result.isConfirmed){
                        window.location.href = "/dev-jobs"
                    }
                });
            }
        }).catch((e) => {
            console.log(e.message);
        })
    }
})