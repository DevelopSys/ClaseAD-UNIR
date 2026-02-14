const inputNombre = document.querySelector("#inputNombre");
const inputCorreo = document.querySelector("#inputCorreo");
const inputPass = document.querySelector("#inputPass");
const btnAgregar = document.querySelector("#btnAgregar");
const divResultado = document.querySelector("#divResultado");
const ulProductos = document.querySelector("#listaProductos");

getAll();

btnAgregar.addEventListener("click", (e) => {
  console.log("Elemento pulsado correctamente");

  // createUser(1);
  /*
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
    .catch((rej) => {});*/
});

// siempre que en un ambito de function await -> async
async function createUser() {
  let response = await fetch(`http://127.0.0.1:8080/api/usuarios/create/`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    // string
    body: JSON.stringify({
      nombre: inputNombre.value,
      correo: inputCorreo.value,
      pass: inputPass.value,
    }),
  });
  let responseJSON = await response.json();
  console.log(responseJSON);
}

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
function getAll() {
  let url = "http://127.0.0.1:8080/api/productos/getAll";
  fetch(url)
    .then((res) => res.json())
    .then((res1) => {
      console.log(res1.data);
      res1.data.forEach((item) => {
        ulProductos.innerHTML += `<li class='list-group-item'>${item.id} ${item.nombre} ${item.precio}</li>`;
      });
    });
}
