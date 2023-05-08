package gesjana.ikub.repository;

import org.springframework.data.repository.CrudRepository;

import gesjana.ikub.model.Class;


/**
 * The Interface ClassRepository.
 */
public interface ClassRepository extends CrudRepository<Class, Integer> {
	
	/**
	 * Find by school year id.
	 *
	 * @param id the id
	 * @return the iterable
	 */
	Iterable<Class> findBySchoolYearId(Integer id);
	
	/**
	 * Find by name containing.
	 *
	 * @param name the name
	 * @return the iterable
	 */
	Iterable<Class> findByNameContaining(String name);
}
