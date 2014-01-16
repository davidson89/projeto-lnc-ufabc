import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;

public class Main {

	public static void main(String[] args) throws Exception {
		// Load from 'FCL' file
		String fileName = "lib/fcl/SistemaBombeio.fcl";
		// System.out.println(System.getProperty("user.dir"));
		FIS fis = FIS.load(fileName, true);

		if (fis == null) { // Error while loading?
			System.err.println("Can't load file: '" + fileName + "'");
			return;
		}

		// Show variables
		FunctionBlock functionBlock = fis.getFunctionBlock(null);
		functionBlock.chart();

		// Set inputs
		/*
		 * functionBlock.setVariable("EV", 0.5); // Eficiência Volumétrica [0..1] functionBlock.setVariable("NAV", 1.1);
		 * // Nível de Atendimento de Vazão [0.95..1.5] functionBlock.setVariable("UH", 0.5); // Grau de Utilização de
		 * Hastes [0..1] functionBlock.setVariable("CH", 1.2); // Custo da Coluna de Hastes [0.3..2.0]
		 * functionBlock.setVariable("PT", 0.5); // Peak de Torque [0..1] functionBlock.setVariable("FB", 15); //
		 * Frequência de Bombeio [0..25] functionBlock.setVariable("CMax", 0.4); // Carga Máxima [0..1]
		 * functionBlock.setVariable("CMin", 0.3); // Carga Mínima [0..1]
		 */

		functionBlock.setVariable("EV", 1); // Eficiência Volumétrica [0..1]
		functionBlock.setVariable("NAV", 1.0); // Nível de Atendimento de Vazão [0.95..1.5]
		functionBlock.setVariable("UH", 0); // Grau de Utilização de Hastes [0..1]
		functionBlock.setVariable("CH", 0.3); // Custo da Coluna de Hastes [0.3..2.0]
		functionBlock.setVariable("PT", 0.0); // Peak de Torque [0..1]
		functionBlock.setVariable("FB", 1); // Frequência de Bombeio [0..25]
		functionBlock.setVariable("CMax", 0.5); // Carga Máxima [0..1]
		functionBlock.setVariable("CMin", 1); // Carga Mínima [0..1]

		// Evaluate
		functionBlock.evaluate();

		// Show the accumulation method�s result: combining of the consequentes
		functionBlock.getVariable("CDH").chartDefuzzifier(true);

		// Print output's defuzzifier
		System.out.println("Coluna de Hastes:" + functionBlock.getVariable("CDH").getValue());
		System.out.println("Bomba de Fundo:" + functionBlock.getVariable("BF").getValue());
		System.out.println("Bombeio:" + functionBlock.getVariable("UB").getValue());
		System.out.println("Sistema de Bombeio:" + functionBlock.getVariable("SB").getValue());
		// OR
		// System.out.println("TIP:" + fis.getVariable("tip").getLatestDefuzzifiedValue());
	}
}
