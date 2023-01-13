#!/usr/bin/env groovy
import com.example.Docker
def call(parsedVersion){
    return new Docker(this).incrementVersion(parsedVersion)
}