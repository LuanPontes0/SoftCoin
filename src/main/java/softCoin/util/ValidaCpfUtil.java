package softCoin.util;

public class ValidaCpfUtil {

	public static boolean verificarFormatoCPF(String cpf) {
		// Remover pontos e traços do CPF
		cpf = cpf.replaceAll("[^0-9]", "");

		// Verificar se o CPF possui 11 dígitos
		if (cpf.length() != 11) {
			return false;
		}

		// Verificar se todos os dígitos são iguais
		boolean todosDigitosIguais = cpf.matches("(\\d)\\1{10}");
		if (todosDigitosIguais) {
			return false;
		}

		// Validar o primeiro dígito verificador
		int soma = 0;
		for (int i = 0; i < 9; i++) {
			soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
		}
		int resto = (soma * 10) % 11;
		if (resto == 10) {
			resto = 0;
		}
		if (resto != Character.getNumericValue(cpf.charAt(9))) {
			return false;
		}

		// Validar o segundo dígito verificador
		soma = 0;
		for (int i = 0; i < 10; i++) {
			soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
		}
		resto = (soma * 10) % 11;
		if (resto == 10) {
			resto = 0;
		}
		if (resto != Character.getNumericValue(cpf.charAt(10))) {
			return false;
		}

		// CPF válido
		return true;
	}
}
