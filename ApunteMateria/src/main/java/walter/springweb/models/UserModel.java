package bravi.springweb.models;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name="usuarios")
public class UserModel implements Serializable {
    
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty
    @NotNull
    @Column(name="usuario", unique=true)
    private String usuario;
    
    @Column(name="password")
    @NotEmpty
    @NotNull
    private String password;
    
    @NotEmpty
    @NotNull
    @Column(name="email", unique=true)
    private String email;
    
}