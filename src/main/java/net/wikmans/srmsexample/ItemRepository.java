package net.wikmans.srmsexample;

import net.wikmans.srmsexample.domain.Item;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Daniel on 2015-03-13.
 */
public interface ItemRepository extends CrudRepository<Item, Long> {

    @Override
    Item save(Item s);

    @Override
    void delete(Long aLong);
}