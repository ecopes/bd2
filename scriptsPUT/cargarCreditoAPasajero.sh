#!/bin/bash
# Cargar crédito a un pasajero en particular

echo "\n"
if [ $#  = '2' ]; then
echo "Agregando al pasajero $1 el credito $2"
curl -X PUT "http://localhost:8080/MuberRESTful/rest/services/pasajeros/cargarCredito/?pasajeroId=$1&monto=$2"
else
echo "Envie 2 parametros, pasajero primero y luego el monto"
fi
echo "\n"
