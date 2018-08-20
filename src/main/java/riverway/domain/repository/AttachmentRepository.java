package riverway.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import riverway.domain.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long>{
}
