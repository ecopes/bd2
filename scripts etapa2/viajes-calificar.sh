#!/bin/bash
# Crear una calificación de un pasajero para un viaje en particular

echo "\n"
if [ $#  = '4' ]; then
echo "Creando una calificacion para el pasajero $2 del viaje $1 con puntaje $3 y comentario $4"

# cambio los espacios por %20 para que se pueda enviar por http
comentario=$(echo $4 | sed 's/ /%20/g')

curl -X POST "http://localhost:8080/MuberRESTful/rest/services/viajes/calificar/?viajeId=$1&pasajeroId=$2&puntaje=$3&comentario=$comentario"
else
echo "Envie 4 parametro: \n 1) id del viaje  \n 2) id del pasajero \n 3) puntaje \n 4) comentario "
fi
echo "\n"
