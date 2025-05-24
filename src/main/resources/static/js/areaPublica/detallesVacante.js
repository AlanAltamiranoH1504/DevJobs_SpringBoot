document.addEventListener("DOMContentLoaded", () => {
    const parametherParts = window.location.pathname.split("/")
    const idVacante = parametherParts[4];
    encontrarVacante(idVacante);


    //Funciones
    function encontrarVacante(id) {
        fetch(`/vacantes/publica/findById/${id}`, {
            method: "GET"
        }).then((response) => {
            return response.json();
        }).then((data) => {
            llenadoInformacionVacante(data);
            llenadoInformacionReclutador(data.candidato);
        }).catch((error) => {
            Swal.fire({
                title: "¡ERROR!",
                text: `No existe una vacante con id: ${id}`,
                icon: "error",
                timer: 5000,
                confirmButtonText: "Ok"
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = "/dev-jobs"
                }
            });
        });
    }

    function llenadoInformacionVacante(vacante) {
        const {titulo, empresa, ubicacion, salario, contrato, descripcion, skills, id} = vacante;
        document.querySelector("#titulo").textContent = titulo;
        document.querySelector("#ubicacion").textContent = ubicacion;
        document.querySelector("#empresa").textContent = empresa;
        document.querySelector("#contrato").textContent = contrato;
        document.querySelector("#salario").textContent = salario;
        document.querySelector("#descripcion").innerHTML = descripcion
        document.querySelector("#vacanteId").setAttribute("value", id);

        const listaDeSkills = document.querySelector("#listaDeSkills");
        skills.forEach((skill) => {
            const {nombre} = skill;
            const liSkill = document.createElement("li");
            liSkill.textContent = nombre;
            listaDeSkills.appendChild(liSkill);
        })
    }

    function llenadoInformacionReclutador(reclutador) {
        const {nombre, email, descripcion, imgPerfil} = reclutador;

        document.querySelector("#nombreReclutador").textContent = nombre;
        const imagenPerfil = document.querySelector("#imgReclutador");
        imagenPerfil.setAttribute("src", `/uploads/${imgPerfil}`);
        imagenPerfil.style.width = "70%"
        document.querySelector("#descripcionReclutador").textContent = descripcion;
        document.querySelector("#email").textContent = email
    }
})