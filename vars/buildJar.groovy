#!/usr/bin/env groovy
def call(){
    echo "building the application"
    sh 'mvn clear'
    sh 'mvn package'
}