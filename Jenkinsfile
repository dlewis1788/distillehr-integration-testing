node('ps-distillehr-docker') {
    try {
        stage('Build') {
            deleteDir()
            dir('distillehr-integration-testing') {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'c9af1764-68a0-4b06-9beb-7150f5b682e5', url: 'ssh://git-codecommit.us-west-2.amazonaws.com/v1/repos/distillehr-integration-testing']]])
            }
            dir('config-service') {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'c9af1764-68a0-4b06-9beb-7150f5b682e5', url: 'ssh://git-codecommit.us-west-2.amazonaws.com/v1/repos/config-service']]])
                sh('./gradlew build')
            }
            dir('eureka-service') {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'c9af1764-68a0-4b06-9beb-7150f5b682e5', url: 'ssh://git-codecommit.us-west-2.amazonaws.com/v1/repos/eureka-service']]])
                sh('./gradlew build')
            }
            dir('distillehr-document-storage-service') {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'c9af1764-68a0-4b06-9beb-7150f5b682e5', url: 'ssh://git-codecommit.us-west-2.amazonaws.com/v1/repos/distillehr-document-storage-service']]])
                sh('./gradlew build')
            }
            dir('distillehr-medhost-enterprise-extraction-service') {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'c9af1764-68a0-4b06-9beb-7150f5b682e5', url: 'ssh://git-codecommit.us-west-2.amazonaws.com/v1/repos/distillehr-medhost-enterprise-extraction-service']]])
                sh('./gradlew build')
            }
            dir('distillehr-document-viewer-service') {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'c9af1764-68a0-4b06-9beb-7150f5b682e5', url: 'ssh://git-codecommit.us-west-2.amazonaws.com/v1/repos/distillehr-document-viewer-service']]])
                sh('./gradlew build')
            }
        }
        stage('Package') {
            dir('config-service') {
                sh('./gradlew buildImage')
            }
            dir('eureka-service') {
                sh('./gradlew buildImage')
            }
            dir('distillehr-document-storage-service') {
                sh('./gradlew buildImage')
            }
            dir('distillehr-medhost-enterprise-extraction-service') {
                sh('./gradlew buildImage')
            }
            dir('distillehr-document-viewer-service') {
                sh('./gradlew buildImage')
            }
        }
        stage('Prepare') {
            dir('distillehr-integration-testing') {
                sh('docker-compose stop && docker-compose rm -f')
                sh('docker-compose up -d')
                sh('sleep 180')
                sh('./setup_allDocumentUploadJob.sh')
            }
        }
        stage('Testing') {
            dir('distillehr-integration-testing') {
                sh('./start_medhostMetadataImportJob.sh')
                sh('./start_allMedhostEncounterImportJob.sh')
                sh('groovy validateMedhostEncounterData.groovy')
                sh('groovy checkErrors_allMedhostEncounterImportJob.groovy')
                sh('./start_mpiUploadJob.sh')
                sh('./start_allMedhostEncounterExportPDFJob.sh')
                sh('groovy checkErrors_allMedhostEncounterExportPDFJob.groovy')
                sh('./start_allDocumentUploadJob.sh')
            }
        }
        stage('Clean-up') {
            dir('distillehr-integration-testing') {
                sh('docker-compose stop && docker-compose rm -f')
                slackSend(color: '#00FF00', message: "Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' completed successfully.")
            }
        }
    } catch (e) {
        dir('distillehr-integration-testing') {
            archiveArtifacts(artifacts: '*.err.txt')
        }
        slackSend(color: '#FF0000', message: "Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' failed!")
        throw e
    }
}