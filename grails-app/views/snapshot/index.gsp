
<!doctype html>
<html>
  <head>
    <meta name="layout" content="public" />
  </head>
  <body>

    <h2>Current snapshot of your released attributes. <small>Taken on <g:fieldValue bean="${snapshot}" field="dateCreated"/></small></h2>

    <g:render template="/templates/flash" plugin="aafApplicationBase"/>
    <g:render template="/templates/errors_bean" model="['bean':snapshot]" plugin="aafApplicationBase"/>

    <table class="table">
      <tbody>
        <tr>
          <td colspan="4" style="padding-top: 36px;"><h4 class="muted">Core Attributes</h4></td>
        </tr>
        <tr>
          <th>Attribute</th>
          <th>Supplied Value</th>
          <th>Status</th>
          <th>Required format and further details</th>
        </tr>
        <g:each in="${snapshot.coreAttributes.sort{it}}" var="attr">
          <g:render template="/templates/attribute" model="[field:attr, referenceURL:"http://wiki.aaf.edu.au/tech-info/attributes/${attr.toLowerCase()}"]" />
        </g:each>

        <tr>
          <td colspan="4" style="padding-top: 36px;"><h4 class="muted">Optional Attributes <small><em>Not supplying optional attributes is valid</em></small></h4></td>
        </tr>
        <tr>
          <th>Attribute</th>
          <th>Supplied Value</th>
          <th>Status</th>
          <th>Required format and further details</th>
        </tr>
        <g:each in="${snapshot.optionalAttributes.sort{it}}" var="attr">
          <g:render template="/templates/attribute" model="[field:attr, referenceURL:"http://wiki.aaf.edu.au/tech-info/attributes/${attr.toLowerCase()}"]" />
        </g:each>
      </tbody>
    </table>

  </body>
</html>
