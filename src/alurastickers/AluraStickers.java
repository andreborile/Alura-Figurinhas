package alurastickers;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class AluraStickers {

    public static void main(String[] args) throws Exception {

        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados 
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}