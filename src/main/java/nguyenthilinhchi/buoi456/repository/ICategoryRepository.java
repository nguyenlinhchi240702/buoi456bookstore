package nguyenthilinhchi.buoi456.repository;

import nguyenthilinhchi.buoi456.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {
}
