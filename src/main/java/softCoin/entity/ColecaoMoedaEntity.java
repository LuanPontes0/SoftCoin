package softCoin.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Table(name = "TB_colecao_moeda")
@Entity
public class ColecaoMoedaEntity {

	@EmbeddedId
	private ColecaoMoedaIdEntity idColecaoMoeda;

	private int quantidade;

	private float valorUnitario;

	boolean disponivelVenda;
}
