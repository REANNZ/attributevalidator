package attributevalidator

import grails.util.Environment

class InjectDevelopmentAccountFilters {

  def grailsApplication

  def filters = {
    populate(controller:'snapshot', action:'index') {
      before = {
        // We may want to extend this over time, just need a few for now.
        if(Environment.current == Environment.DEVELOPMENT && grailsApplication.config.aaf.base.realms.federated.development.active) {
          request.setAttribute('persistent-id', 'https://vho.test.aaf.edu.au/idp/shibboleth!-!8fde68a6-0897-4b59-a4da-ab4d465792299')
          request.setAttribute('cn', 'Development User')
          request.setAttribute('auEduPersonSharedToken', '1234')
        }
      }
      after = { Map model ->

      }
      afterView = { Exception e ->

      }
    }
  }
}
