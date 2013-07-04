package aaf.av

import spock.lang.*
import org.springframework.web.servlet.ModelAndView

@TestFor(SnapshotController)
@Mock(Snapshot)
class SnapshotControllerSpec extends spock.lang.Specification {
  def setup() {
    grailsApplication.config.aaf.base.realms.federated << [
      active: true,
      development: [ active: false ],
    ]

    def subject = Mock(org.apache.shiro.subject.Subject)
    controller.metaClass.getSubject = {-> subject}

    request.setAttribute('persistent-id', 'https://idp.one.edu.au/idp/shibboleth!-!123412341234-12341234-12341234-2134')
    request.setAttribute('Shib-Session-ID', 'fake-sessionid-test')
    request.setAttribute('Shib-Identity-Provider', 'https://idp.one.edu.au/idp/shibboleth')
  }

  def 'create a snapshot when a user presents only a shared token'() {
    setup:
    def validToken = '123456789_123456789_1234567'
    request.setAttribute('auEduPersonSharedToken', validToken)

    def subject = Mock(org.apache.shiro.subject.Subject)
    controller.metaClass.getSubject = {-> subject}

    when:
    def model = controller.index()

    then:
    model instanceof Map
    model.snapshot.auEduPersonSharedToken == validToken
  }

  def 'show a different view when the shared token is too short'() {
    setup:
    def validToken = '123456789'
    request.setAttribute('auEduPersonSharedToken', validToken)

    when:
    controller.index()

    then:
    view == '/snapshot/badSharedToken'
    model.auEduPersonSharedToken == validToken
  }

  def 'show a different view when the shared token is too long'() {
    setup:
    def validToken = '123456789_123456789_123456789_123456789_123456789_123456789_123456789'
    request.setAttribute('auEduPersonSharedToken', validToken)

    when:
    controller.index()

    then:
    view == '/snapshot/badSharedToken'
    model.auEduPersonSharedToken == validToken
  }
}
