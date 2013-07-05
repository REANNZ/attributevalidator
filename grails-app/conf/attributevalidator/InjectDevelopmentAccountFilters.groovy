package attributevalidator

import grails.util.Environment

class InjectDevelopmentAccountFilters {

  def grailsApplication
  def developmentAttributesService

  def filters = {
    populate(controller:'snapshot', action:'index') {
      before = {
        if (grailsApplication.config.aaf.base.realms.federated.development.active) {
          developmentAttributesService.injectAttributes(request, session)
        }

        return true
      }
    }
  }
}
