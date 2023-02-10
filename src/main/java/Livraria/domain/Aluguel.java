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
@Table(name="aluguel")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Aluguel {
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
    @Column(name="data_devolucao")
    private Date dataDevolucao;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_at")
    private Date createAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_at")
    private Date updateAt;
}
