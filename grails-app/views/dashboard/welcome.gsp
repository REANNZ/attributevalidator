<!doctype html>
<html>
  <head>
    <meta name="layout" content="public" />
  </head>
  <body>

    <div class="row">
      <div class="span12">
        <div class="hero-unit">
            <h2>Attribute Validator</h2>
            <p>
              This service will validate the atrributes provided about you from your identity provider ensuring they are provided in a format that is suitable for consumption by AAF connected services.
            </p>
            <p>
              <g:link controller="snapshot" action="index" class="btn btn-large btn-info"><i class="icon-white icon-user"></i> Show me my attributes</g:link>
            </p>
        </div>

          <div class="alert alert-info">
            <h5>Identity Provider Administrators</h5> 
            <p>
              If you're the administrator of an Identity Provider and <strong>this is the first time</strong> you've accessed this validator please ensure you've <a href="http://support.aaf.edu.au/entries/22545567-Automating-Attribute-Release">configured automated attribute release</a> or correctly setup manual attribute release policies for your users before continuing to ensure you don't have connectivity problems with services such as this.
            </p>
            <p><g:message code="branding.application.supportdesk"/></p>
          </div>
      </div>
    </div>

  </body>
</html>
