<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
  <head>
    <style type="text/css">      
      /* Reset Styles */
      body{margin:0; padding:0; font:'Helvetica, Arial, sans-serif'}
      img{border:0; height:auto; line-height:100%; outline:none; text-decoration:none;}
      table {width: 100%;table-layout:fixed;}
      table td {word-wrap: break-word;}
      
      h1 {
        color:#f2f2f2;
        font-size: 1.4em;
      }

      h2{
        color:#f2f2f2;
        font-size: 1.2em;
      }

      h2 small {
        font-size: 1.0em;
      }

      h3 {
        color:#666666;
        font-size: 1.1em;
      }

      h3.error {
        color: #D60606;
      }

      h4{
        color:#666666;
      }

      h3 small {
        font-size: 0.8em;
      }

      span.alert-error {
        color: #D60606;
      }

      small.attr {
        font-size: 0.7em;
      }
    </style>
  </head>
  <body>

    <div style="background-color:#495666; padding: 12px;">
      <h1>Australian Access Federation - Attribute Validation</h1>
      <h2>Snapshot of released attributes as at <g:formatDate date="${snapshot.dateCreated}" type="datetime" style="MEDIUM"/></h2>
    </div>

    <g:hasErrors bean="${snapshot}">
      <div class="alert alert-error">
        <h3 class="error">Identified Errors</h3>
        <ol>
          <g:eachError bean="${snapshot}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
          </g:eachError>
        </ol>
      </div>
      <hr/>
    </g:hasErrors>

    <h3 class="muted">Core Attributes</h3>
    <table class="table table-borderless">
      <tbody>
        <tr>
          <th>Attribute</th>
          <th style="width:75px;">Status</th>
          <th>Supplied Value</th>
        </tr>
        <g:each in="${snapshot.coreAttributes.sort{it}}" var="attr">
          <g:render template="/templates/attribute_pdf" model="[field:attr]" />
        </g:each>
      </tbody>
    </table>

    <hr/>

    <h3 class="muted">Optional Attributes <small><em>Not supplying optional attributes is valid</em></small></h3>
    <table>
      <tbody>
        <tr>
          <th>Attribute</th>
          <th style="width:75px;">Status</th>
          <th>Supplied Value</th>
        </tr>
        <g:each in="${snapshot.optionalAttributes.sort{it}}" var="attr">
          <g:render template="/templates/attribute_pdf" model="[field:attr]" />
        </g:each>
      </tbody>
    </table>

    <hr/>

    <p>
      For technical requirements associated with each attribute please refer to <a href="http://wiki.aaf.edu.au/tech-info/attributes">http://wiki.aaf.edu.au/tech-info/attributes</a>
    </p>

    <p>
      Generated at <g:link action="index">${grailsApplication.config.grails.serverURL}</g:link>
    </p>

    <hr/>
    
    <p>
      <small>
      <g:message code="branding.application.name"/> <strong><g:message code="branding.footer.version"/> <g:meta name="app.version"/></strong>
      </small>
    </p>

  </body>
</html>
