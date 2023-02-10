package Livraria.domain;

import Livraria.repository.UsuarioRepository;
import Livraria.request.UsuarioDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name="vendas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vendas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "cliente_id",nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name="livro_id",nullable = false)
    private Livro livro;
    @Column(name = "preco")
    private double preco;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_at")
    private Date createAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_at")
    private Date updateAt;


}
