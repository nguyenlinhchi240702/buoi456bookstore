package nguyenthilinhchi.buoi456.entity;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
@Data
@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    @NotEmpty(message = "Title must not empty")
    private String name;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Book> book;
}