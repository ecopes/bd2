#!/bin/bash
# Listar todos los conductores registrados en Muber

echo "\n"
curl -X GET "http://localhost:8080/MuberRESTful/rest/services/conductores" || json_pp
echo "\n"



