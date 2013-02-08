class DashboardController {
  
  static defaultAction = "dashboard"
  def grailsApplication

  def welcome = {
    if(subject) {
      redirect action:'dashboard'
    }
  }

  def dashboard = {
    redirect controller:'snapshot', action:'index'
  }

}
