#!/usr/bin/env groovy
package com.example
class Docker implements Serializable {
    def script
    def docker_registry = 'gouda99/my-repo'
    Docker(script){
        this.script = script

    }
    def buildDockerImage(String imageName){
        script.echo "building Docker Image"
        script.sh "docker build -t $docker_registry:$imageName ."
    }

    def dockerLogin(){
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){

            script.sh "docker login -u $script.USER -p $script.PASS"
        }
    }

    def dockerPush(String imageName){
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){

            script.sh "docker push $docker_registry:$imageName"
        }
    }

    
}