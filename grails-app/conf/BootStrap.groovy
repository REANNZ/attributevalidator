import org.apache.commons.lang.StringUtils

class BootStrap {

    def init = { servletContext ->
      String.mixin StringUtils
    }
    def destroy = {
    }
}
