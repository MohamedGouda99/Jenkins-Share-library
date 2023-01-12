#!/usr/bin/env groovy
package com.example
class Docker implements Serializable {
    def script
    Docker(script){
        this.script = script

    }
    def buildDockerImage(String imageName){
        script.echo "building Docker Image"
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
            script.sh "docker build -t $imageName ."
            script.sh "docker login -u $script.USER -p $script.PASS"
            script.sh "docker push $imageName"
        }
    }
}