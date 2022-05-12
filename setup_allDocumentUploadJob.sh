#!/usr/bin/env ksh
typeset -x _HOSTNAME_PORT

_HOSTNAME_PORT=${1}

echo "Load test customerEntity into distillehr-document-storage-service..."
curl -H "Content-Type: application/json" \
    -d '{ "mnemonic": "PS",
    "name": "Projective Software",
    "streetAddress": "Test Address",
    "city": "Franklin",
    "state": "Tennessee",
    "zipCode": "37067",
    "phoneNumber": "111-111-1111",
    "contactName": "Derek Lewis" }' http://${_HOSTNAME_PORT}/customerEntities

echo "Load test facility into distillehr-document-storage-service..."
curl -H "Content-Type: application/json" \
    -d "{ \"customerEntity\": \"http://${_HOSTNAME_PORT}/customerEntities/1\",
    \"facilityName\": \"DistillEHR Test Facility\",
    \"facilityStreetAddress\": \"Test Address\",
    \"facilityCity\": \"Franklin\",
    \"facilityState\": \"Tennessee\",
    \"facilityZipCode\": \"37067\" }" \
    -X POST http://${_HOSTNAME_PORT}/facilities
