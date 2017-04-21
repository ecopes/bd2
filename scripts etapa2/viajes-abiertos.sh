#!/bin/bash
# Listar todos los viajes abiertos en Muber

echo "\n"
curl -X GET "http://localhost:8080/MuberRESTful/rest/services/viajes/abiertos" || json_pp
echo "\n"



