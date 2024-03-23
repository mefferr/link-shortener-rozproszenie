#!/bin/bash

export workdir=~/work/AG/google-integrator

. $workdir/deploy/log.sh

#external_ip=$(kubectl get svc -n istio-system cluster-gateway --template="{{range .status.loadBalancer.ingress}}{{.ip}}{{end}}")

main() {
  inf "integrator] \n\n\n\t\t\t\t INSTALLING APP\t\t" "\n"
  checkClusterConnection
  checkOrInstallHelm
  installPostgress
#  initGateway
#  rotateCerts
  initRegistry
#  postInstallInfo
  runApps
}

initRegistry() {
  inf "integrator" "\tInitializing registry"
  docker run -d -p 5000:5000 --restart=always --name registry registry:2
  inf "integrator" "Registry initialized"
}

checkClusterConnection() {
  kubectl get nodes &>/dev/null
  if [ $? -eq 0 ]; then
    inf "integrator" "Connection to cluster successfull."
  else
    err "integrator" "Can't connect to kubernetes cluster. Check your kubectl config."
    exit
  fi
}

checkOrInstallHelm() {
  helm version &>/dev/null
  if [ $? -eq 0 ]; then
    inf "integrator" "Helm already present."
  else
    err "integrator" "Helm not installed. Installing"
    curl https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 | bash
    if [ $? -eq 0 ]; then
      inf "integrator" "Helm installed."
    else
      err "integrator" "Failure installing helm."
      exit
    fi
  fi
}

installPostgress() {
  inf "integrator" "Postgres setup"
  sh $workdir/deploy/postgres/init-postgres.sh
}

runApps() {
  kubectl apply -f $workdir/manager/manager.yaml
  kubectl apply -f $workdir/reader/reader.yaml
}

main
