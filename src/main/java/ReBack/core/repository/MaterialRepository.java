package ReBack.core.repository;

import ReBack.core.data.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    Material findByMaterialName(String materialName);
}