import org.slf4j.LoggerFactory
//import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import jakarta.servlet.*
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpSession

@Component
class SessionLoggingFilter : Filter {
    private val logger = LoggerFactory.getLogger(SessionLoggingFilter::class.java)

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        val session: HttpSession? = httpRequest.getSession(false)
//        val auth = SecurityContextHolder.getContext().authentication

        logger.info("Session ID: {}", session?.id)
//        logger.info("Is Authenticated: {}", auth?.isAuthenticated)
//        logger.info("Principal: {}", auth?.principal)

        chain.doFilter(request, response)
    }
}