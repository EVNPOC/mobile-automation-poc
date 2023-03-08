pipeline {
    agent any
    stages {
        stage('execute test') {
            steps {
                bat './apache-maven-3.9.0/bin/mvn clean test'
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    echo 'Error when running the test cases'
                }
            }
        }
        stage('generate report') {
            steps {
                bat './apache-maven-3.9.0/bin/mvn allure:report'
                script {
                        allure([
                                includeProperties: false,
                                jdk: '',
                                properties: [[key: 'allure.issues.tracker.pattern', value: 'http://tracker.company.com/%s']],
                                reportBuildPolicy: 'ALWAYS',
                                results: [[path: 'target/allure-results']]
                        ])
                }
            }
        }
    }
}