import org.springframework.security.acls.domain.DefaultPermissionFactory
import de.guthe.sven.beerpong.tournamentplaner.configuration.CustomPermission

beans = {
    aclPermissionFactory(DefaultPermissionFactory, CustomPermission)
}