package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {
        return apply(texto, 3);
    }

    @Override
    public String descriptografar(String texto) {
        return apply(texto, -3);
    }

    private String apply(String texto, Integer fator) {
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("Texto nao pode ser vazio.");
        }

        StringBuilder encriptedText = new StringBuilder();
        String textoLower = texto.toLowerCase();

        for (int i=0; i < textoLower.length(); i++) {
            char caracter = textoLower.charAt(i);
            encriptedText.append(
                applyToChar(caracter, fator)
            );
        }

        return encriptedText.toString();
    }

    private char applyToChar(Character caracter, int fator) {
        if (!Character.isLetter(caracter)) {
            return caracter;
        }

        int asciiCaracter = caracter;

        int asciiCharStartsIn = 97;
        int minValue = 97;
        int maxValue = 122;

        int novoChar = (asciiCaracter-asciiCharStartsIn+fator);

        if (0 > novoChar) {
            novoChar += maxValue;
        } else if (novoChar < minValue) {
            novoChar += minValue;
        }

        return (char)novoChar;
    }
}
