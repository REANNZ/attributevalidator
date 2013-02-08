<tr>
  <td>${field}</td>
  <td>${fieldValue(bean: snapshot, field: field) ?:"<em>Not Supplied</em>"}</td>
  <td>
    <g:if test="${snapshot.errors.hasFieldErrors(field)}">
      <span class="badge badge-important"><i class="icon-white icon-remove"></i></span>
    </g:if>
    <g:else>
      <span class="badge badge-success"><i class="icon-white icon-ok"></i></span>
    </g:else>
  </td>
  <g:if test="${referenceURL}">
    <td>
      <p><a href="${referenceURL}">${referenceURL}</a></p>
    </td>
  </g:if>
</tr>
