package net.wikmans.srmsexample;

import net.wikmans.srmsexample.domain.Person;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by Daniel on 2015-03-13.
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

}
