properties([pipelineTriggers([cron('H 0 * * *'), githubPush()])])

pipeline {
    agent any

    stages {
        stage('Clean Test') {
            steps {
                bat './apache-maven-3.9.0/bin/mvn clean'
            }
        }
        stage('Execute Test') {
            steps {
                bat './apache-maven-3.9.0/bin/mvn test'
            }
            post {
                always {
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

    post {
        success {
            mail to: 'tuan_nguyen4@epam.com',
              subject: "Success Pipeline: ${currentBuild.fullDisplayName}",
              body: "OK with ${env.BUILD_URL}"
        }
        failure {
          mail to: 'tuan_nguyen4@epam.com',
              subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
              body: "Something is wrong with ${env.BUILD_URL}"
        }
    }
}