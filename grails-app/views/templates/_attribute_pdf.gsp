<tr>
  <td>${field}</td>
  <td style="width:75px;">
    <g:if test="${snapshot.errors.hasFieldErrors(field)}">
      <span class="alert alert-error">invalid</span>
    </g:if>
    <g:else>
      <span>valid</span>
    </g:else>
  </td>
  <td>
    <small class="attr">${fieldValue(bean: snapshot, field: field) ?:"<em>Not Supplied</em>"}</small>
  </td>
</tr>
