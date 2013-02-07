package attributevalidator

class InjectDevelopmentAccountFilters {

    def filters = {
        all(controller:'snapshot', action:'index') {
            before = {
              request.setAttribute('eduPersonTargetedID', 'http://idp.com!http://sp.com!1234')
              request.setAttribute('cn', 'Development User')
              request.setAttribute('auEduPersonSharedToken', '1234')
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
