#!/user/bin/env groovy

def call(String imageName) {
    echo 'building the docker image...'
    withCredentials([usernamePassword(credentialsId: 'docker-hub', passwordVariable: 'PASSWD', usernameVariable: 'USER')]) {
    sh "docker image build -t ${imageName} ."
    sh 'echo $PASSWD | docker login -u $USER --password-stdin'
    sh "docker push ${imageName}"
    }

}

