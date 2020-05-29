package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Estacionamento {

    private List<Carro> carros = new ArrayList<Carro>();

    private Carro getCarroRemover() {
        Stream<Carro> carroMotoristaNaoIdoso = carros.stream().filter(carro -> !carro.isMotoristaIdoso());
        return carroMotoristaNaoIdoso.findFirst().orElse(null);
    }

    public boolean isLotado() {
        return 10 == this.carrosEstacionados();
    }

    public void estacionar(Carro carro) {
        if (isLotado()) {
            Carro carroRemover = getCarroRemover();
            if (null == carroRemover) {
                throw new EstacionamentoException("Estacionamento cheio");
            }

            carros.remove(carroRemover);
        }

        if (null == carro.getMotorista()) {
            throw new EstacionamentoException("Apenas carros com motoristas");
        }

        if (20 < carro.getMotorista().getPontos()) {
            throw new EstacionamentoException("Apenas motoristas com pontos na carteira.");
        }

        if (18 > carro.getMotorista().getIdade()) {
            throw new EstacionamentoException("Apenas motoristas maiores de idade");
        }

        carros.add(carro);
    }

    public int carrosEstacionados() {
        return carros.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return carros.contains(carro);
    }
}
