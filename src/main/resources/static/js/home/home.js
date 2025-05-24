document.addEventListener("DOMContentLoaded", () => {
    //Llamado de funciones
    peticionFindAllVacantes();

    //Selectores
    const listaVacantes = document.querySelector("#listaVacantes");

    //Eventos
    listaVacantes.addEventListener("click", (e) => {
        if (e.target.id === "vacanteInfo"){
            const btn = e.target;
            const id = btn.getAttribute("data_id");
            redireccionMostrarVacante(id);
        }
    });

    //Funciones
    function peticionFindAllVacantes(){
        fetch("/home/findAllVacantes", {
            method: "GET"
        }).then((response) => {
            return response.json();
        }).then((data) =>{
            const imagenPerfil = document.querySelector("#imgPerfil");
            imagenPerfil.setAttribute("src", `/uploads/${data.usuario.imgPerfil}`);
            imagenPerfil.classList.add("img_perfil_home");
            const mensajeBienvenida = document.querySelector("#mensajeBienvenida");
            mensajeBienvenida.textContent = `Bienvenido ${data.usuario.nombre}`

            if (data.vacantes.length >= 1){
                renderVacantes(data.vacantes);
            }else {
                mostrarAlertas("error", "divAlertas");
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
                    <button id="vacanteInfo" data_id="${vacante.id}" class="btn btn-verde">Más Info</button>
                </div>
            `;
            listaVacantes.appendChild(divVacante);
        })
    }

    function mostrarAlertas(tipo, lugar){
        const divAlertas = document.querySelector("#divAlertas");
        if (tipo === "error"){
            divAlertas.style.background = "red";
            divAlertas.style.color = "white";
            divAlertas.style.textAlign = "center";
            divAlertas.style.textTransform = "uppercase"
            divAlertas.style.paddingTop = "10px";
            divAlertas.style.paddingBottom = "10px";

            divAlertas.textContent = "No tienes vacante registradas";
        }
    }

    function redireccionMostrarVacante(id){
        window.location.href = `/vacantes/busqueda-vacante/${id}`;
    }
})