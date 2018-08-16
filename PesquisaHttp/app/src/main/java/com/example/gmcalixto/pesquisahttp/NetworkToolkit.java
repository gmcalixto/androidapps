package com.example.gmcalixto.pesquisahttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class NetworkToolkit {

        //Responsavel por realizar a operação de GET
        public static String doGet(String url){
            String retorno = "";

            try {
                URL apiEnd = new URL(url);
                int codigoResposta;
                HttpURLConnection conexao;
                InputStream is;

                conexao = (HttpURLConnection) apiEnd.openConnection();
                conexao.setRequestMethod("GET");
                conexao.setReadTimeout(15000);
                conexao.setConnectTimeout(15000);
                conexao.connect();

                codigoResposta = conexao.getResponseCode();
                if(codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST){
                    is = conexao.getInputStream();
                }else{
                    is = conexao.getErrorStream();
                }

                retorno = converterInputStreamToString(is);
                is.close();
                conexao.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }

            return retorno;
        }

    //Responsavel por realizar a operação de POST
    public static String doPost(String url, String postContent){
        String retorno = "";

        try {
            URL apiEnd = new URL(url);
            int codigoResposta;
            HttpURLConnection conexao;
            InputStream is;

            conexao = (HttpURLConnection) apiEnd.openConnection();
            conexao.setRequestMethod("POST");
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);
            conexao.setDoInput(true);
            conexao.setDoOutput(true);



            OutputStream os = conexao.getOutputStream();
            PrintStream ps = new PrintStream(os);

            ps.print(postContent);
            ps.close();


            conexao.connect();

            codigoResposta = conexao.getResponseCode();
            if(codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST){
                is = conexao.getInputStream();
            }else{
                is = conexao.getErrorStream();
            }

            retorno = converterInputStreamToString(is);
            os.close();
            conexao.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return retorno;
    } //Responsavel por realizar a operação de PUT
    //Responsavel por realizar a operação de PUT
    public static String doPut(String url, String putContent){
        String retorno = "";

        try {
            URL apiEnd = new URL(url);
            int codigoResposta;
            HttpURLConnection conexao;
            InputStream is;

            conexao = (HttpURLConnection) apiEnd.openConnection();
            conexao.setRequestMethod("PUT");
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);
            conexao.setDoInput(true);
            conexao.setDoOutput(true);



            OutputStream os = conexao.getOutputStream();
            PrintStream ps = new PrintStream(os);

            ps.print(putContent);
            ps.close();


            conexao.connect();

            codigoResposta = conexao.getResponseCode();
            if(codigoResposta < HttpURLConnection.HTTP_BAD_REQUEST){
                is = conexao.getInputStream();
            }else{
                is = conexao.getErrorStream();
            }

            retorno = converterInputStreamToString(is);
            os.close();
            conexao.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return retorno;
    }
    public static Boolean doDelete(String url){
        Boolean retorno = true;

        try {
            URL apiEnd = new URL(url);
            int codigoResposta;
            HttpURLConnection conexao;

            conexao = (HttpURLConnection) apiEnd.openConnection();
            conexao.setRequestMethod("DELETE");
            conexao.setReadTimeout(15000);
            conexao.setConnectTimeout(15000);
            conexao.connect();

            codigoResposta = conexao.getResponseCode();
            retorno = (codigoResposta == HttpURLConnection.HTTP_NO_CONTENT);
            conexao.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return retorno;
    }
        //Responsável por converter um InputStream para String
        private static String converterInputStreamToString(InputStream is){
            StringBuffer buffer = new StringBuffer();
            try{
                BufferedReader br;
                String linha;

                br = new BufferedReader(new InputStreamReader(is));
                while((linha = br.readLine())!=null){
                    buffer.append(linha);
                }

                br.close();
            }catch(IOException e){
                e.printStackTrace();
            }

            return buffer.toString();
        }
}
