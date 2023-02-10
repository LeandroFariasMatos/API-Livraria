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
@Table(name="livro")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Livro {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "nome")
    private String nomeLivro;
    @Column(name="genero_livro",nullable = false)
    private String generoLivro;
    @ManyToOne
    @JoinColumn(name="editora_id",nullable = false)
    private Editora editora;
    @ManyToOne
    @JoinColumn(name="autor_id",nullable = false)
    private Autor autor;
    @ManyToOne
    @JoinColumn(name="livraria_id",nullable = false)
    private Livraria livraria;
    @Column(name="preco_aluguel_por_dia")
    private double precoAluguelPorDia;
    @Column(name="preco_venda")
    private double precoVenda;
    @Column(name="status")
    private int status;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_at")
    private Date createAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_at")
    private Date updateAt;
}
