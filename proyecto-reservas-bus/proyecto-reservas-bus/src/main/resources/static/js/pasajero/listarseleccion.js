  function guardarAsientos() {
            var asientosSeleccionados = [];
            var filasAsientos = document.querySelectorAll("tbody tr");

            filasAsientos.forEach(function(fila) {
                var idAsiento = fila.getAttribute("data-id");
                asientosSeleccionados.push(idAsiento);
            });

            document.getElementById("idsAsientosSeleccionados").value = asientosSeleccionados.join(',');

            document.getElementById("formReserva").submit();
        }