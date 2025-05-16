document.addEventListener("DOMContentLoaded", () => {
    const pathParths = window.location.pathname.split("/");
    const id = pathParths[3];
    fetch(`/vacante/findById/${id}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            // "X-CSRF-TOKEN":
        }
    }).then((response) =>{
        return response.json();
    }).then((data) => {
        renderDetallesVacante(data);
    }).catch((error) => {
        Swal.fire({
            title: "¡ERROR!",
            text: `No existe una vacante con id: ${id}`,
            icon: "error",
            timer: 5000,
            textConfirmButton: "ok"
        });
        console.log(error.message);
    });
    function renderDetallesVacante(info){
        document.querySelector("#editarVacante").setAttribute("data-id", info.id);
        document.querySelector("#eliminarVacante").setAttribute("data-id", info.id);
        document.querySelector("#titulo").textContent = info.titulo;
        document.querySelector("#empresa").textContent = info.empresa;
        document.querySelector("#ubicacion").textContent = info.ubicacion;
        document.querySelector("#contrato").textContent = info.contrato;
        document.querySelector("#salario").textContent = "$ " +  info.salario;
        document.querySelector("#descripcion").innerHTML = info.descripcion;

        const skills = info.skills;
        const listaDeSkills = document.querySelector("#listaDeSkills");
        skills.forEach((skill) =>{
            const liSkill = document.createElement("li");
            liSkill.textContent = skill.nombre;
            listaDeSkills.appendChild(liSkill);
        });
    }

    //Selectores
    const seccionBtns = document.querySelector("#seccionBtns");

    //Eventos
    seccionBtns.addEventListener("click", (e) =>{
        const btnSeleccionado = e.target;
        identificadorBtn(btnSeleccionado);
    });

    //Funciones
    function identificadorBtn(btn){
        const idBtn = btn.getAttribute("id");
        const idEntidad = btn.getAttribute("data-id");

        switch (idBtn){
            case "editarVacante":
                console.log("Editando vacante");
                break;
            case "eliminarVacante":
                eliminacionVacante(idEntidad);
                break;
        }
    }

    function eliminacionVacante(id){
        Swal.fire({
            title: "¿Estas seguro que quieres eliminar la vacante?",
            text: "Esta acción no se puede deshacer",
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "Si, eliminar",
            cancelButtonText: "No, cancelar"
        }).then((result) =>{
            if (result.isConfirmed){
                fetch(`/vacante/delete/${id}`, {
                    method: "DELETE",
                    headers: {
                        "Content-Type": "application/json"
                    }
                }).then((response) => {
                    return response.json();
                }).then((data) => {
                    if (data.msg){
                        Swal.fire({
                            title: "¡Vacante Eliminada!",
                            text: "Vacante eliminada correctamente",
                            icon: "success",
                            timer: 3000
                        });
                        setTimeout(() =>{
                            window.location.href = "/";
                        }, 4000)
                    }
                }).catch((e) => {
                    console.log("Error en elimicacion de vacante con id: " + id);
                    console.log(e.message);
                })
            }else{
                console.log("Cancelando eliminacion");
            }
        });
    }
});