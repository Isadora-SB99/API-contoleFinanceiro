#!/bin/bash
 
echo 'Starting to Deploy...'
ssh ubuntu@52.38.40.75 " sudo docker image prune -f 
        cd controleFinanceiro 
        sudo docker-compose down
        git fetch origin
        git reset --hard origin/testando-CI  &&  echo 'You are doing well'
        sudo docker-compose build && sudo docker-compose up -d
        "
echo 'Deployment completed successfully'
