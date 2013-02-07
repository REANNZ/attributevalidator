class SecurityFilters {

  def filters = {
    workflow_authenticated(uri:"/snapshot/**") {
      before = {
        accessControl { true }
      }
    }
  }
}
