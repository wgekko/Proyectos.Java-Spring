package walter.blockNotas.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;


@Entity
@Data
@Table(name="notas")
public class NotaModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @NotEmpty
    @Column(name="titulo", nullable=false)
    private String titulo;
    @NotEmpty
    @Column(name="referencia", nullable=false)
    private String referencia;
    @NotEmpty
    @Column(name="nota", nullable=false, columnDefinition="TEXT")
    private String nota;
    @NotEmpty
    @Column(name="created_at")
    private LocalDateTime createdAt = LocalDateTime.of(LocalDate.now(), LocalTime.now());
    @NotEmpty
    @Column(name="updated_at")
    private LocalDateTime updatedAt = LocalDateTime.of(LocalDate.now(), LocalTime.now());
    
}