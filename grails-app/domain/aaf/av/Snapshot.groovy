package aaf.av

import org.apache.commons.validator.EmailValidator

class Snapshot {

  private static final affiliations = ['faculty', 'student', 'staff', 'employee', 'member', 'affiliate', 'alum', 'library-walk-in']

  public static final coreAttributes = ['cn', 'mail', 'auEduPersonSharedToken', 'displayName', 'eduPersonAssurance', 'eduPersonAffiliation', 
                                        'eduPersonScopedAffiliation', 'eduPersonEntitlement', 'eduPersonTargetedID', 
                                        'o', 'authenticationMethod']
  public static final optionalAttributes = ['givenName', 'surname', 'mobileNumber', 'telephoneNumber', 'postalAddress', 'organizationalUnit', 
                                            'schacHomeOrganization', 'schacHomeOrganizationType']

  Date dateCreated
  static belongsTo = [subject:aaf.base.identity.Subject]

  // AAF attributes
  
  // Core
  String cn                             // oid:2.5.4.3
  String mail                           // oid:0.9.2342.19200300.100.1.3
  String auEduPersonSharedToken         // oid:1.3.6.1.4.1.27856.1.2.5
  String displayName                    // oid:2.16.840.1.113730.3.1.241
  String eduPersonAssurance             // oid:1.3.6.1.4.1.5923.1.1.1.11
  String eduPersonAffiliation           // oid:1.3.6.1.4.1.5923.1.1.1.1
  String eduPersonScopedAffiliation     // oid:1.3.6.1.4.1.5923.1.1.1.9
  String eduPersonEntitlement           // oid:1.3.6.1.4.1.5923.1.1.1.7
  String eduPersonTargetedID            // oid:1.3.6.1.4.1.5923.1.1.1.10
  String o                              // oid:2.5.4.10
  String authenticationMethod
  
  // Optional
  String givenName                      // oid:2.5.4.42
  String surname                        // oid:2.5.4.4
  String mobileNumber                   // oid:0.9.2342.19200300.100.1.41
  String telephoneNumber                // oid:2.5.4.20
  String postalAddress                  // oid:2.5.4.16
  String organizationalUnit             // oid:2.5.4.11
  String schacHomeOrganization          // oid:1.3.6.1.4.1.25178.1.2.9 
  String schacHomeOrganizationType      // oid:1.3.6.1.4.1.25178.1.2.10

  static constraints = {
    cn (nullable:true, blank:false, validator: validCn)
    mail (nullable:true, validator: validMail)
    auEduPersonSharedToken (nullable:true, blank:false, size: 27..27, matches: '^[A-z0-9_-]+$', validator: {if(!it) return false })
    displayName (nullable:true, blank:false, validator: {if(!it) return false })
    eduPersonAssurance (nullable:true, blank:false, validator: validEduPersonAssurance)
    eduPersonAffiliation (nullable:true, blank:false, validator: validEduPersonAffiliation)
    eduPersonScopedAffiliation (nullable:true, blank:false, validator: validEduPersonScopedAffiliation)
    eduPersonEntitlement (nullable:true)
    eduPersonTargetedID (nullable:true, blank:false, validator: {if(!it) return false })
    o (nullable:true, blank:false, validator: {if(!it) return false })
    authenticationMethod (nullable:true, blank:false, validator: validAuthenticationMethod)

    givenName (nullable:true, blank:true, validator: validGivenName)
    surname (nullable:true, blank:true, validator: validSurname)
    mobileNumber (nullable:true, blank:true)
    telephoneNumber (nullable:true, blank:true)
    postalAddress (nullable:true, blank:true)
    organizationalUnit (nullable:true, blank:true)
    
    // Regex adapted from http://stackoverflow.com/a/106223 for RFC compliance, ensures at least xyz.com as opposed to documented which allows xyz
    schacHomeOrganization (nullable:true, blank:true, matches: "^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)+([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])\$")
    schacHomeOrganizationType (nullable:true, blank:true, matches: '^urn:.+$')
  }

  static validCn = { value, obj ->
    if(!value) { return false }

    if(value.contains(';')) { return false }

    value?.count(' ') <= 1
  }

  static validMail = { value, obj ->
    if(!value) { return false }

    EmailValidator emailValidator = EmailValidator.getInstance()
    boolean valid = true

    if (value?.contains(";")) {
      value?.split(";").each { val -> valid = valid ? emailValidator.isValid(val):false  }
    } 
    else { valid = emailValidator.isValid(value) }

    valid
  }

  static validEduPersonAssurance = { value, obj ->
    if(!value) { return false }

    String regex = '(urn:mace:aaf.edu.au:iap:id:[0-4]|urn:mace:aaf.edu.au:iap:authn:[0-4])'
    Snapshot.attributeMatches(regex, value)
  }

  static validAuthenticationMethod = { value, obj ->
    if(!value) { return false }
  }

  static validEduPersonAffiliation = { value, obj ->
    if(!value) { return false }

    String regex = "(${Snapshot.affiliations.join('|')})"
    Snapshot.attributeMatches(regex, value)
  }

  static validEduPersonScopedAffiliation = { value, obj ->
    if(!value) { return false }

    String regex = "(${Snapshot.affiliations.join('|')})@((([A-z0-9\\-]+)\\.)*[A-z0-9\\-]+)"
    Snapshot.attributeMatches(regex, value)
  }

  static validGivenName = { value, obj ->
    if(!value) { return true }

    if(value.contains(';')) { return false }

    value?.count(' ') < 1
  }

  static validSurname = { value, obj ->
    if(!value) { return true }

    if(value.contains(';')) { return false }

    value?.count(' ') < 1
  }

  private static boolean attributeMatches(String regex, String value, boolean multivalued = true) {
    boolean valid = true

    // Those which are multivaluded have values seperated by ; (Shibboleth SP standard)
    if (value?.contains(";") && multivalued) {
      value?.split(";").each { attr -> valid = valid ? attr ==~ regex:false }
    } 
    else { valid = value ==~ regex }

    valid
  }
}
