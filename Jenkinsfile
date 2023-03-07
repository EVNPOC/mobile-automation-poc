pipeline {
    agent any
    stages {
        stage('execute test') {
            steps {
                sh '''#!/bin/bash --login -x
                ./apache-maven-3.9.0/bin/mvn clean test
                '''
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    sh "exit 1"
                }
            }
        }
        stage('generate report') {
            steps {
                sh '''#!/bin/bash --login -x
                ./apache-maven-3.9.0/bin/mvn allure:report
                '''
            }
        }
    }
}