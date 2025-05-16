document.addEventListener("DOMContentLoaded", ()=>{
    //Selectores
    const formNuevaVacante = document.querySelector("#formNuevaVacante");

    //Eventos
    formNuevaVacante.addEventListener("submit", requestSaveNuevaVacante);

    //Funciones
    function requestSaveNuevaVacante(e){
        e.preventDefault();

        let skillSeleccionadas = "";
        let skillSeleccionados2 = [];
        document.querySelectorAll("input[name='skills']:checked").forEach((check, index, array) => {
            skillSeleccionadas += check.value + (index < array.length -1 ? "," : "")
            const skill = {
                id: check.getAttribute("data-id"),
                nombre: check.value
            }
            skillSeleccionados2.push(skill);
        });
        const inputTitulo = document.querySelector("#titulo").value;
        const inputEmpresa = document.querySelector("#empresa").value;
        const inputUbicacion = document.querySelector("#ubicacion").value;
        const inputSalario = document.querySelector("#salario").value;
        const inputContrato = document.querySelector("#contrato").value;
        const inputDescripcion = document.querySelector("#x").value;

        const requestBody = {
            titulo: inputTitulo,
            empresa: inputEmpresa,
            ubicacion: inputUbicacion,
            salario: inputSalario,
            contrato: inputContrato,
            descripcion: inputDescripcion,
            skills: skillSeleccionados2
        }

        fetch("/vacante/save-vacante", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(requestBody)
        }).then((response) => {
            return response.json();
        }).then((data) => {
            if (data.msg){
                Swal.fire({
                    title: "¡Exito!",
                    icon: "success",
                    text: "Vacante creada de manera correcta",
                    timer: 3000,
                    showConfirmButton: false,
                });
            } else{
                const erroresArray = Object.values(data);
                const divAlertas = document.querySelector("#alertas");
                divAlertas.innerHTML = "";

                Swal.fire({
                    title: "¡Error!",
                    icon: "error",
                    text: "Error en creacion de vacante. Consulta errores en parte superior",
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
                }, 7000)
            }
        }).catch((error) => {
            console.log("Error en peticion de guardado");
            console.log(error.message);
        })
    }
})