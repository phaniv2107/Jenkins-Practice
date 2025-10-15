    def call(Map config) {
        pipeline {
            agent any
            stages {
                stage('Checkout') {
                    steps {
                        git url: config.gitRepo, branch: config.gitBranch
                    }
                }
                stage('Build') {
                    steps {
                        script {
                            // Example build command based on config
                            if (config.buildTool == 'maven') {
                                sh "mvn clean install"
                            } else if (config.buildTool == 'gradle') {
                                sh "gradle build"
                            }
                        }
                    }
                }
                stage('Test') {
                    steps {
                        sh "run-tests.sh" // Assuming a script in your project
                    }
                }
                // Add more stages as needed
            }
            post {
                always {
                    echo "Pipeline finished for project: ${config.projectName}"
                }
            }
        }
    }
