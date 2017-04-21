#!/bin/bash
# Crear una calificación de un pasajero para un viaje en particular

echo "\n"
if [ $#  = '4' ]; then
curl -X POST "http://localhost:8080/MuberRESTful/rest/services/viajes/calificar/?viajeId=$1&pasajeroId=$2&puntaje=$3&comentario=$4"
else
echo "Envie 4 parametro: \n 1) id del viaje  \n 2) id del pasajero \n 3) puntaje \n 4) comentario "
fi
echo "\n"