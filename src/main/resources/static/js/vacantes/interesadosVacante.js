document.addEventListener("DOMContentLoaded", () => {
    const pathParts = window.location.pathname.split("/");
    const idVacante = pathParts[3];

    //Peticion para busqueda de candidatos de la vacante
    fetch(`/vacante/getInteresados/${idVacante}`, {
        method: "GET",
    }).then((response) => {
        return response.json();
    }).then((data) => {
        if (data.length >= 0){
            renderCandidatos(data);
        } else {
            Swal.fire({
                title: "¡ERROR!",
                text: "Acceso no autorizado para esta vacante",
                icon: "warning",
                timer: 2000
            });
            setTimeout(() => {
                window.location.href = "/home";
            }, 1500)
        }
    }).catch((e) => {
        console.log("ERROR EN BUSQUEDA DE INTERESADOS DE VACANTE");
        console.log(e.message);
    });

    //Funciones
    function renderCandidatos(candidatos){
        const listaCandidatos = document.querySelector("#listaCandidatos");

        if (candidatos.length <= 0){
            const divAdbertencia = document.createElement("div");
            divAdbertencia.classList.add("alertas_errores");
            divAdbertencia.textContent = "No hay candidatos para esta vacante";

            listaCandidatos.appendChild(divAdbertencia);
        }else {
            candidatos.forEach((candidato) => {
                const divCandidato = document.createElement("div");
                divCandidato.classList.add("vacante", "panel-administracion");
                divCandidato.innerHTML = `
                    <div class="caja">
                        <p class="etiqueta">Nombre</p>
                        <p class="nombre">${candidato.nombre}</p>
                    </div>
                    <div class="caja">
                        <p class="etiqueta">E-Mail</p>
                        <p class="nombre">${candidato.email}</p>
                    </div>
                    <div class="caja" ">
                        <p class="etiqueta" style="text-align: center">Opciones</p>
                        <a href="/uploads/cvs_interesados/${candidato.cv}" class="btn btn-azul" style="margin-bottom: 10px">Ver CV</a>
                        <a href="#" data-id="${candidato.email}" class="btn btn-verde">Contactar</a>
                    </div>
                `;
                listaCandidatos.appendChild(divCandidato);
            })
        }
    }
})