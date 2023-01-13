#!/usr/bin/env groovy
package com.example
class Docker implements Serializable {
    def script
    Docker(script){
        this.script = script

    }
    def buildDockerImage(String imageName){
        script.echo "building Docker Image"
        script.sh "docker build -t $imageName ."
    }

    def dockerLogin(){
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){

            script.sh "docker login -u $script.USER -p $script.PASS"
        }
    }

    def dockerPush(String imageName){
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){

            script.sh "docker push $imageName"
        }
    }

    def incrementVersion(){
        script.sh "mvn build-helper:parse-version versions:set \\\n" +
                "  -DnewVersion=\\${parsedVersion.majorVersion}.\\${parsedVersion.MinorVersion}.\\${parsedVersion.nextIncrementalVersion}"
    }
}