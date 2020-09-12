/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Camila
 */
public class Diretorio {

    private int id_diretorio;
    private String path;

    public Diretorio(int id_diretorio, String path) {
        this.id_diretorio = id_diretorio;
        this.path = path;
    }

    public Diretorio(String path) {
        this.path = path;
    }

    private Diretorio() {

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId_diretorio() {
        return id_diretorio;
    }

    public void setId_diretorio(int id_diretorio) {
        this.id_diretorio = id_diretorio;
    }

    public List<Diretorio> buscarSub(Diretorio diretorio) {
        List<Diretorio> diretorios = new ArrayList<Diretorio>();
        File file = new File(diretorio.getPath());
        File[] arquivos = file.listFiles();

        for (File arquivo : arquivos) {
            if (arquivo.isDirectory()) {
                Diretorio dir = new Diretorio();
                String conteudo = arquivo.toString();
                dir.setPath(conteudo);
                diretorios.add(dir);
            }
        }

        return diretorios;
    }

    public List<Diretorio> buscarBuilds(Diretorio diretorio) {
        List<Diretorio> diretorios = new ArrayList<Diretorio>();
        String str = "build";
        File file = new File(diretorio.getPath());
        File[] arquivos = file.listFiles();

        for (File arquivo : arquivos) {
            if (arquivo.isDirectory() && arquivo.getName().contains(str)) {
                File[] builds = arquivo.listFiles();
                for (File build : builds) {
                    Diretorio dir = new Diretorio();
                    String conteudo = build.toString();
                    dir.setPath(conteudo);
                    diretorios.add(dir);
                }

            }
        }

        return diretorios;
    }

    public String returnJunitLog(String path) {
        String junit = "\\junitResult.xml";
        String path2 = path;
        StringBuilder strBuf = new StringBuilder();
        strBuf.append(path2);
        strBuf.append(junit);
        return strBuf.toString();
    }

    public String returnBuildLog(String path) {
        String build = "\\build.xml";
        String path2 = path;
        StringBuilder strBuf = new StringBuilder();
        strBuf.append(path2);
        strBuf.append(build);
        return strBuf.toString();
    }

    public String returnChangeLog(String path) {
        String change = "\\change.xml";
        String path2 = path;
        StringBuilder strBuf = new StringBuilder();
        strBuf.append(path2);
        strBuf.append(change);
        return strBuf.toString();
    }

    public String returnProjecLog(String path) {
        String parent = path;
        String splited[] = parent.split("\\\\");
        String archive = "archive";
        String name = splited[4];
        String file = "pom.xml";
        StringBuilder strBuf = new StringBuilder();
        for (int i = 0; i < splited.length; i++) {
            strBuf.append(splited[i]);
            strBuf.append("\\");
        }
        
        strBuf.append(archive);
        strBuf.append("\\");
       // strBuf.append(name);
      //  strBuf.append("\\");
        strBuf.append(file);
        return strBuf.toString();
    }

    public String returnEnvironment(String path) {
        String parent = path;
        String splited[] = parent.split("\\\\");
        splited[3] = "workspace";
        StringBuilder strBuf = new StringBuilder();
        for(int i=0; i<(splited.length - 2); i++){
            strBuf.append(splited[i]);
            strBuf.append("\\");
        } 
        String target = buscarTarget(strBuf.toString());
        String temp = target;
        File file = new File(temp);
        File surefile = buscarSurfire(file);
        
        return surefile.toString();
    }
    
    public String buscarTarget(String diretorio) {
        File file = new File(diretorio);
        File[] arquivos = file.listFiles();
        String str = "target";

        for (File arquivo : arquivos) {
            if (arquivo.isDirectory() && arquivo.getName().contains(str)) {
                return arquivo.toString();
            }
        }
        return diretorio;
    }
    
    public File buscarSurfire(File diretorio) {
        //   String retorno = null;
        File[] arquivos = diretorio.listFiles();
        String str = "surefire-reports";
        String str2 = ".xml";

        for (File arquivo : arquivos) {
            if (arquivo.getName().contains(str) && arquivo.isDirectory()) {
                File[] contains = arquivo.listFiles();
            }else{
                buscarSurfire(arquivo);
            }
        }
        return diretorio;
    }

    public List<String> breakPerComma(String list) {
        List<String> listReturn = new ArrayList<>();
        String parent = list;
        String splited[] = parent.split("\\, ");
        for(int i = 0; i<splited.length; i++){
            listReturn.add(splited[i]);
        }
        
        return listReturn;
    }
    
}
