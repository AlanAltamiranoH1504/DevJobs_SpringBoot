document.addEventListener("DOMContentLoaded", () => {
    const pathParts = window.location.pathname.split("/");
    const id = pathParts[3];
    const token = document.querySelector("#csrf").value;

    fetch(`/vacante/findById/${id}`, {
        method: "POST",
        headers: {
            "X-CSRF-TOKEN": token
        }
    }).then((response) => {
        return response.json();
    }).then((data) => {
        llenadoFormulario(data);
    }).catch((e) =>{
        console.log("Error en busqueda de vacante con id: " + id);
    });

    //Selectores
    const formEdicionVacante = document.querySelector("#formEdicionVacante");

    formEdicionVacante.addEventListener("submit", updateVacantePeticion);
    //Funciones
    function llenadoFormulario(info){
        document.querySelector("#nombrePagina").textContent = `Edicion de Vacante: ${info.titulo}`
        document.querySelector("#titulo").value = info.titulo;
        document.querySelector("#empresa").value = info.empresa;
        document.querySelector("#ubicacion").value = info.ubicacion;
        document.querySelector("#salario").value = info.salario;
        document.querySelector("#contrato").value = info.contrato;
        document.querySelector("#x").value = info.descripcion;
    }

    function updateVacantePeticion(e){
        e.preventDefault();
        const inputTitulo = document.querySelector("#titulo").value;
        const inputEmpresa = document.querySelector("#empresa").value;
        const inputUbicacion = document.querySelector("#ubicacion").value;
        const inputSalario = document.querySelector("#salario").value;
        const inputContrato = document.querySelector("#contrato").value;
        const inputDescripcion = document.querySelector("#x").value;
        let skills = [];

        document.querySelectorAll("input[name='skills']:checked").forEach((check) => {
            const skill = {
                id: check.getAttribute("data-id"),
                nombre: check.value
            }
            skills.push(skill);
        });

        const requestBody = {
            id,
            titulo: inputTitulo,
            empresa: inputEmpresa,
            ubicacion: inputUbicacion,
            salario: inputSalario,
            contrato: inputContrato,
            descripcion: inputDescripcion,
            skills: skills
        }
        fetch("/vacante/update",{
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(requestBody)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            if (data.msg){
                Swal.fire({
                    title: "¡Actualizacion Correcta!",
                    text: "La vacante ha sido actualziada correctamente",
                    icon: "success",
                    timer:3000
                });
                setTimeout(() => {
                    window.location.href = "/"
                }, 3200)
            }else{
                const erroresArray = Object.values(data);
                const divAlertas = document.querySelector("#alertas");
                divAlertas.innerHTML = "";

                Swal.fire({
                    title: "¡Error!",
                    icon: "error",
                    text: "Error en actualizacion de vacante. Consulta los errores.",
                    timer: 7000,
                    showConfirmButton: true
                });
                erroresArray.forEach((error) => {
                    const parrafoError = document.createElement("p");
                    parrafoError.classList.add("alertas_errores")
                    parrafoError.textContent = error;
                    divAlertas.appendChild(parrafoError);
                });
                setTimeout(() => {
                    divAlertas.innerHTML = "";
                }, 5000)
            }
        }).catch((error) => {
            console.log("Error en actualizacion de vacante: " + id);
            console.log(error.message);
        })
    }
});