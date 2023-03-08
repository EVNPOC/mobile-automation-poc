pipeline {
    agent any

    triggers {
        cron('H 0 * * *')
    }

    stages {
        stage('Execute Test') {
            steps {
                bat './apache-maven-3.9.0/bin/mvn clean test'
            }
        }
        stage('Generate allure report') {
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