/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traceability;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Camila
 */
public class Traceability {

    public Traceability() {
    }

    public List<String> buscarSub(List<String> testPath) { // mudar para uma lista de testPath
        List<String> listString = new ArrayList<String>();

        for (String a : testPath) {
            File file = new File(a);
            File[] arquivos = file.listFiles();
            for (File arquivo : arquivos) {
                if (arquivo.isFile() && arquivo.getName().contains(".java")) {
                    String conteudo = arquivo.toString();
                    listString.add(conteudo);
                }
            }
        }
        return listString;
    }

    public List<String> testAssert(List<String> listAssert) {
        List<String> listParametersAssert = new ArrayList<>();

        for (String assertt : listAssert) {
            if (assertt.contains(",")) {
                String assertString = assertt;
                String splited[] = assertString.split("\\,");
                int num = splited.length;
                String replace = splited[num - 1].replaceAll(";", "");
                replace = replace.replaceFirst(" ", "");
                replace = replace.replaceAll(" ", ".");
                listParametersAssert.add(replace);

            } else {
                String assertString = assertt;
                String splited[] = assertString.split("\\(");
                int num = splited.length;
                String replace = splited[num - 1].replaceAll(";", "");
                replace = replace.replaceFirst(" ", "");
                replace = replace.replaceAll(" ", ".");
                listParametersAssert.add(replace);
            }
        }
        return listParametersAssert;
    }

    public List<Construct> chanceStringToConstruct(List<String> listConstruct) {
        List<Construct> listConstrutores = new ArrayList<>();
        for (String a : listConstruct) {
            if (a.contains("= new") || a.contains("=new")) {
                String assertString = a;
                String splited[] = assertString.split("new");
                String temp = splited[1].replaceAll(";", "");
                temp = temp.replaceAll(" ", "");
                String replace = splited[0];
                replace = replace.replaceFirst("=", "");
                String splited2[] = replace.split(" ");
                Construct construcObject = new Construct(splited2[splited2.length - 2], splited2[splited2.length - 1], temp);

                listConstrutores.add(construcObject);

            } else { // final
                String assertString = a;
                String splited[] = assertString.split("=");
                String temp = splited[1].replaceAll(";", "");
                temp = temp.replaceAll(" ", "");
                String replace = splited[0];
                replace = replace.replaceFirst("final", "");
                String splited2[] = replace.split(" ");
                Construct construcObject = new Construct(splited2[splited2.length - 2], splited2[splited2.length - 1], temp);

                listConstrutores.add(construcObject);
            }
        }
        return listConstrutores;
    }

    public List<String> parametrsAndConstruct(List<String> listAsserts, List<Construct> listConstructors) {
        List<String> listclassmethods = new ArrayList<>();

        for (Construct construct : listConstructors) {
            for (String asser : listAsserts) {
                if (asser.contains(construct.getNameConstruct())) {
                    String replace = asser.replaceAll(construct.getNameConstruct(), construct.getNameClass());
                    listclassmethods.add(replace);
                }
            }
        }
        return listclassmethods;
    }

    public List<String> finalList(List<String> initialList) {
        List<String> finalList = new ArrayList<>();
        int test = 0;
        for (String a : initialList) {
            test = 0;
            if (finalList.isEmpty()) {
                finalList.add(a);
            } else {
                for (String b : finalList) {
                    if (a.equals(b) == true) {
                        test = 1;
                        break;
                    } else {
                        test = 2;
                    }
                }
            }
            if (test == 2) {
                finalList.add(a);
            }
        }
        return finalList;
    }

    public List<String> traceability(String nameTest, List<String> testPath) {
        Traceability test = new Traceability();
        List<String> listAsserts = new ArrayList<>();
        List<String> listResult = new ArrayList<>();
        List<Construct> listResult2 = new ArrayList<>();
        List<String> listConstrutores = new ArrayList<>();

        for (String string : testPath) {
            try {
                File file = new File(string);
                BufferedReader fXmlFile = new BufferedReader(new FileReader(file));
                while (fXmlFile.ready()) {
                    String linha = fXmlFile.readLine();
                    if (linha.contains(nameTest)) { //testa se a linha contem o nome procurado, caso encontre, estamos na classe correta
                        linha = fXmlFile.readLine();
                        while (linha.contains("@Override") == false) { // enquanto for diferente de uma nova anotação
                            //  System.out.println(linha);
                            if (linha.contains("assert")) {
                                listAsserts.add(linha);
                                linha = fXmlFile.readLine();
                            } else {
                                if (linha.contains("= new") || linha.contains("=new") || linha.contains("final")) {
                                    listConstrutores.add(linha);
                                    linha = fXmlFile.readLine();
                                } else {
                                    linha = fXmlFile.readLine();
                                }
                            }
                        }
                    }
                }

                fXmlFile.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        listResult = test.testAssert(listAsserts);

        listResult2 = test.chanceStringToConstruct(listConstrutores);

        List<String> listResult3 = test.parametrsAndConstruct(listResult, listResult2);

        List<String> listResult4 = test.finalList(listResult3);

        return listResult4;

    }
}
