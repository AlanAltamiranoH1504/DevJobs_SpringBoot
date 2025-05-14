document.addEventListener("DOMContentLoaded", () => {
    peticionFindAllVacantes();

    function peticionFindAllVacantes(){
        fetch("/home/findAllVacantes", {
            method: "GET"
        }).then((response) => {
            return response.json();
        }).then((data) =>{
            if (data.vacantes.length >= 1){
                // console.log("Hay vacantes")
                renderVacantes(data.vacantes);
            }else {
                console.log("No hay vacantes");
            }
        }).catch((error) => {
            console.log("Error en peticion de listado de vacantes");
            console.log(error.message);
        });
    }

    function renderVacantes(vacantes){
        const listaVacantes = document.querySelector("#listaVacantes");
        vacantes.forEach((vacante) => {
            const divVacante = document.createElement("div");
            divVacante.classList.add("vacante");
            divVacante.innerHTML = `
                <div class="caja">
                    <h3>${vacante.empresa}</h3>
                    <p class="puesto">${vacante.titulo}</p>
                </div>
                <div class="caja">
                    <p class="etiqueta">Ubicación</p>
                    <p class="nombre">${vacante.ubicacion}</p>
                </div>
                <div class="caja">
                    <p class="etiqueta">Contrato</p>
                    <p class="nombre contrato">${vacante.contrato}</p>     
                </div>
                <div class="caja centrar-vertical">
                    <button id="vacanteInfo" data-id="${vacante.id}" class="btn btn-verde">Más Info</button>
                </div>
            `;
            listaVacantes.appendChild(divVacante);
        })
    }
})