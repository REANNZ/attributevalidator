class DashboardController {
  
  static defaultAction = "dashboard"

  def welcome = {
    if(subject) {
      redirect action:'dashboard'
    }
  }

  def dashboard = {
  }

}
