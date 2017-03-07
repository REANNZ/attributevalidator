<!doctype html>
<html>
  <head>
    <meta name="layout" content="internal"/>
  </head>
  <body>

    <div class="row">
      <div class="span12">
        <div class="hero-unit">
          <h1>Invalid attribute detected</h1>
          <p>Sorry, we weren't able to verify your released attributes.</p>

          <p>An important attribute, <code>auEduPersonSharedToken</code> was not valid, which prevented us from recording a snapshot of your attributes.</p>

          <p>Please report this to your helpdesk as soon as possible.</p>

          <div class="alert alert-error">
            <p><strong>Technical information:</strong></p>

            <p>Presented token: 
              <g:if test="${auEduPersonSharedToken == null }">
                <em>Not Supplied</em>
              </g:if>
              <g:else>
                <code>${auEduPersonSharedToken}</code>
              </g:else>
            </p>

            <g:if test="${auEduPersonSharedToken != null }">
              <ul>
                <g:if test="${auEduPersonSharedToken.length() > 27}">
                  <li>Token value is too long</li>
                </g:if>

                <g:if test="${auEduPersonSharedToken.length() < 27}">
                  <li>Token value is too short</li>
                </g:if>

                <g:if test="${auEduPersonSharedToken.matches(/.{27}=/)}">
                  <li>Padding character has not been stripped</li>
                </g:if>

                <g:if test="${auEduPersonSharedToken.matches(/.*[\/\+].*/)}">
                  <li>
                    Token contains <code>+</code> and/or <code>/</code> which are not included in the
                    <strong><a href="http://www.ietf.org/rfc/rfc4648.txt">Base 64 Encoding with URL and Filename Safe Alphabet</a></strong>
                  </li>
                </g:if>

                <g:if test="${auEduPersonSharedToken.matches(/.*[^A-Za-z0-9_-].*/)}">
                  <li>
                    Token contains invalid characters;
                    the only valid characters are <code>A-Z</code>, <code>a-z</code>, <code>0-9</code>,
                    <code>_</code> (underline) and <code>-</code> (minus)
                  </li>
                </g:if>
              </ul>
            </g:if>

            <p>Please see the <a href="http://wiki.aaf.edu.au/tech-info/attributes/auedupersonsharedtoken">AAF Wiki</a> for information on token requirements.</p>
          </div>

          <p><g:message code="branding.application.supportdesk"/></p>
        </div>
      </div>
    </div>

  </body>
</html>
