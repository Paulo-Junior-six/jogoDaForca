package jogo_da_forca;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class JogoDaForca 
  {
    public static void main(String[] args) {
        Map<String, String[]> categorias = new HashMap<>();
        categorias.put("Linguagem de Programação", new String[]{"JAVA", "PYTHON", "C++", "JAVASCRIPT", "SWIFT", "VISUAL BASIC"});
        categorias.put("Animal", new String[]{"ELEFANTE", "GIRAFA", "CACHORRO", "TIGRE"});
        categorias.put("País", new String[]{"BRASIL", "CANADA", "INDIA", "JAPAO"});
        categorias.put("Instrumento Musical", new String[]{"VIOLAO", "ABACO", "BATERIA", "PIANO", "FLAUTA", "GUITARRA"});
        categorias.put("Carro", new String[]{"BRASILIA", "ASTRA", "OMEGA", "OPALA", "CARAVAN", "SUPRA", "SKALINE"});
        categorias.put("Elemento Químico", new String[]{"BISMUTO", "HIDROGENIO", "FERRO", "PLUTONIO", "RUTHERFORDIO", "URANIO"});
        categorias.put("Séries", new String[]{"THE WALKING DEAD", "BREAKING BAD", "VIKINGS", "RAGNAROK"});

        Random random = new Random();
        Object[] temas = categorias.keySet().toArray();
        String tema = (String) temas[random.nextInt(temas.length)];
        String[] palavrasDoTema = categorias.get(tema);
        String palavraSecreta = palavrasDoTema[random.nextInt(palavrasDoTema.length)];

        int tentativasRestantes = 10;
        HashSet<Character> letrasUsadas = new HashSet<>();
        StringBuilder palavraOculta = new StringBuilder("_".repeat(palavraSecreta.length()));
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Jogo da Forca!");
        System.out.println("Tema: " + tema);
        System.out.println("A palavra tem " + palavraSecreta.length() + " letras.");
        System.out.println("Você tem " + tentativasRestantes + " tentativas.");

        while (tentativasRestantes > 0 && palavraOculta.toString().contains("_")) {
            System.out.println("\nPalavra: " + palavraOculta);
            System.out.println("Letras usadas: " + letrasUsadas);
            System.out.print("Digite uma letra: ");
            char letra = scanner.nextLine().toUpperCase().charAt(0);

            if (letrasUsadas.contains(letra)) {
                System.out.println("Você já usou essa letra. Tente outra.");
                continue;
            }

            letrasUsadas.add(letra);

            if (palavraSecreta.indexOf(letra) >= 0) {
                System.out.println("Boa! A letra está na palavra.");
                for (int i = 0; i < palavraSecreta.length(); i++) {
                    if (palavraSecreta.charAt(i) == letra) {
                        palavraOculta.setCharAt(i, letra);
                    }
                }
            } else {
                tentativasRestantes--;
                System.out.println("A letra não está na palavra. Tentativas restantes: " + tentativasRestantes);
                desenharForca(10 - tentativasRestantes);
            }
        }

        if (palavraOculta.toString().equals(palavraSecreta)) {
            System.out.println("\nParabéns! Você adivinhou a palavra: " + palavraSecreta);
        } else {
            System.out.println("\nVocê perdeu! A palavra era: " + palavraSecreta);
        }

        scanner.close();
    }

    public static void desenharForca(int erros) {
        System.out.println(" -------");
        System.out.println(" |     |");
        System.out.println((erros > 0 ? " O" : " ") + "     |");              
        System.out.println((erros > 1 ? " |" : " ") + "     |");             
        System.out.println((erros > 2 ? "/" : " ") + (erros > 3 ? "|" : " ") + (erros > 4 ? "\\" : " ") + "     |"); 
        System.out.println((erros > 5 ? " |" : " ") + "     |");             
        System.out.println((erros > 6 ? "/" : " ") + (erros > 7 ? " " : "") + (erros > 8 ? "\\" : " ") + "     |"); 
        System.out.println("       |");
        System.out.println("---------");
    }
}
