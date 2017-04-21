#!/bin/bash
# Obtener la información de un conductor (nombre de usuario, viajes realizados, puntaje promedio y fecha de licencia)

echo "\n"
if [ $#  = '1' ]; then
curl -X GET "http://localhost:8080/MuberRESTful/rest/services/conductores/detalle/?conductorId=$1"
else
echo "Envie 1 parametro: id del pasajero"
fi
echo "\n"
