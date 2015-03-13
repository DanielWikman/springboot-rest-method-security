package net.wikmans.srmsexample.repository;

import net.wikmans.srmsexample.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by Daniel on 2015-03-13.
 * Repository with complex security setup.
 */
@SuppressWarnings("UnusedDeclaration")
@PreAuthorize("hasRole('ROLE_USER')")
public interface ItemRepository extends CrudRepository<Item, Long> {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    <S extends Item> S save(S s);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    void delete(Long aLong);
}