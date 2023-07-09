package softCoin.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "TB_nota_moeda")
@Entity
public class NotaMoedaEntity {

	@EmbeddedId
	private NotaMoedaID idNotaMoeda;

	private int quantidade;

	private double valorUnitario;
}
