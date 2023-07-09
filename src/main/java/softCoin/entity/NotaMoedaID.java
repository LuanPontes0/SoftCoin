package softCoin.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.ToString;

@Data
@Embeddable
public class NotaMoedaID implements Serializable {

	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nota", nullable = false, referencedColumnName = "ID_nota")
	private NotaEntity idNota;

	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_moeda", nullable = false, referencedColumnName = "ID_moeda")
	private MoedaEntity idMoeda;
	
}
