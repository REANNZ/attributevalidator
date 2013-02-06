modules = { 
  'app' {
    dependsOn 'app_base'
    resource url: 'css/application.css', attrs:[rel:'stylesheet/less', type:'css']
    resource url: 'js/application.js', attrs:[type:'js']
  }
}
