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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_Nota")
public class NotaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_nota")
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date data;

	@ManyToOne
	@JoinColumn(name = "ID_comprador", nullable = false)
	private PessoaEntity comprador;

	@ManyToOne
	@JoinColumn(name = "ID_vendedor", nullable = false)
	private PessoaEntity vendedor;

	@OneToMany(mappedBy = "idNotaMoeda.idNota", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<NotaMoedaEntity> notaMoeda;

	@Column(nullable = false, name = "valor_total")
	private double valorTotal;

}
