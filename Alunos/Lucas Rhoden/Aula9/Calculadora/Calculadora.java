package Calculadora;

import java.awt.*;
import javax.swing.*;

class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

public class Calculadora extends JFrame {
    private JTextField campoNumero1, campoNumero2;
    private JLabel resultado;

    public Calculadora() {
        setTitle("Calculadora Avançada");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painelEntrada = new JPanel(new GridLayout(2, 2, 5, 5));
        campoNumero1 = new JTextField();
        campoNumero2 = new JTextField();

        painelEntrada.add(new JLabel("Número 1:", JLabel.CENTER));
        painelEntrada.add(campoNumero1);
        painelEntrada.add(new JLabel("Número 2:", JLabel.CENTER));
        painelEntrada.add(campoNumero2);

        JPanel painelBotoes = new JPanel(new GridLayout(2, 2, 5, 5));

        JButton botaoSoma = criarBotao("+");
        JButton botaoSubtracao = criarBotao("-");
        JButton botaoMultiplicacao = criarBotao("×");
        JButton botaoDivisao = criarBotao("÷");

        botaoSoma.addActionListener(e -> calcular("+"));
        botaoSubtracao.addActionListener(e -> calcular("-"));
        botaoMultiplicacao.addActionListener(e -> calcular("×"));
        botaoDivisao.addActionListener(e -> calcular("÷"));

        painelBotoes.add(botaoSoma);
        painelBotoes.add(botaoSubtracao);
        painelBotoes.add(botaoMultiplicacao);
        painelBotoes.add(botaoDivisao);

        resultado = new JLabel("Resultado: ", JLabel.CENTER);
        resultado.setFont(new Font("Arial", Font.BOLD, 16));
        resultado.setForeground(Color.BLUE);

        add(painelEntrada, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.CENTER);
        add(resultado, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 18));
        botao.setBackground(new Color(50, 150, 250));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        return botao;
    }

private void calcular(String operacao) {
    try {
        double num1 = validarEntrada(campoNumero1.getText());
        double num2 = validarEntrada(campoNumero2.getText());
        double res = 0;

        switch (operacao) {
            case "+":
                res = num1 + num2;
                break;
            case "-":
                res = num1 - num2;
                break;
            case "×":
                res = num1 * num2;
                break;
            case "÷":
                if (num2 == 0)
                    throw new InvalidInputException("Erro: Divisão por zero não permitida.");
                res = num1 / num2;
                break;
            default:
                throw new InvalidInputException("Operação inválida.");
        }

        resultado.setText("Resultado: " + res);
    } catch (InvalidInputException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
}

    private double validarEntrada(String entrada) throws InvalidInputException {
        try {
            return Double.parseDouble(entrada);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Entrada inválida! Digite apenas números.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculadora::new);
    }
}