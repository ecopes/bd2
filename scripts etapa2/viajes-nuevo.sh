#!/bin/bash
# Crear un viaje

echo "\n"
if [ $#  = '5' ]; then
curl -H "Content-Type: application/json" -X POST -d '{"origen": "'"$1"'","destino":"'"$2"'","conductorId":"'$3'","costoTotal":"'$4'","cantidadPasajeros":"'$5'"}'  http://localhost:8080/MuberRESTful/rest/services/viajes/nuevo/
else
echo "Envie 5 parametro: \n 1) origen  \n 2) destino \n 3) id del conductor \n 4) costo total \n 5) cantidad de pasajeros "
fi
echo "\n"
