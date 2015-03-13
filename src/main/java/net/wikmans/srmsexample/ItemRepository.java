package net.wikmans.srmsexample;

import net.wikmans.srmsexample.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by Daniel on 2015-03-13.
 */
@RepositoryRestResource
@PreAuthorize("hasRole('ROLE_USER')")
public interface ItemRepository extends CrudRepository<Item, Long> {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    <S extends Item> S save(S s);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(Long aLong);
}