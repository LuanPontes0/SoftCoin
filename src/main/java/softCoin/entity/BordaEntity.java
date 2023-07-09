package softCoin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_Borda")
public class BordaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_Borda")
	private int id;
	@Column(name = "nome", nullable = false, length = 50, unique = true)
	private String tipoBorda;

	@Override
	public String toString() {
		return "" + tipoBorda + "";
	}

}
