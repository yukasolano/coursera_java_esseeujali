package model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    public Usuario(String login, String nome, String senha) {
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.pontos = 0;
    }
    
    public Usuario(String login, String nome, String senha, Integer pontos) {
        this.login = login;
        this.nome = nome;
        this.senha = senha;
        this.pontos = pontos;
    }

    private String login = "";
    private String nome = "";
    private String senha = "";
    private Integer pontos = 0;
    private List<String> trofeus = new ArrayList<String>();

    public List<String> getTrofeus() {
        return trofeus;
    }

    public void setTrofeus(List<String> trofeus) {
        this.trofeus = trofeus;
    }

    public String getLogin() {
            return login;
    }

    public String getNome() {
            return nome;
    }

    public String getSenha() {
            return senha;
    }

    public Integer getPontos() {
            return pontos;
    }
    public void setPontos(Integer pontos) {
            this.pontos = pontos;
    }
}
