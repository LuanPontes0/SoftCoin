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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "TB_Moeda")
public class MoedaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_moeda")
	private long id;

	@Column(name = "titulo", nullable = false, unique = true, length = 100)
	private String titulo;

	@Column(name = "ano_face", nullable = false)
	private int anoFace;

	@Column(name = "valor_face", nullable = false)
	private double valorFace;

	@ManyToMany
	@JoinTable(name = "TB_moeda_borda", joinColumns = @JoinColumn(name = "ID_moeda", nullable = false), inverseJoinColumns = @JoinColumn(name = "ID_borda", nullable = false))
	private List<BordaEntity> bordas;

	@ManyToMany
	@JoinTable(name = "TB_moeda_material", joinColumns = @JoinColumn(name = "ID_moeda", nullable = false), inverseJoinColumns = @JoinColumn(name = "ID_material", nullable = false))
	private List<MaterialEntity> materiais;

	@Column(name = "cod_catalogo", length = 50, nullable = false, unique = true)
	private String codCatalogo;

	@ManyToOne
	@JoinColumn(name = "id_pais", nullable = false)
	private PaisEntity paisOrigem;

	@Column(name = "peso", nullable = false)
	private double peso;

	@Column(name = "espessura", nullable = false)
	private double espessura;

	@Column(name = "diametro", nullable = false)
	private double diametro;

	@OneToMany(mappedBy = "idColecaoMoeda.idMoeda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ColecaoMoedaEntity> colecaoMoeda;

	@OneToMany(mappedBy = "idNotaMoeda.idMoeda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<NotaMoedaEntity> notaMoeda;
}
