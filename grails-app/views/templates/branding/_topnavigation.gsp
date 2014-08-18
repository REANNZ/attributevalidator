<div class="container">
  <div class="navbar">
    <div class="navbar-inner">

      <a class="btn btn-navbar btn-mini" data-toggle="collapse" data-target=".nav-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>

      <ul class="nav">
        <aaf:isLoggedIn>
          <li>
            <g:link controller="snapshot" action="index"><g:message code="branding.nav.snapshot" /></g:link>
          </li>
        </aaf:isLoggedIn>
        <aaf:isNotLoggedIn>
          <li>
            <g:link controller="dashboard" action="welcome"><g:message code="branding.nav.welcome" /></g:link>
          </li>
        </aaf:isNotLoggedIn>
      </ul>
      <ul class="nav pull-right">
        <aaf:isNotLoggedIn>
          <li>
            <g:link controller="auth"><g:message code="branding.nav.login"/></g:link>
          </li>
        </aaf:isNotLoggedIn>
      </ul>

      <div class="nav-collapse">
        <ul class="nav">

          <aaf:isAdministrator>
          <li>
            <g:link controller="snapshot" action="historical"><g:message code="branding.nav.snapshot.historical" /></g:link>
          </li>
          <li class="dropdown">
            <a class="dropdown-toggle" id="administration" role="button" data-toggle="dropdown" data-target="#" href="#">
              <g:message code="branding.nav.admin" />
            </a>
            <ul class="dropdown-menu" role="menu" aria-labelledby="administration">
              <li><g:link controller="adminDashboard" action="index"><g:message code="branding.nav.admin.dashboard" /></g:link></li>
              <li><g:link controller="subject"><g:message code="branding.nav.admin.subjects" /></g:link></li>
              <li><g:link controller="role"><g:message code="branding.nav.admin.roles" /></g:link></li>
              <li class="dropdown-submenu">
                <a tabindex="-1" href="#"><g:message code="branding.nav.admin.workflow" /> <i class="icon-chevron-right icon-white"></i></a>
                <ul class="dropdown-menu">
                  <li><g:link controller="workflowProcess" action="list"><g:message code="branding.nav.admin.workflow.process" /></g:link></li>
                  <li><g:link controller="workflowScript" action="list"><g:message code="branding.nav.admin.workflow.script" /></g:link></li>
                </ul>
              </li>
              <li><g:link controller="emailTemplate" action="list"><g:message code="branding.nav.admin.emailtemplate" /></g:link></li>
              <li><g:link controller="console" action="index" target="_blank"><g:message code="branding.nav.admin.console" /></g:link></li>
              <li><g:link controller="adminDashboard" action="environment"><g:message code="branding.nav.admin.environment" /></g:link></li>
            </ul>
          </li>
          </aaf:isAdministrator>

          <li><a href="https://tuakiri.ac.nz/confluence/display/Tuakiri/Support+Desk" target="_blank"><g:message code="branding.nav.support" /></a></li>
        </ul>

        <ul class="nav pull-right">
          <aaf:isLoggedIn>
            <li>
              <g:link controller="auth" action="logout"><g:message code="branding.nav.logout" /></g:link>
            </li>
          </aaf:isLoggedIn>
        </ul>
      </div>

    </div>
  </div>
</div>
