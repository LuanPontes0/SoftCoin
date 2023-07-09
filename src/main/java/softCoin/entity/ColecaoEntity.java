package softCoin.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TB_Colecao")
public class ColecaoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_colecao")
	private long id;

	@OneToMany(mappedBy = "idColecaoMoeda.idColecao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ColecaoMoedaEntity> colecaoMoeda;

	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "ID_pessoa", nullable = false)
	private PessoaEntity dono;

	private String tituloColecao;

	@Override
	public String toString() {
		return "" + tituloColecao + "";
	}

}
