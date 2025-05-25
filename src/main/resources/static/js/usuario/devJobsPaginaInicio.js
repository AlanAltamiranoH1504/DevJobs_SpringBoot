document.addEventListener("DOMContentLoaded", () => {
    peticionListadoDeVacantes();

    function peticionListadoDeVacantes(){
        fetch("/auth/findAll", {
            method: "GET"
        }).then((response) => {
            return response.json();
        }).then((data) => {
            if (data.length >= 1){
                renderVacantes(data);
            }else{
                console.log("Error de no vacantes para mostrar")
            }
        }).catch((e) => {
            console.log("Error en el listado de vacantes");
            console.log(e.message);
        });
    }
    function renderVacantes(vacantes){
        const listaVacantes = document.querySelector("#listaVacantes");
        vacantes.forEach((vacante) => {
            const divVacante = document.createElement("div");
            divVacante.classList.add("vacante");
            divVacante.innerHTML = `
                <div class="caja">
                    <h3>${vacante.titulo}</h3>
                    <p class="nombre">${vacante.empresa}</p>
                </div>
                <div class="caja">
                    <p class="etiqueta">Ubicación</p>
                    <p class="nombre">${vacante.ubicacion}</p>
                </div>
                <div class="caja">
                    <p class="etiqueta">Contratación</p>
                    <p class="nombre contrato">${vacante.contrato}</p>
                </div>
                <div class="caja centrar-vertical">
<!--                    <button type="button" id="vacanteInformacion" data-id="${vacante.id}" class="btn btn-verde">Más Info</button>-->
                    <a href="/dev-jobs/detalles/vacante/${vacante.id}" class="btn btn-verde">Más Info</a> 
                </div>
            `;
            listaVacantes.appendChild(divVacante);
        });
    }
});