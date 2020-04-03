package org.devops

//构建类型,命令
def Build(buildType,buildShell){

def buildTools = ["mvn":"M2_HOME","ant":"ANT_HOME","gradle":"GRADLE_HOME","npm":"NPM_HOME"]

    println("当前选择的构建类型为${buildType}")
    
    if ("${buildType}" == "npm") {
                sh """
                   export NODE_HOME=${buildHome}
                   export PATH=\$NODE_HOME/bin:\$PATH 
                   ${buildHome}/bin/${buildType} ${buildShell}"""
    } else {
    
    buildHome = tool buildTools[buildType]

    sh "${buildHome}/bin/${buildType} ${buildShell}"
    }
}
