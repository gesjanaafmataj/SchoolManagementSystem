package gesjana.ikub.repository;

import gesjana.ikub.model.ReportCardDetail;
import org.springframework.data.repository.CrudRepository;

// TODO: Auto-generated Javadoc
/**
 * The Interface ReportCardDetailRepository.
 */
public interface ReportCardDetailRepository extends CrudRepository<ReportCardDetail, Integer> {
	
	/**
	 * Find by report card id.
	 *
	 * @param id the id
	 * @return the iterable
	 */
	Iterable<ReportCardDetail> findByReportCardId(Integer id);
}
