package nguyenthilinhchi.buoi456.repository;


import nguyenthilinhchi.buoi456.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {
}
