#!/bin/bash
# Listar los 10 conductores mejor calificados que no tengan viajes abiertos registrados

echo "\n"
curl -X GET "http://localhost:8080/MuberRESTful/rest/services/conductores/top10" || json_pp
echo "\n"



