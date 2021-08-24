package com.company;

import java.util.ArrayList;
import java.util.List;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SalvamentoJson {

    private String filepath = "./tarefas.json";
    static List<Tarefa> listaTarefas = new ArrayList<Tarefa>();
    static List<Classificacao> listaClassificacao = new ArrayList<Classificacao>();

    public List<Tarefa> getTarefas() { return this.listaTarefas; }
    public List<Classificacao> getClassificacao() { return this.listaClassificacao; }

    public  SalvamentoJson(List<Tarefa> tarefas, List<Classificacao> classificacoes)
    {
        this.listaTarefas = tarefas;
        this.listaClassificacao = classificacoes;
    }

    public void salvarJsonTarefas()
    {
        JSONObject salvamentoTarefas = new JSONObject();
        JSONArray tarefas = new JSONArray();
        JSONArray classificacoes = new JSONArray();

        // Adiciona as tarefas para o objeto tarefas do json
        for(Tarefa t : this.listaTarefas)
        {
            JSONObject objTarefa = new JSONObject();
            objTarefa.put("id", t.getId());
            objTarefa.put("nome", t.getNome());
            objTarefa.put("detalhes", t.getDetalhes());
            objTarefa.put("data", t.getData());
            objTarefa.put("classificacao", t.getClassificação());

            tarefas.add(objTarefa);
        }

        // Adiciona as classificações para o objeto classificacoes do json
        for(Classificacao c : this.listaClassificacao)
        {
            JSONObject objClassificacoes = new JSONObject();
            objClassificacoes.put("id", c.getId());
            objClassificacoes.put("nomeclassificacao", c.getNomeClassificacao());

            classificacoes.add(objClassificacoes);
        }

        // Cria o objeto tarefas e o objeto classificacoes
        salvamentoTarefas.put("tarefas", tarefas);
        salvamentoTarefas.put("classificacoes", classificacoes);

        // Salva o arquivo
        try (FileWriter file = new FileWriter(this.filepath)) {
            file.write(salvamentoTarefas.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lerJsonTarefas()
    {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(this.filepath))
        {
            this.listaTarefas.clear();
            this.listaClassificacao.clear();
            Menu.maiorID = 1;

            Object obj = jsonParser.parse(reader);

            // Lê o objeto tarefas
            JSONObject tarefas = (JSONObject) obj;
            JSONArray listaTarefas = (JSONArray) tarefas.get("tarefas");
            for (Object t : listaTarefas) {
                JSONObject tf = (JSONObject) t;
                Tarefa objTarefa = new Tarefa();

                objTarefa.setId(((Long) tf.get("id")).intValue());
                objTarefa.setNome((String) tf.get("nome"));
                objTarefa.setData((String) tf.get("data"));
                objTarefa.setDetalhes((String) tf.get("detalhes"));
                objTarefa.setClassificação(((Long) tf.get("classificacao")).intValue());

                if(((Long) tf.get("id")).intValue() > Menu.maiorID)
                    Menu.maiorID = ((Long) tf.get("id")).intValue();

                this.listaTarefas.add(objTarefa);
            }

            // Lê o objeto classificações
            JSONObject classificacoes = (JSONObject) obj;
            JSONArray listaClass = (JSONArray) classificacoes.get("classificacoes");
            for (Object t : listaClass) {
                JSONObject tf = (JSONObject) t;
                Classificacao objClassificacao = new Classificacao();

                objClassificacao.setId(((Long) tf.get("id")).intValue());
                objClassificacao.setNomeClassificacao((String) tf.get("nomeclassificacao"));

                this.listaClassificacao.add(objClassificacao);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
