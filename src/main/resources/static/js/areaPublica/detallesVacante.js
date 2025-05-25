document.addEventListener("DOMContentLoaded", () => {
    const parametherParts = window.location.pathname.split("/")
    const idVacante = parametherParts[4];
    encontrarVacante(idVacante);

    //Selectores
    const formEnvioCV = document.querySelector("#formEnvioCV");

    //Eventos
    formEnvioCV.addEventListener("submit", peticionEnvioCV);

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

        document.querySelector("#nombreReclutador").textContent = `Nombre: ${nombre}`;
        const imagenPerfil = document.querySelector("#imgReclutador");
        imagenPerfil.setAttribute("src", `/uploads/${imgPerfil}`);
        imagenPerfil.style.width = "70%"
        document.querySelector("#descripcionReclutador").textContent = `Descripción: ${descripcion !== null ? descripcion : "Sin descripción"}`;
        document.querySelector("#email").textContent = `E-Mail de Contacto: ${email}`
    }

    function peticionEnvioCV(e){
        e.preventDefault();

        const nombreInteresado = document.querySelector("#nombreInteresado").value;
        const emailInteresado = document.querySelector("#emailInteresado").value;
        const vacanteId = document.querySelector("#vacanteId").value;
        const inputCV = document.querySelector("#cv").files[0];
        const token = document.querySelector("#csrf").value;

        const formData = new FormData();
        formData.append("nombreInteresado", nombreInteresado);
        formData.append("emailInteresado", emailInteresado);
        formData.append("vacanteId", vacanteId);
        formData.append("cv", inputCV);

        fetch("/vacantes/publica/save/interesado", {
            method: "POST",
            headers: {
                "X-CSRF-TOKEN": token
            },
            body: formData
        }).then((response) => {
            return response.json();
        }).then((data) => {
            if (data.msg) {
                Swal.fire({
                    title: data.msg,
                    text: "Espera la respuesta del reclutado para continuar el proceso",
                    icon: "success",
                    textConfirmButton: "Aceptar"
                }).then((result) => {
                    if (result.isConfirmed){
                        window.location.href = "/dev-jobs";
                    }
                });
            }
        }).catch((e) => {
            Swal.fire({
                title: "¡Error!",
                text: "Error en postulacion a la vacante",
                icon: "error",
                textConfirmButton: "Aceptar"
            }).then((result) => {
                if (result.isConfirmed){
                    window.location.href = "/dev-jobs";
                }
            });
        })
    }
})