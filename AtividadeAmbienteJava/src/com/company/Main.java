package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Tarefa> tarefas = new ArrayList<Tarefa>();
    private static List<Classificacao> classificacoes = new ArrayList<Classificacao>();

    public static void main(String[] args) {
        int resp = 0;
        do{
            Menu.exibeMenu();
            resp = Menu.LerReposta();

            switch (resp){
                case 1:
                    System.out.println("Teste 1");
                    break;
                case 2:
                    System.out.println("Teste 2");
                    break;
            }
        }while(resp != 2);



        // ------------------------------ Teste de demonstração ---------------------------
        // Objeto tarefa
        Tarefa tarefa = new Tarefa();
        tarefa.setId(2);
        tarefa.setNome("teste1");
        tarefa.setData("12/04/2021");
        tarefa.setClassificação(0);
        tarefa.setDetalhes("Tem que fazer algo");

        // adicionando tarefas a lista tarefas
        tarefas.add(tarefa);
        tarefas.add(tarefa);
        tarefas.add(tarefa);

        // Objeto classificação
        Classificacao cla = new Classificacao();
        cla.setId(5);
        cla.setNomeClassificacao("domestico");

        // adicionando classificações a lista classificações
        classificacoes.add(cla);
        classificacoes.add(cla);

        // Salvando o json
        SalvamentoJson salvar = new SalvamentoJson(tarefas, classificacoes);
        salvar.salvarJsonTarefas();

        // Lendo o json
        salvar.lerJsonTarefas();

        // Carregando os valores lidos para as listas de Main()
        classificacoes = salvar.getClassificacao();
        tarefas = salvar.getTarefas();

        // Printando alguns valores para mostrar que funciona
        System.out.println(classificacoes.get(1).getNomeClassificacao());
        System.out.println(tarefas.get(1).getNome());
        // ------------------------------------------------------------------------
    }
}
