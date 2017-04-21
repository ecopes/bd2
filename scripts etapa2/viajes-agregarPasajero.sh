#!/bin/bash
# Agregar un pasajero a un viaje
echo "\n"
if [ $#  = '2' ]; then
echo "Agregando el pasajero $1 al viaje $2"
curl -X PUT "http://127.0.0.1:8080/MuberRESTful/rest/services/viajes/agregarPasajero/?pasajeroId=$1&viajeId=$2"
else
echo "Envie 2 parametros: \n 1) id del pasajero \n 2) id del viaje"
fi
echo "\n"
