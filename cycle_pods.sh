#!/usr/bin/env ksh

kubectl rollout restart deployment/config-service
kubectl rollout restart deployment/distillehr-document-storage-service
kubectl rollout restart deployment/distillehr-document-storage-service-db
kubectl rollout restart deployment/distillehr-document-viewer-service
kubectl rollout restart deployment/distillehr-medhost-enterprise-extraction-service
kubectl rollout restart deployment/distillehr-medhost-enterprise-extraction-service-db
kubectl rollout restart deployment/distillehr-empi-service
kubectl rollout restart deployment/eureka-service
