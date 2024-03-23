#!/bin/bash


. $workdir/deploy/log.sh

clean() {
  kubectl delete pvc data-integrator-postgresql-0  &>/dev/null
  kubectl delete pv postgresql-pv --grace-period=0 --force &>/dev/null
  kubectl delete pod integrator-postgresql-client 0 &>/dev/null
}

clean

helm repo add bitnami https://charts.bitnami.com/bitnami
kubectl apply -f $workdir/deploy/postgres/postgres-pv.yaml
#If using with --namespace need to copy secret after deploy to default ns.
inf "postgres" "Installing postgres"
helm install integrator bitnami/postgresql --set persistence.existingClaim=postgresql-pv-claim --set volumePermissions.enabled=true &>/dev/null
inf "postgres" "Exposing postgres"

if ! kubectl wait --for=condition=Ready pod -l app.kubernetes.io/name=postgresql --timeout=150s; then
  inf "postgres" "Timeout occurred. Cleaning up resources."
  clean
fi

export POSTGRES_PASSWORD=$(kubectl get secret integrator-postgresql -o jsonpath="{.data.postgres-password}" | base64 --decode)

inf "postgres" "Creating database integrator"
kubectl run integrator-postgresql-client --rm -i --restart='Never' --namespace default --image docker.io/bitnami/postgresql:15.2.0-debian-11-r5 \
  --env="PGPASSWORD=$POSTGRES_PASSWORD" \
  --command -- sh -c "psql --host integrator-postgresql -U postgres -d postgres -p 5432 -c 'CREATE DATABASE integrator;' < /dev/null"

inf "postgres" "Applying postgres IP to services config"
postgres_ip=$(kubectl get svc integrator-postgresql --template"={{.spec.clusterIP}}")
cp $workdir/manager/manager-template.yaml $workdir/manager/manager.yaml
cp $workdir/reader/reader-template.yaml $workdir/reader/reader.yaml
sed -i '' "s/POSTGRES_IP_SED/$postgres_ip/g" $workdir/manager/manager.yaml
sed -i '' "s/POSTGRES_IP_SED/$postgres_ip/g" $workdir/reader/reader.yaml
