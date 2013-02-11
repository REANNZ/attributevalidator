package attributevalidator

import grails.util.Environment

class InjectDevelopmentAccountFilters {

  def grailsApplication

  def filters = {
    populate(controller:'snapshot', action:'index') {
      before = {
        // We may want to extend this over time, just need a few for now.
        if(Environment.current == Environment.DEVELOPMENT && grailsApplication.config.aaf.base.realms.federated.development.active) {
          request.setAttribute('eduPersonTargetedID', 'http://idp.com!http://sp.com!1234')
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
