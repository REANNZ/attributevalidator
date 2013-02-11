
<!doctype html>
<html>
  <head>
    <meta name="layout" content="internal" />
  </head>
  <body>

    <h3>Attribute history for ${subject.cn} <small>${subject.principal}</small></h3>

    <g:each in="${subject.snapshots}" var="snapshot">
      <g:set var="snapshot" value="${snapshot.validate()}"/>
      <h4>Snapshot on ${snapshot.dateCreated}</h4>
      <table class="table table-borderless table-condensed">
        <tbody>
          <tr>
            <td colspan="4"><h5 class="muted">Core Attributes</h5></td>
          </tr>
          <tr>
            <th>Attribute</th>
            <th>Supplied Value</th>
            <th>Status</th>
          </tr>
          <g:each in="${snapshot.coreAttributes.sort{it}}" var="attr">
            <g:render template="/templates/attribute" model="[snapshot:snapshot, field:attr]" />
          </g:each>

          <tr>
            <td colspan="4"><h5 class="muted">Optional Attributes <small><em>Not supplying optional attributes is valid</em></small></h5></td>
          </tr>
          <tr>
            <th>Attribute</th>
            <th>Supplied Value</th>
            <th>Status</th>
          </tr>
          <g:each in="${snapshot.optionalAttributes.sort{it}}" var="attr">
            <g:render template="/templates/attribute" model="[snapshot:snapshot, field:attr]" />
          </g:each>
        </tbody>
      </table>

      <hr>
    </g:each>

  </body>
</html>
