package net.wikmans.srmsexample.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * Created by Daniel on 2015-03-13.
 *
 * Only reason for having this class is to enable parsing of Pre/Post annotations in the Repositories.
 * The EnableGlobalMethodSecurity does not bite, if you put it in the security package, then the
 * classes in the repository package are not scanned...
 */
@SuppressWarnings({ "UnusedDeclaration","SpringFacetCodeInspection" })
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class RepositorySecurityConfiguration {
}
