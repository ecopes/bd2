#!/bin/bash
# Cargar crédito a un pasajero en particular

echo "\n"
if [ $#  = '2' ]; then
echo "Agregando al pasajero $1 el credito $2"
curl -H "Content-Type: application/json" -X PUT -d '{"pasajeroId": "'"$1"'","monto":"'"$2"'"}'  http://localhost:8080/MuberRESTful/rest/services/pasajeros/cargarCredito/
else
echo "Envie 2 parametro: \n 1) id del pasajero  \n 2) monto a agegar"
fi
echo "\n"
