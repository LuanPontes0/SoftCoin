package softCoin.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_Pessoa")
public class PessoaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_pessoa")
	private int id;

	@Column(name = "nome_completo", length = 70, nullable = false)
	private String nomeCompleto;

	@Column(name = "cpf", length = 11, nullable = false, unique = true)
	private String cpf;

	@Column(name = "data_nascimento", nullable = false, columnDefinition = "DATE")
	private Date dataNascimento;

	@Column(nullable = false, unique = true, length = 200)
	private String email;

	@Column(length = 8)
	private String senha;

	@Column(length = 11, nullable = false)
	private String telefone;

	private boolean adm;

	@OneToMany(mappedBy = "dono", cascade = CascadeType.ALL)
	List<ColecaoEntity> colecoes;

	@ToString.Exclude
	@OneToMany(mappedBy = "comprador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<NotaEntity> compras;

	@ToString.Exclude
	@OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<NotaEntity> vendas;

}
