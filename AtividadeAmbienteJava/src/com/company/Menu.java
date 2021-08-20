package com.company;

import java.util.Scanner;

public class Menu {

    static void exibeMenu()
    {
        System.out.println("1- Adicionar nova tarefa");
        System.out.println("2- Sair");
    }

    static int LerReposta(){
        Scanner sc = new Scanner(System.in);
        int resposta;

        do{
            System.out.println("Escolha uma opção: ");
            resposta = tryParseInt(sc.nextLine().trim(), -1);
        }while (resposta == -1);

        return resposta;
    }

    //TryParseInt igual do C#
    static int tryParseInt(String value, int defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

}
