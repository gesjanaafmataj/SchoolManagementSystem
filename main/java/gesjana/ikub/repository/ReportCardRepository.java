package gesjana.ikub.repository;

import gesjana.ikub.model.ReportCard;
import org.springframework.data.repository.CrudRepository;

// TODO: Auto-generated Javadoc
/**
 * The Interface ReportCardRepository.
 */
public interface ReportCardRepository extends CrudRepository<ReportCard, Integer> {
	
	/**
	 * Find by registration id.
	 *
	 * @param id the id
	 * @return the report card
	 */
	ReportCard findByRegistrationId(Integer id);
}
