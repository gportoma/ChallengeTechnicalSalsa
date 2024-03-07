package br.com.guilherme.util;

import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

public class YAMLConfigReader {
    private static final String filePath = "src/main/resources/config.yaml";

    public static String getValueByKey(String key) {
        Yaml yaml = new Yaml();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Map<String, Object> yamlData = yaml.load(fis);
            fis.close();
            return (String) yamlData.get(key);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo " + filePath + " não encontrado.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo YAML.");
            e.printStackTrace();
        }
        return null;
    }
}

