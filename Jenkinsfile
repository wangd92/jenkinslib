#!groovy

@Library('jenkinslib') _
def tools = new org.devops.tools()

hello()

pipeline {
  agent any
  
  options{
      timestamps()
	  skipDefaultCheckout()
	  disableConcurrentBuilds()
	  timeout(time: 1, unit: 'HOURS')
  }
  
  stages{
      stage("拉取代码 "){ //阶段名称
	      when { environment name: 'test', value: 'abcd' }
          steps{   //步骤，一般一个stage下都会有一个steps
              timeout(time:5, unit:"MINUTES"){    //步骤超时时间
                  script{ //填写运行代码
                    println('获取代码')
					tools.PrintMes("获取代码",'green')
					println("${test}")
					
					input id: 'Test', message: '我们是否要继续？', ok: '是，继续吧', parameters: [choice(choices: ['a', 'b'], description: '', name: 'test1')], submitter: 'admin'
              }
            }
          }
        }
      
	  stage("01"){
	      failFast true
		  parallel {
	  
			  //构建
			  stage("打包"){
				  steps{
					  timeout(time:20, unit:"MINUTES"){
						  script{
							  println('应用打包')
							  tools.PrintMes("应用打包",'green')
							  mvnHome = tool "M2_HOME"
							  println(mvnHome)
							  
							  sh "${mvnHome}/bin/mvn --version"					  
					  }
					}
				  }
				}
				
			   //代码扫描
			  stage("CodeScan"){
				  steps{
					  timeout(time:30, unit:"MINUTES"){
						  script{
							  println('代码扫描')
							  tools.PrintMes("代码扫描",'green')
					   }
					 }
				  }
				}
		    }
	    }
	}
  //构建后操作
  post {
        always {
            script{
                println("always")
            }
        }

        success {
            script{
                currentBuild.description += "\n 构建成功!" 
            }
        }

        failure {
            script{
                currentBuild.description += "\n 构建失败!" 
            }
        }

        aborted {
            script{
                currentBuild.description += "\n 构建取消!" 
            }
        }
    }
}
