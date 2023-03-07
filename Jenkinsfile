pipeline {
    agent any
    stages {
        stage('execute test') {
            steps {
                bat './apache-maven-3.9.0/bin/mvn clean test'
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    bat "exit 1"
                }
            }
        }
        stage('generate report') {
            steps {
                script {
                        allure([
                                includeProperties: false,
                                jdk: '',
                                properties: [[key: 'allure.issues.tracker.pattern', value: 'http://tracker.company.com/%s']],
                                reportBuildPolicy: 'ALWAYS',
                                results: [[path: 'target/allure-results']]
                        ])
                }
                bat './apache-maven-3.9.0/bin/mvn allure:report'
            }
        }
    }
}