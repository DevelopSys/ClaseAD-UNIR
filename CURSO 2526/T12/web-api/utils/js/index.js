const inputNombre = document.querySelector("#inputNombre");
const inputCorreo = document.querySelector("#inputCorreo");
const inputPass = document.querySelector("#inputPass");
const btnAgregar = document.querySelector("#btnAgregar");
const divResultado = document.querySelector("#divResultado");

btnAgregar.addEventListener("click", (e) => {
  console.log("Elemento pulsado correctamente");
  let usuario = {
    nombre: inputNombre.value,
    correo: inputCorreo.value,
    pass: inputPass.value,
  };

  fetch("http://127.0.0.1:8080/api/usuarios/create", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    // string
    body: JSON.stringify({
      nombre: inputNombre.value,
      correo: inputCorreo.value,
      pass: inputPass.value,
    }),
  })
    .then((res) => {
      Swal.fire({
        title: "OK",
        text: "Usuario creado correctemente",
        icon: "success",
      });
      return res.json();
    })
    .then((res1) => {
      divResultado.innerHTML = `<div class="alert alert-secondary" role="alert">
        Usuario con id ${res1.data.id} creado correctamente
      </div>;`;
      setTimeout(() => {
        divResultado.innerHTML = "";
      }, 5000);
    })
    .catch((rej) => {});
});

// localhost:8080/api/usuarios/create -> body / Post

/* 

----------------------------------------------- proceso(conjunto de hilos)
-----------x------------x-----------x----------- main (render+listener)
           ------------- ------------> Promise
                            Fetch(url, datos (body, method))

la contestacion puede ser
    resolve -> la contestacion ha sido correcta
    reject -> la contestacion ha sido incorrecta
*/
