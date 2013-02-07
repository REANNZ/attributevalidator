package aaf.av

class SnapshotController {

  final CURRENT_SNAPSHOT = "aaf.av.SnapshotController.CURRENT_SNAPSHOT"

  def grailsApplication

  def index = {

    def snapshot = Snapshot.get(session.getAttribute(CURRENT_SNAPSHOT))

    if(!snapshot) {
        snapshot = new Snapshot(subject:subject)

      Snapshot.coreAttributes.each { attr ->
        grabAttribute(snapshot, attr)
      }
      Snapshot.optionalAttributes.each { attr ->
        grabAttribute(snapshot, attr)
      }

      // We force whatever drivel we get into the DB, as we want to see the bad data
      snapshot.save(flush:true, validate:false, failOnError:false).validate()

      session.setAttribute(CURRENT_SNAPSHOT, snapshot.id)
    } 
    else { snapshot.validate() }

    [snapshot:snapshot]
  }

  private void grabAttribute(Snapshot snapshot, String attr) {
    log.info "Retrieving value for attribute $attr"
    snapshot."$attr" = federatedAttributeValue(grailsApplication.config.aaf.base.realms.federated.mapping."${attr}")

    if(snapshot."$attr")
      log.info "For attribute $attr retrived value ${snapshot."$attr"}"
    else
      log.info "No value present for $attr"
  }

  private String federatedAttributeValue(def attr) {
    if(attr instanceof String) {
      log.info "Looking for value of attribute $attr"
      def value = null
      if(grailsApplication.config.aaf.base.realms.federated.request.attributes) {
        if(request.getAttribute(attr))
          value = new String(request.getAttribute(attr).getBytes("ISO-8859-1"))
      } else {
        if(request.getHeader(attr))
          value = new String(request.getHeader(attr).getBytes("ISO-8859-1"))  // Not as secure
      }
      
      value
    } else {
      log.error "Not attempting to retrieve attribute. No mapping in aaf.base.realms.federated.mapping"
    }
  }

}
