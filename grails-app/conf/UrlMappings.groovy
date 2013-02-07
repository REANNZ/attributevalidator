class UrlMappings {

  static mappings = {
    // Public Welcome, Internal Dashboard
    "/"(controller:"dashboard", action:"welcome")
    "/snapshot"(controller:"snapshot", action:"index")
  }

}
