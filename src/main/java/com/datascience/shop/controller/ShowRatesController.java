package com.datascience.shop.controller;

import com.datascience.shop.Rates;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ShowRatesController implements Controller {

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {

        URL url = null;
        try {
            url = new URL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Scanner scanner = null;
        try {
            scanner = new Scanner(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
            StringBuffer stringBuffer = new StringBuffer();
            while (scanner.hasNext()) {
                stringBuffer.append(scanner.next());
            }
            String result = stringBuffer.toString();
            result = result.replaceAll("<[^>]*>", "");
            ObjectMapper objectMapper = new ObjectMapper();
        List<Rates> ratesActual = null;
        try {
            ratesActual = Arrays.asList(objectMapper.readValue(result, Rates[].class));
            System.out.println(ratesActual.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

            req.setAttribute("rates", ratesActual);

        return new ControllerResultDto("rates");
    }


/*
        List<Rates> ratesActual=new ArrayList<>();
        ratesActual.add(new Rates("долар",28.5));
        req.setAttribute("rates", ratesActual);

        return new ControllerResultDto("rates");
*/
}


