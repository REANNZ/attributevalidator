package aaf.base.identity

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString(includeNames=true, includeFields=true, excludes="sharedToken")
@EqualsAndHashCode
class Subject {
  static auditable = true

  String principal
  String cn
  String email
  String sharedToken

  boolean enabled

  static belongsTo = Role

  static hasMany = [ 
    sessionRecords: SessionRecord, 
    roles: Role, 
    permissions: Permission,
    snapshots: aaf.av.Snapshot
  ]

  static constraints = {
    principal(nullable: false, blank: false, unique:true)
    cn nullable:true, blank:false
    email nullable:true, email:true
    sharedToken nullable:true, blank:false
  }

  static mapping = {
    table 'base_subject'
  }
}
