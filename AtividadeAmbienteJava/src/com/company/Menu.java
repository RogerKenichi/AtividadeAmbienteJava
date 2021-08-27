package com.company;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Menu {

    static int maiorID;

    static void exibeMenu()
    {
        System.out.println("1- Adicionar nova tarefa");
        System.out.println("2- Listar todas as tarefas");
        System.out.println("3- Listar tarefas por classificação");
        System.out.println("4- Remover Tarefa");
        System.out.println("5- Sair");
        System.out.println();
    }

    static int lerReposta(){
        Scanner sc = new Scanner(System.in);
        int resposta;

        do{
            System.out.println("Escolha uma opção: ");
            resposta = tryParseInt(sc.nextLine().trim(), -1);
        }while (resposta == -1);

        System.out.println();
        return resposta;
    }

    static void adicionarTarefa(){

        SalvamentoJson json = new SalvamentoJson(SalvamentoJson.listaTarefas,SalvamentoJson.listaClassificacao);

        SalvamentoJson.listaTarefas.add(criarTarefa());
        json.salvarJsonTarefas();
    }

    static Tarefa criarTarefa(){

        Scanner sc = new Scanner(System.in);
        Tarefa tarefa = new Tarefa();

        tarefa.setId(++Menu.maiorID);

        System.out.println("\nOque deve ser feito?");
        tarefa.setNome(sc.nextLine().trim());

        System.out.println("Algum detalhe?");
        tarefa.setDetalhes(sc.nextLine().trim());

        boolean dataValida;
        String data;
        do{
            dataValida = true;
            System.out.println("Qual o prazo para finalização da tarefa?");
            data = sc.nextLine().trim();
            if(validaData(data))
                tarefa.setData(data);
            else{
                dataValida = false;
                System.out.println("Formata Inválido!");
            }

        }while(!dataValida);

        int resposta;

        do{
            listarClassificações();
            resposta = tryParseInt(sc.nextLine().trim(), -1);

        }while (resposta == -1);

        tarefa.setClassificação(resposta);

        return  tarefa;
    }

    static void listarClassificações(){
        int i = 0;
        System.out.println("Escolha uma classificação: ");
        System.out.println();
        for(Classificacao classificacao : SalvamentoJson.listaClassificacao){
            i++;
            System.out.println(classificacao.getId() + " - " + classificacao.getNomeClassificacao());
        }
    }

    static void listarTodasTarefas(){
        for(Tarefa tarefa : SalvamentoJson.listaTarefas){
            System.out.println(tarefa.getId() + " - " +tarefa.getNome() + " - " + tarefa.getDetalhes() + " - " + tarefa.getData());
        }

        System.out.println();
    }

    static void listarTarefasPorClassificacao(){
        Scanner sc = new Scanner(System.in);
        int idClassificacao;

        do{
            listarClassificações();
            idClassificacao = tryParseInt(sc.nextLine().trim(), -1);

        }while (idClassificacao == -1);

        for(Tarefa tarefa : SalvamentoJson.listaTarefas){
            if(tarefa.getClassificação() == idClassificacao)
                System.out.println(tarefa.getId() + " - " +tarefa.getNome() + " - " + tarefa.getDetalhes() + " - " + tarefa.getData());
        }

        System.out.println();
    }

    static void removerTarefa(){
        Scanner sc = new Scanner(System.in);

        int idTarefa;

        do{
            listarTarefasPorClassificacao();
            System.out.println("Qual tarefa você quer remover?");
            idTarefa = tryParseInt(sc.nextLine().trim(), -1);

        }while (idTarefa == -1);

        for (Tarefa tarefa: SalvamentoJson.listaTarefas) {
            if(tarefa.getId() == idTarefa){
                SalvamentoJson.listaTarefas.remove(tarefa);
                break;
            }
        }

        SalvamentoJson json = new SalvamentoJson(SalvamentoJson.listaTarefas,SalvamentoJson.listaClassificacao);
        json.salvarJsonTarefas();

    }


    //TryParseInt igual do C#
    static int tryParseInt(String value, int defaultVal) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultVal;
        }

    }

    //TryParseData
    static Boolean validaData(String value) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            Date date = df.parse(value);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
