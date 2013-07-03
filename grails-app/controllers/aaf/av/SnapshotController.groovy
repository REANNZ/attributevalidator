package aaf.av

import aaf.base.identity.Subject

import org.apache.shiro.SecurityUtils
import org.springframework.web.servlet.ModelAndView

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

      snapshot.validate(['auEduPersonSharedToken'])
      if (snapshot.errors['auEduPersonSharedToken']) {
        return new ModelAndView('/snapshot/badSharedToken', [auEduPersonSharedToken: snapshot.auEduPersonSharedToken])
      }

      // We force whatever drivel we get into the DB, as we want to see the bad data
      snapshot.save(flush:true, validate:false, failOnError:false).validate()

      session.setAttribute(CURRENT_SNAPSHOT, snapshot.id)
    } 
    else { snapshot.validate() }

    if(params.pdf) {
      renderPdf([template:'/templates/snapshot_pdf', filename:'attributevalidatorsnapshot', model:[snapshot:snapshot]])
      return
    } 

    [snapshot:snapshot]
  }

  def historical = {
    if(SecurityUtils.subject.isPermitted('app:administration')) {
      def subjects = Subject.list()
      [subjects:subjects]
    } else {
      log.warn "Attempt to do administrative historical account listing by $subject was denied - not permitted by assigned permissions"
      response.sendError 403
    }
  }

  def historicalsubject = {
    if(SecurityUtils.subject.isPermitted('app:administration')) {
      def subject = Subject.get(params.id)

      if(!subject) {
        response.sendError '500'
        return
      }

      [subject:subject]
    } else {
      log.warn "Attempt to do administrative historical account snapshots for $subject by $subject was denied - not permitted by assigned permissions"
      response.sendError 403
    }
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
