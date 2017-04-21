#!/bin/bash
# Listar todos los pasajeros registrados en Muber

echo "\n"
curl -X GET "http://localhost:8080/MuberRESTful/rest/services/pasajeros" || json_pp
echo "\n"



