
<!doctype html>
<html>
  <head>
    <meta name="layout" content="internal" />
  </head>
  <body>

    <h2>Accounts which have accessed attribute validator</h2>
    <p>Default view shows most recent login first. Click table headings to sort.</p>

    <table class="table table-borderless table-sortable">
      <thead>
        <tr>
          <th>Principal</th>
          <th>Common Name</th>
          <th>Email</th>
          <th/>
        </tr>
      </thead>
      <tbody>
        <g:each in="${subjects}" var="subject">
          <tr>
            <td>${subject.principal}</td>
            <td>${subject.cn}</td>
            <td>${subject.email}</td>
            <td><g:link action="historicalsubject" id="${subject.id}" class="btn">View</g:link></td>
          </tr>
        </g:each>
      </tbody>
    </table>

  </body>
</html>
