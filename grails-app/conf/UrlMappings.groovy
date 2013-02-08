class UrlMappings {

  static mappings = {
    // Public Welcome, Internal Dashboard
    "/"(controller:"dashboard", action:"welcome")
    "/dashboard/dashboard"(controller:"dashboard", action:"dashboard")

    "/snapshot"(controller:"snapshot", action:"index")
    "/snapshot/historical"(controller:"snapshot", action:"historical")
    "/snapshot/historical/$id"(controller:"snapshot", action:"historicalsubject")
  }

}
