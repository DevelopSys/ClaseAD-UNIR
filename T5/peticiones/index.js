async function getAllUser() {
  let respuesta = await fetch(
    "https://cors-anywhere.herokuapp.com/http://localhost:8080/usuarios/getAll"
  );
  let respuestaJSON = await respuesta.json();
  console.log(respuestaJSON);
}

getAllUser();
