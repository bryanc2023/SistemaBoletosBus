 var fechaActual = new Date();

    // Formateamos la fecha para que tenga un formato legible
    var options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
    var fechaFormateada = fechaActual.toLocaleDateString('es-ES', options);

    // Actualizamos el contenido del párrafo con la fecha actual
    document.getElementById('fecha-actual').innerHTML = 'Boletos reservados en efectivo para hoy: ' + fechaFormateada;