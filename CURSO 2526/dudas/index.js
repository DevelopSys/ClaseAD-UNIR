const btnAgregar = document.querySelector("#btnAgregar");
const inputNombre = document.querySelector("#inputNombre");
const parrafoResultate = document.querySelector("#inputNombre");

btnAgregar.addEventListener("click", (e) => {
  // caputar lo que hay en el iput y lanzarlo a la base de datos
  fetch("https://www.miapi.com/users/create")
    .then((res) => res.json())
    .then((res1) => {
        // aqui tengo el JSON
        // pinto un nodo para poder decir al usuario que se ha insertado
        parrafoResultate.innerHTML = res1.message
    })
    .catch((err) => {});
});
