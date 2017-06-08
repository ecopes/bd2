#!/bin/bash
# Finalizar un viaje. Considerar que el viaje sólo puede finalizarse una vez.

echo "\n"
if [ $#  = '1' ]; then
echo "Finalizando viaje $1"
curl -H "Content-Type: application/json" -X PUT -d '{"viajeId": "'"$1"'"}'  http://localhost:8080/MuberRESTful/rest/services/viajes/finalizar/
else
echo "Envie 1 parametro : \n id del viaje a finalizar"
fi
echo "\n"
