package ec.espe.edu.sft.ttpf.Util;

public class ValidacionCrearUsuarioCliente {

    public static boolean validarCedula(String cedula) {
        if (cedula == null || cedula.length() != 10) {
            return false;
        }

        try {
            Long.parseLong(cedula);
        } catch (NumberFormatException numberFormatException) {
            return false;
        }

        byte codigoVerificador = (byte) Character.getNumericValue(cedula.charAt(9));
        byte acumulador = 0;

        for (int i = 0; i < 9; i++) {
            byte digito = (byte) Character.getNumericValue(cedula.charAt(i));

            if (i % 2 == 0) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }
            acumulador += digito;
        }

        byte codigoVerificardorCalculado = (byte) (acumulador % 10);

        if (codigoVerificardorCalculado != 0) {
            codigoVerificardorCalculado = (byte) (10 - codigoVerificardorCalculado);
        }

        return codigoVerificardorCalculado == codigoVerificador;
    }
}


