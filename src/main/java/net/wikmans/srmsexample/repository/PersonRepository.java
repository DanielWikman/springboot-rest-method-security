package net.wikmans.srmsexample.repository;

import net.wikmans.srmsexample.domain.Person;
import org.springframework.data.repository.CrudRepository;


/**
 * Created by Daniel on 2015-03-13.
 * Simple Repository for Persons.
 */
@SuppressWarnings({"UnusedDeclaration"})
public interface PersonRepository extends CrudRepository<Person, Long> {

}
