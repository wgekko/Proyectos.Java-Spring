package bravi.springweb.models;

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
@Table(name="apuntes")
public class ApunteModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @NotEmpty
    @Column(name="carrera", nullable=false)
    private String carrera;
    @NotEmpty
    @Column(name="materia", nullable=false)
    private String materia;
    @NotEmpty
    @Column(name="apunte", nullable=false, columnDefinition="TEXT")
    private String apunte;
    @NotEmpty
    @Column(name="created_at")
    private LocalDateTime createdAt = LocalDateTime.of(LocalDate.now(), LocalTime.now());
    @NotEmpty
    @Column(name="updated_at")
    private LocalDateTime updatedAt = LocalDateTime.of(LocalDate.now(), LocalTime.now());
    
}