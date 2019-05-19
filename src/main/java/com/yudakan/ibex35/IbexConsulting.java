package com.yudakan.ibex35;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class IbexConsulting {

    private static Comparator<Empresa> compValor = new Comparator<Empresa>() {
        @Override
        public int compare(Empresa e1, Empresa e2) {

            return Double.compare(Double.parseDouble(e1.getValor()), Double.parseDouble(e2.getValor()));
        }
    };

    private static Comparator<Empresa> compName = new Comparator<Empresa>() {
        @Override
        public int compare(Empresa e1, Empresa e2) {

            return e1.name.compareTo(e2.name);
        }
    };

    private static class Empresa {

        String name;
        String valor;

        public Empresa(String name, String valor) {
            this.name = name;
            this.valor = valor;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValor() {
            return valor;
        }

        public void setValor(String valor) {
            this.valor = valor;
        }

        @Override
        public String toString() {

            final StringBuilder str = new StringBuilder();

            str.append(name).append(", ").append(valor).append(" euros").append(".\n");

            return str.toString();
        }
    }

    private static String getHTML(String url) throws Exception {

        URL ibexURL = new URL(url);
        InputStreamReader html = new InputStreamReader(ibexURL.openStream());

        final StringBuilder str = new StringBuilder();
        int ch = html.read();

        while(ch != -1) {
            str.append((char)ch);
            ch = html.read();
        }

        html.close();
        return str.toString();
    }

    private static int findSubString(String ori, String pat, int start, int end) { // start inclusive, end exclusive

        int i, ch, count = 0;
        boolean found = false;

        for(i=start; i < end; i++) {

            ch = ori.charAt(i);

            if(ch == pat.charAt(count)) {
                if(count+1 == pat.length()) {
                    found = true;
                    break;
                }
                count++;
            }
            else count = 0;
        }

        return found ? i+1 : -1;
    }

    private static int findSubString(String ori, String pat, int start) {

        return findSubString(ori, pat, start, ori.length());
    }

    private static int findSubString(String ori, String pat) {

        return findSubString(ori, pat, 0, ori.length());
    }

    public static String getList(String sort) {

        StringBuilder builder = new StringBuilder();

        try {

            /* Filter */
            if(!sort.equals("valor") && !sort.equals("name") && !sort.equals("all"))
                throw new Exception();

            /* Get http data */
            String dataWeb = getHTML("http://www.bolsamadrid.es/esp/aspx/Mercados/Precios.aspx?indice=ESI100000000&punto=indice");


            /* Extract data */
            ArrayList<Empresa> llista = new ArrayList<>();
            StringBuilder str = new StringBuilder();
            String name, valor;
            final String taulaId = "id=\"ctl00_Contenido_tblAcciones\"";
            final String endTable = "</table>";
            int startIx = findSubString(dataWeb, taulaId);
            int endIx = findSubString(dataWeb, endTable, startIx);

            while( (startIx = findSubString(dataWeb, "<tr", startIx, endIx)) != - 1 ) {

                startIx = findSubString(dataWeb, "<td", startIx, endIx);
                startIx = findSubString(dataWeb, ">", startIx, endIx);
                startIx = findSubString(dataWeb, ">", startIx, endIx);

                while(dataWeb.charAt(startIx) != '<') {
                    str.append(dataWeb.charAt(startIx));
                    startIx++;
                }
                name = str.toString();
                str.delete(0, str.length());

                startIx = findSubString(dataWeb, "<td", startIx, endIx);
                startIx = findSubString(dataWeb, ">", startIx, endIx);

                while(dataWeb.charAt(startIx) != '<') {
                    if(dataWeb.charAt(startIx) == ',')
                        str.append('.');
                    else
                        str.append(dataWeb.charAt(startIx));

                    startIx++;
                }
                valor = str.toString();
                str.delete(0, str.length());

                llista.add(new Empresa(name, valor));
            }

            /* Sort */
            if(sort.equals("valor"))
                Collections.sort(llista, compValor);
            else if(sort.equals("name"))
                Collections.sort(llista, compName);


            /* Build list as str */
            for(Empresa e: llista)
                builder.append(e);

            return builder.toString();
        }
        catch(Exception e) {
            return "Vaya, pueeeeees... no quiero hacerlo.";
        }
    }
}