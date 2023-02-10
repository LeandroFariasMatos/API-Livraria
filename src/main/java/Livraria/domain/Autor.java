package Livraria.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="autor")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Autor {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @Column(name="nome")
    private String nomeAutor;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_at")
    private Date createAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_at")
    private Date updateAt;

}
