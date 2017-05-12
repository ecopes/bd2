#!/bin/bash
# Crear un viaje

echo "\n"
if [ $#  = '5' ]; then
# cambio los espacios por %20 para que se pueda enviar por http
origen=$(echo $1 | sed 's/ /%20/g')
destino=$(echo $2 | sed 's/ /%20/g')

curl -X POST "http://localhost:8080/MuberRESTful/rest/services/viajes/nuevo/?origen=$origen&destino=$destino&conductorId=$3&costoTotal=$4&cantidadPasajeros=$5"
else
echo "Envie 5 parametro: \n 1) origen  \n 2) destino \n 3) id del conductor \n 4) costo total \n 5) cantidad de pasajeros "
fi
echo "\n"
