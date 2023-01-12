#!/usr/bin/env groovy
def call(String imageName){
    echo "building Docker Image"
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
        sh "docker build -t $imageName ."
        sh "docker login -u $USER -p $PASS"
        sh "docker push $imageName"
    }
}