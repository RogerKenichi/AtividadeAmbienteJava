package com.company;

import com.google.gson.Gson;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.JSONFunctions;


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

    static Tarefa adicionarTarefa(){
        Scanner sc = new Scanner(System.in);
        Tarefa tarefa = new Tarefa();

        System.out.println("Qual o nome da tarefa?");
        tarefa.nome = sc.nextLine().trim();

        System.out.println("O que deve ser feito?");
        tarefa.detalhes = sc.nextLine().trim();

        System.out.println("Qual a data limite para finalizar a tarefa?");
        tarefa.data = sc.nextLine().trim();

        try {
            System.out.println("A qual classificação pertence a tarefa?");
            tarefa.classificação = tryParseInt(sc.nextLine().trim(), -1);
        }catch(Exception e){
            return null;
        }

        return tarefa;
    }

    static void criarJSON() {
        Gson gson = new Gson();

        String json = gson.toJson(adicionarTarefa());

        System.out.println(json);
        
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
