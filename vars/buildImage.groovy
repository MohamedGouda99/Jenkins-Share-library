#!/usr/bin/env groovy
def call(){
    echo "building Docker Image"
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
        sh 'docker build -t gouda99/my-repo:jma-4.0 .'
        sh "docker login -u $USER -p $PASS"
        sh 'docker push gouda99/my-repo:jma-4.0'
    }
}